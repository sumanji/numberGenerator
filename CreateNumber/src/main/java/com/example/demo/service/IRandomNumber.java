package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public interface IRandomNumber {
	
	public RandomNumberDetail createNumber(RandomNumberDetail numberDetail);

	public List<RandomNumberDetail> getAllNumberDetails();
	
	public RandomNumberDetail getNumberDetails(Integer id);
}
