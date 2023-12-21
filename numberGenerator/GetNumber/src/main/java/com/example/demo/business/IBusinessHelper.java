package com.example.demo.business;

import java.util.Date;
import java.util.List;

import com.example.demo.ResponseEntity.ResponseBean;
import com.example.demo.entity.LogoStorage;
import com.example.demo.entity.RandomNumberDetail;

public interface IBusinessHelper {
	
  
    RandomNumberDetail findNumber(String date);
	ResponseBean findAllNumber(Integer pageNumber, Integer pageSize);
	LogoStorage getLogoDetails(Integer logoId);
	List<LogoStorage> getAllLogos();
    
}
