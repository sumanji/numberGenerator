package com.example.demo.business;

public interface IBusinessHelper {
	
    boolean createNumber(Integer number);
    
    boolean isSessionActive(String uuid);

	void createSession(String userName, String cookie, String uuid);
	
	
	void deleteSession(String userName);

    
}
