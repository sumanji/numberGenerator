package com.example.demo.business;

import com.example.demo.entity.LogoStorage;

public interface IBusinessHelper {
	
    boolean createNumber(Integer number);
    
    boolean isSessionActive(String uuid);

	void createSession(String userName, String cookie, String uuid);
	
	
	void deleteSession(String userName);
	
	
	LogoStorage getLogoDetails(Integer logoId);
	
	void saveApplicationLogo(LogoStorage entity);

    
}
