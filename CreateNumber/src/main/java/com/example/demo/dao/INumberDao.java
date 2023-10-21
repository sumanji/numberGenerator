package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RandomNumberDetail;

public interface INumberDao extends JpaRepository<RandomNumberDetail, Integer> {

}
