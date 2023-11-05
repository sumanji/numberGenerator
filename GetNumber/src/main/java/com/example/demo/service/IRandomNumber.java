package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public interface IRandomNumber {
	
	public List<RandomNumberDetail> getAllNumberDetails();
	
	public RandomNumberDetail getNumberDetails(String date);
}
