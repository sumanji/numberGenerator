package com.example.demo.business;

import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public interface IBusinessHelper {
	
    boolean createNumber(Integer number);
    List<RandomNumberDetail> findAllNumber();
    RandomNumberDetail findNumber(Integer numberId);
    
}
