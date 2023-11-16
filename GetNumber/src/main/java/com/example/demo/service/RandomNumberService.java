package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.INumberDao;
import com.example.demo.entity.RandomNumberDetail;

@Service
public class RandomNumberService implements IRandomNumber {

	@Autowired
	private INumberDao numberDao;



	@Override
	public List<RandomNumberDetail> getAllNumberDetails(Integer pageNumber,Integer pageSize) {
		int countResult = (int)numberDao.count();
		int totalRecordviewed =  (pageNumber * pageSize);
		
		Pageable firstPageWithTwoElements = null;
		
		if(totalRecordviewed<countResult) {
			firstPageWithTwoElements = PageRequest.of(pageNumber,pageSize);
		}else {
			int remainingRecordSize= countResult -(pageNumber-1 * pageSize);
			firstPageWithTwoElements = PageRequest.of(pageNumber,remainingRecordSize);
		}
		 
		
		return numberDao.findAll(firstPageWithTwoElements).getContent();
	}

	@Override
	public RandomNumberDetail getNumberDetails(String date) {
		return numberDao.findNumberByDate(date);
	}
	
	
	

}
