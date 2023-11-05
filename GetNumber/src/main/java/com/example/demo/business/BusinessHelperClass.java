package com.example.demo.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RandomNumberDetail;
import com.example.demo.service.IRandomNumber;

@Service
public class BusinessHelperClass implements IBusinessHelper {
	
	@Autowired
	IRandomNumber numberService;


	@Override
	public List<RandomNumberDetail> findAllNumber() {
		// TODO Auto-generated method stub
		return numberService.getAllNumberDetails();
	}

	@Override
	public RandomNumberDetail findNumber(String date) {   
		RandomNumberDetail  numberEntity = numberService.getNumberDetails(date);
		return numberEntity;
	}

}
