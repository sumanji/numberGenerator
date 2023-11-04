package com.example.demo.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RandomNumberDetail;
import com.example.demo.service.IRandomNumber;
import com.example.demo.service.IUser;

@Service
public class BusinessHelperClass implements IBusinessHelper {
	
	@Autowired
	IRandomNumber numberService;

	@Override
	public boolean createNumber(Integer number) {
		// TODO Auto-generated method stub
		
		RandomNumberDetail response = null;
		if(number != null) {
			// Formatting as per given pattern in the argument
	        SimpleDateFormat ft
	            = new SimpleDateFormat("dd-MM-yyyy");
	 
	        try {
	        	Date currentDate = new Date(System.currentTimeMillis());
	        
				//Date currentDate = ft.parse(new Date().toString());
				RandomNumberDetail  numberEntity = new RandomNumberDetail();
				numberEntity.setNumberInsertionDate(currentDate);
				numberEntity.setRandomNumber(number);  
				response = numberService.createNumber(numberEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return response != null;
	}

	@Override
	public List<RandomNumberDetail> findAllNumber() {
		// TODO Auto-generated method stub
		return numberService.getAllNumberDetails();
	}

	@Override
	public RandomNumberDetail findNumber(Integer numberId) {
		// TODO Auto-generated method stub
		return numberService.getNumberDetails(numberId);
	}

}
