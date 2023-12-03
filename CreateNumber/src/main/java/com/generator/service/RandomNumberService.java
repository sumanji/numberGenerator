package com.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.generator.dao.INumberDao;
import com.generator.entity.RandomNumberDetail;
import com.generator.pojo.ResponseBean;

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

	@Override
	public ResponseBean getAllNumberDetails(Integer pageNumber,Integer pageSize) {
		int countResult = (int)numberDao.count();
		int totalRecordviewed =  (pageNumber * pageSize);
		ResponseBean responseBean = new ResponseBean();
		
		Pageable firstPageWithTwoElements = null;
		
		if(totalRecordviewed<countResult) {
			firstPageWithTwoElements = PageRequest.of(pageNumber,pageSize);
		}else {
			int remainingRecordSize= countResult -(pageNumber-1 * pageSize);
			firstPageWithTwoElements = PageRequest.of(pageNumber,remainingRecordSize);
		}
		 
		responseBean.setTotalCount(countResult);
		responseBean.setResponseData(numberDao.findAll(firstPageWithTwoElements).getContent());
		return  responseBean;
	}

	@Override
	public RandomNumberDetail getNumberDetails(String date) {
		return numberDao.findNumberByDate(date);
	}

}
