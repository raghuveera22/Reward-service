package com.retailer.app.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.app.model.RewardsForThreeMonthsDuration;
import com.retailer.app.service.RewardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/rewads")
@Api(tags = {"Retailer Reward Service"},description = "Retailer Reward Service")
public class RewardsController {

	private static final Logger logger = Logger.getLogger(RewardsController.class);
	@Autowired
	private RewardService rewardService;
	ObjectMapper mapper = new ObjectMapper();

	@ApiOperation(value = "Reward on a Transaction")
	@PostMapping({ "/rewardOnTrasaction" })
	public ResponseEntity<?> rewardOnTrasaction(@RequestParam(value = "userId", required = true) Integer userId, 
			@RequestParam(value = "transactionDate", required = true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDate transactionDate,			
			@RequestParam(value = "transAmount", required = true) Double transAmount)
					throws IOException {
		logger.info("Reward on a Transaction");

		RewardsForThreeMonthsDuration reward = rewardService.saveReward(userId,transactionDate,transAmount);
		return new ResponseEntity<>(reward, HttpStatus.CREATED);
	}
	@ApiOperation(value = "Reward on a Transaction")
	@GetMapping({ "/test" })
	public ResponseEntity<?> test()
					throws IOException {
		logger.info("Reward on a Transaction");

		return new ResponseEntity<>("test", HttpStatus.CREATED);
	}

	}
