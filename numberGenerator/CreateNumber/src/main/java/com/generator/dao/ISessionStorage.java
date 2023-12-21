package com.generator.dao;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generator.entity.SessionManagement;

public interface ISessionStorage extends JpaRepository<SessionManagement,Integer> {
	@Query("SELECT session.sessionEndDateTime FROM SessionManagement session WHERE session.userUUID = :uuid")
	LocalDateTime getSessionEndTime(@Param("uuid")String UUId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM SessionManagement session WHERE session.userName = :userId")
	void  deleteSession(@Param("userId")String userId);

}
