package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ISessionStorage;
import com.example.demo.entity.SessionManagement;
import com.example.demo.utilities.HelperConstants;

@Service
public class SessionManager implements ISessionManager {
	
	@Autowired
	ISessionStorage sessionStorage;

	@Override
	public boolean isSessionActive(String uuid) {
	   LocalDateTime  endTime=  sessionStorage.getSessionEndTime(uuid);
	   LocalDateTime  currentTime = LocalDateTime.now().minusMinutes(HelperConstants.sessionElapseTime);
		return currentTime.isBefore(endTime);
	}

	@Override
	public void createSession(SessionManagement session) {
		sessionStorage.save(session);
		
	}

}
