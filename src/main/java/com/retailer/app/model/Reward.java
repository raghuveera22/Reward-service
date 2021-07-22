package com.retailer.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Integer userId;

	private Double rewardPoints;

	private LocalDate transactionDate;

}
