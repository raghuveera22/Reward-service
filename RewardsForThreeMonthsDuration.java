package com.retailer.app.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardsForThreeMonthsDuration {

	private Integer userId;
	private Map<Integer, Double> monthlyReward;
	private Double totalReward;

}
