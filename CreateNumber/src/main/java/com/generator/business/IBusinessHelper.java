package com.generator.business;

import java.util.List;

import com.generator.entity.LogoStorage;
import com.generator.entity.UserInfo;
import com.generator.exception.ApplicationException;

public interface IBusinessHelper {
	
    boolean createNumber(Integer number) throws ApplicationException;
    
    boolean isSessionActive(String uuid) throws ApplicationException;

	void createSession(String userName, String cookie, String uuid) throws ApplicationException;
	
	
	void deleteSession(String userName) throws ApplicationException;
	
	
	LogoStorage getLogoDetails(Integer logoId) throws ApplicationException;
	
	void saveApplicationLogo(LogoStorage entity) throws ApplicationException;
	
	List<UserInfo> getAllUser() throws ApplicationException;
	
	UserInfo getUserById(Integer userId) throws ApplicationException;

	void createuser(UserInfo user) throws ApplicationException;
	
	UserInfo getUser(String userName,String password) throws ApplicationException;

    
}
