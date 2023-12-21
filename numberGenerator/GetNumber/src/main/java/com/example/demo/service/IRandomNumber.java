package com.example.demo.service;

import com.example.demo.ResponseEntity.ResponseBean;
import com.example.demo.entity.RandomNumberDetail;

public interface IRandomNumber {
	

	public RandomNumberDetail getNumberDetails(String date);

	public ResponseBean getAllNumberDetails(Integer pageNumber, Integer PageSize);
}
