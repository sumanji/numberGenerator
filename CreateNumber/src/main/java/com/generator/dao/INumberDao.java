package com.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generator.entity.RandomNumberDetail;

public interface INumberDao extends JpaRepository<RandomNumberDetail, Integer> {

}
