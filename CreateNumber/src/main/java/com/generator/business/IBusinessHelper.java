package com.generator.business;

import java.util.List;

import com.generator.entity.LogoStorage;
import com.generator.entity.UserInfo;
import com.generator.exception.CreateNumberException;
import com.generator.exception.LoginException;
import com.generator.exception.LogoException;
import com.generator.exception.UserException;

public interface IBusinessHelper {
	
    boolean createNumber(Integer number) throws CreateNumberException;
    
    boolean isSessionActive(String uuid) throws LoginException;

	void createSession(String userName, String cookie, String uuid) throws LoginException;
	
	
	void deleteSession(String userName) throws LoginException;
	
	
	LogoStorage getLogoDetails(Integer logoId) throws LogoException;
	
	void saveApplicationLogo(LogoStorage entity) throws LogoException;
	
	List<UserInfo> getAllUser() throws UserException;
	
	UserInfo getUserById(Integer userId) throws UserException;

	void createuser(UserInfo user) throws UserException;

    
}
