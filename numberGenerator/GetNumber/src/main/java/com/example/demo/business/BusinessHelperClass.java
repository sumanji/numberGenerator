package com.example.demo.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ResponseEntity.ResponseBean;
import com.example.demo.entity.LogoStorage;
import com.example.demo.entity.RandomNumberDetail;
import com.example.demo.service.ILogo;
import com.example.demo.service.IRandomNumber;

@Service
public class BusinessHelperClass implements IBusinessHelper {
	
	@Autowired
	IRandomNumber numberService;
    @Autowired
	ILogo logoService;
    
	@Override
	public ResponseBean findAllNumber(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		return numberService.getAllNumberDetails(pageNumber, pageSize);
	}

	@Override
	public RandomNumberDetail findNumber(String date) {   
		RandomNumberDetail  numberEntity = numberService.getNumberDetails(date);
		return numberEntity;
	}
	
	@Override
	public LogoStorage getLogoDetails(Integer logoId) {
		LogoStorage response = null;
		
		Optional<LogoStorage> result =  logoService.getLogoInfo(logoId);
		if(result.isPresent()) {
			response = result.get();
		}
		return response;
	}

	@Override
	public List<LogoStorage> getAllLogos() {
		// TODO Auto-generated method stub
		return logoService.getAllLogos();
	}

}
