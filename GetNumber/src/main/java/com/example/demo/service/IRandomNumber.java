package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public interface IRandomNumber {
	

	public RandomNumberDetail getNumberDetails(String date);

	List<RandomNumberDetail> getAllNumberDetails(Integer pageNumber, Integer PageSize);
}
