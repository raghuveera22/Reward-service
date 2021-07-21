package com.retailer.app.service;

import java.time.LocalDate;

import com.retailer.app.model.RewardsForThreeMonthsDuration;

public interface RewardService {

	RewardsForThreeMonthsDuration saveReward(Integer userId, LocalDate transactionDate, Double transAmount);

}