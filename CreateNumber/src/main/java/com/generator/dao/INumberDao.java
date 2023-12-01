package com.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generator.entity.RandomNumberDetail;

public interface INumberDao extends JpaRepository<RandomNumberDetail, Integer> {
	
	@Query("select count(1) from RandomNumberDetail r  where r.numberInsertionDate = curdate()")
	Integer isNumberCreationAllowed();

}
