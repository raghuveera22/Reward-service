package com.retailer.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REWARD")
@NamedQuery(name = "RewardEntity.findAll", query = "SELECT e FROM RewardEntity e")
public class RewardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "reward_points")
	private Double rewardPoints;

	@Column(name = "transaction_date", columnDefinition = "DATE")
	private LocalDate transactionDate;

}
