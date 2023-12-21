package com.generator.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.dao.ISessionStorage;
import com.generator.entity.SessionManagement;
import com.generator.utilities.HelperConstants;

@Service
public class SessionManager implements ISessionManager {
	
	@Autowired
	ISessionStorage sessionStorage;

	@Override
	public boolean isSessionActive(String uuid) {
	   LocalDateTime  endTime=  sessionStorage.getSessionEndTime(uuid);
	   LocalDateTime  currentTime = LocalDateTime.now();
		return currentTime.isBefore(endTime);
	}

	@Override
	public void createSession(SessionManagement session) {
		sessionStorage.save(session);
		
	}
	
	
	@Override
	public void deleteSession(String userName) {
		// TODO Auto-generated method stub
		sessionStorage.deleteSession(userName);
	}

}
