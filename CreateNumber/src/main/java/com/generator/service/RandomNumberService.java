package com.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.dao.INumberDao;
import com.generator.entity.RandomNumberDetail;

@Service
public class RandomNumberService implements IRandomNumber {

	@Autowired
	private INumberDao numberDao;

	@Override
	public RandomNumberDetail createNumber(RandomNumberDetail numberDetail) {
		return numberDao.save(numberDetail);
	}

	@Override
	public boolean isCreateNumberAllowed() {
		return numberDao.isNumberCreationAllowed()==0?true:false;
	}

}
