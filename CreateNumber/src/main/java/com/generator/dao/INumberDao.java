package com.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generator.entity.RandomNumberDetail;

public interface INumberDao extends JpaRepository<RandomNumberDetail, Integer> {
	
	@Query("select count(1) from RandomNumberDetail r  where r.numberInsertionDate = curdate()")
	Integer isNumberCreationAllowed();

	@Query(value = "SELECT * FROM random_number_store WHERE number_insertion_date Like %?1%", nativeQuery = true)
	 RandomNumberDetail findNumberByDate(@Param("date")String date);

}
