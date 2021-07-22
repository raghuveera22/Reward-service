package com.retailer.app.service;

import com.retailer.app.entity.RewardEntity;
import com.retailer.app.entity.UserEntity;
import com.retailer.app.exception.RewardServiceException;
import com.retailer.app.model.Reward;
import com.retailer.app.model.RewardsForThreeMonthsDuration;
import com.retailer.app.repository.RewardRepository;
import com.retailer.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RewardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    ModelMapper modelMapper;

    @Value("${reward.firstThreshold}")
    int firstRewardThreshold;

    @Value("${reward.secondThreshold}")
    int secondRewardThreshold;

    public RewardsForThreeMonthsDuration saveReward(Integer userId, LocalDate transactionDate, Double transAmount) {

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            saveRewardToRepo(userId, transactionDate, transAmount);
        } else {
            throw new RewardServiceException("User not found Exception");
        }
        return null;
    }

    public RewardsForThreeMonthsDuration getRewardForUser(Integer userId) {

        List<RewardEntity> rewardList = rewardRepository.findRewardEntityByUserId(userId);

        if (!CollectionUtils.isEmpty(rewardList)) {
            Map<Integer, Double> monthlyReward = rewardList.stream()
                    .collect(Collectors.groupingBy(d -> d.getTransactionDate().getDayOfMonth(),
                            Collectors.summingDouble(RewardEntity::getRewardPoints)));
            Double totalReward = rewardList.stream().mapToDouble(f -> f.getRewardPoints()).sum();
            RewardsForThreeMonthsDuration rewardsForThreeMonthsDuration = new RewardsForThreeMonthsDuration();
            rewardsForThreeMonthsDuration.setUserId(userId);
            rewardsForThreeMonthsDuration.setMonthlyReward(monthlyReward);
            rewardsForThreeMonthsDuration.setTotalReward(totalReward);

            return rewardsForThreeMonthsDuration;
        }
        return null;
    }

    private Reward saveRewardToRepo(Integer userId, LocalDate transactionDate, Double transAmount) {

        double rewardPoints = 0;

        double tmp = transAmount;
        if (transAmount >= secondRewardThreshold) {
            rewardPoints = ((transAmount - secondRewardThreshold) * 2);
            tmp -= (transAmount - secondRewardThreshold);
        }
        if (tmp >= firstRewardThreshold) {
            rewardPoints += (tmp - firstRewardThreshold);
        }
        try {
            RewardEntity reward = new RewardEntity();
            reward.setUserId(userId);
            reward.setRewardPoints(rewardPoints);
            reward.setTransactionDate(transactionDate);
            RewardEntity savedReward = rewardRepository.save(reward);
            if (savedReward != null) {
                return modelMapper.map(savedReward, Reward.class);
            } else {
                throw new RewardServiceException("Reward not saved");
            }
        } catch (Exception e) {
            throw new RewardServiceException("Reward not saved");
        }
    }
}
