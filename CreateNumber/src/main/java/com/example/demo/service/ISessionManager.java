package com.example.demo.service;

import com.example.demo.entity.SessionManagement;

public interface ISessionManager {
	
	boolean isSessionActive(String uuid);
	
	void createSession(SessionManagement session);
	
	void deleteSession(String userName);

}
