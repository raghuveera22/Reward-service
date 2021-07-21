package com.retailer.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.app.entity.RewardEntity;

@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Integer> {
	
	List<RewardEntity> findRewardEntityByUserId(Integer userId);

}
