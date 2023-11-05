package com.example.demo.business;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public interface IBusinessHelper {
	
    List<RandomNumberDetail> findAllNumber();
    RandomNumberDetail findNumber(String date);
    
}
