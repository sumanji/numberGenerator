package com.example.demo.business;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public interface IBusinessHelper {
	
  
    RandomNumberDetail findNumber(String date);
	List<RandomNumberDetail> findAllNumber(Integer pageNumber, Integer pageSize);
    
}
