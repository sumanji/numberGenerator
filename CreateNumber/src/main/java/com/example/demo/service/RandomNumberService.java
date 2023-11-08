package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.INumberDao;
import com.example.demo.entity.RandomNumberDetail;

@Service
public class RandomNumberService implements IRandomNumber {

	@Autowired
	private INumberDao numberDao;

	@Override
	public RandomNumberDetail createNumber(RandomNumberDetail numberDetail) {
		// TODO Auto-generated method stub
		return numberDao.save(numberDetail);
	}

	
	

}
