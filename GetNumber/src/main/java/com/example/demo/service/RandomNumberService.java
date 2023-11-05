package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.INumberDao;
import com.example.demo.entity.RandomNumberDetail;

@Service
public class RandomNumberService implements IRandomNumber {

	@Autowired
	private INumberDao numberDao;



	@Override
	public List<RandomNumberDetail> getAllNumberDetails() {
		return numberDao.findAll();
	}

	@Override
	public RandomNumberDetail getNumberDetails(String date) {
		return numberDao.findNumberByDate(date);
	}
	
	
	

}
