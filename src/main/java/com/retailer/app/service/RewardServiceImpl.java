package com.retailer.app.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.retailer.app.entity.RewardEntity;
import com.retailer.app.entity.UserEntity;
import com.retailer.app.exception.RewardServiceException;
import com.retailer.app.model.Reward;
import com.retailer.app.model.RewardsForThreeMonthsDuration;
import com.retailer.app.repository.RewardRepository;
import com.retailer.app.repository.UserRepository;

/**
 *
 * Service Implementation class for note crud operations.
 */
@Service
public class RewardServiceImpl implements RewardService {

	private static final Logger logger = Logger.getLogger(RewardServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	RewardRepository rewardRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public RewardsForThreeMonthsDuration saveReward(Integer userId, LocalDate transactionDate, Double transAmount) {

		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if (userEntity.isPresent()) {
			double rewardPoints = 0;

			long monthsBetween = ChronoUnit.MONTHS.between(transactionDate.withDayOfMonth(1),
					userEntity.get().getRegisteredDate().withDayOfMonth(1));
			System.out.println(monthsBetween);
			if (monthsBetween <= 3) {

				double tmp = transAmount;
				if (transAmount >= 100) {
					rewardPoints = ((transAmount - 100) * 2);
					tmp -= (transAmount - 100);
				}
				if (tmp >= 50) {
					rewardPoints += (tmp - 50);
				}

				RewardEntity reward = new RewardEntity();
				reward.setUserId(userId);
				reward.setRewardPoints(rewardPoints);
				reward.setTransactionDate(transactionDate);
				//RewardEntity savedReward =
						rewardRepository.save(reward);

			}

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

		} else {
			throw new RewardServiceException("User not found Exception");
		}

		return null;
	}

}
