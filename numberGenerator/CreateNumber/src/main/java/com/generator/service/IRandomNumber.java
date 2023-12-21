package com.generator.service;

import com.generator.entity.RandomNumberDetail;
import com.generator.pojo.ResponseBean;

public interface IRandomNumber {
	
	public RandomNumberDetail createNumber(RandomNumberDetail numberDetail);
	
	public boolean isCreateNumberAllowed();

	public ResponseBean getAllNumberDetails(Integer pageNumber, Integer pageSize);

	public RandomNumberDetail getNumberDetails(String date);

}
