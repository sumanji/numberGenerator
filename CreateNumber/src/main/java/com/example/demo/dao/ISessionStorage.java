package com.example.demo.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.SessionManagement;

public interface ISessionStorage extends JpaRepository<SessionManagement,Integer> {
	@Query("SELECT session.sessionEndDateTime FROM SessionManagement session WHERE session.userUUID = :uuid")
	LocalDateTime getSessionEndTime(@Param("uuid")String UUId);

}
