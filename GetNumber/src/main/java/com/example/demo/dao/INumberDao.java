package com.example.demo.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.RandomNumberDetail;

public interface INumberDao extends JpaRepository<RandomNumberDetail, Integer> {

	// @Query("SELECT rand FROM RandomNumberDetail rand WHERE rand.numberInsertionDate = :date")
	@Query(value = "SELECT * FROM random_number_store WHERE number_insertion_date Like %?1%", nativeQuery = true)
	 RandomNumberDetail findNumberByDate(@Param("date")String date);

}
