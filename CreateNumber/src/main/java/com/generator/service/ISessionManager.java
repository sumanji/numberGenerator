package com.generator.service;

import com.generator.entity.SessionManagement;

public interface ISessionManager {
	
	boolean isSessionActive(String uuid);
	
	void createSession(SessionManagement session);
	
	void deleteSession(String userName);

}
