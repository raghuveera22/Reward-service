package com.retailer.app.controller;

import com.retailer.app.model.RewardsForThreeMonthsDuration;
import com.retailer.app.service.RewardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/rewards")
@Api(tags = {"Retailer Reward Service"}, description = "Retailer Reward Service")
public class RewardsController {

    @Autowired
    private RewardService rewardService;

    @ApiOperation(value = "Reward on a Transaction")
    @PostMapping({"/saveRewardOnTransaction"})
    public ResponseEntity<?> saveRewardOnTransaction(@RequestParam(value = "userId", required = true) Integer userId,
                                                @RequestParam(value = "transactionDate", required = true)
												@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate transactionDate,
                                                @RequestParam(value = "transAmount", required = true) Double transAmount) throws IOException {
        log.info("Reward on a Transaction");
        RewardsForThreeMonthsDuration reward = rewardService.saveReward(userId, transactionDate, transAmount);
        return new ResponseEntity<>(reward, HttpStatus.CREATED);
    }

    @ApiOperation(value = "GET Rewards for User")
    @GetMapping({"/ getRewardForUser"})
    public ResponseEntity<?> getRewardForUser(@RequestParam(value = "userId", required = true) Integer userId) throws IOException {

        log.info("GET Rewards for User");
        RewardsForThreeMonthsDuration reward = rewardService.getRewardForUser(userId);
        return new ResponseEntity<>(reward, HttpStatus.CREATED);
    }
}
