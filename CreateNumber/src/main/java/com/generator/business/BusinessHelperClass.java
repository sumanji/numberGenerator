package com.generator.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.entity.LogoStorage;
import com.generator.entity.RandomNumberDetail;
import com.generator.entity.SessionManagement;
import com.generator.entity.UserInfo;
import com.generator.exception.CreateNumberException;
import com.generator.exception.LoginException;
import com.generator.exception.LogoException;
import com.generator.exception.UserException;
import com.generator.service.ILogo;
import com.generator.service.IRandomNumber;
import com.generator.service.ISessionManager;
import com.generator.service.IUser;
import com.generator.utilities.HelperClass;

@Service
public class BusinessHelperClass implements IBusinessHelper {

	@Autowired
	IRandomNumber numberService;

	@Autowired
	ISessionManager sessionService;

	@Autowired
	IUser userService;

	@Autowired
	ILogo logoService;

	@Override
	public boolean createNumber(Integer number) throws CreateNumberException {
		RandomNumberDetail response = null;
		if (number != null) {
			try {
				Date currentDate = new Date(System.currentTimeMillis());
				RandomNumberDetail numberEntity = new RandomNumberDetail();
				numberEntity.setNumberInsertionDate(currentDate);
				numberEntity.setRandomNumber(number);
				response = numberService.createNumber(numberEntity);
			} catch (Exception e) {
				throw new CreateNumberException(e.getMessage());
			}

		}
		return response != null;
	}

	@Override
	public boolean isSessionActive(String uuid) throws LoginException {
		boolean flag = false;
		try {
			flag = sessionService.isSessionActive(uuid);
		} catch (Exception e) {
			throw new LoginException("Session Expired");
		}
		return flag;
	}

	@Override
	public void createSession(String userName, String cookie, String uuid) throws LoginException {
		// TODO Auto-generated method stub
		SessionManagement session = new SessionManagement();
		session.setUserName(userName);
		session.setCookie(cookie);
		session.setUserUUID(uuid);
		try {
			sessionService.createSession(session);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}

	}

	@Override
	public void deleteSession(String userName) throws LoginException {
		try {
		sessionService.deleteSession(userName);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	@Override
	public LogoStorage getLogoDetails(Integer logoId) throws LogoException {
		LogoStorage response = null;
		try {
		Optional<LogoStorage> result = logoService.getLogoInfo(logoId);
		if (result.isPresent()) {
			response = result.get();
		}
		return response;
		}catch (Exception e) {
			throw new LogoException(e.getMessage());
		}
	}

	@Override
	public void saveApplicationLogo(LogoStorage entity) throws LogoException {
		try {
		if (entity != null) {
			logoService.saveLogoInfo(entity);
		}}catch (Exception e) {
			throw new LogoException(e.getMessage());
		}
		

	}

	@Override
	public void createuser(UserInfo user) throws UserException {
		try {
		if(user	!= null && user.getPassword() != null) {
			String password = user.getPassword();
			Date currentDate = new Date(System.currentTimeMillis());
			user.setPassword(HelperClass.encrypt(password));
			user.setPasswordCreationDate(currentDate);
			userService.saveUser(user);
		}
		}catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}

	@Override
	public List<UserInfo> getAllUser() throws UserException {
		try {
		return userService.getAllUser();
		}catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}

	@Override
	public UserInfo getUserById(Integer userId) throws UserException {
		try {
		return userService.getUserById(userId);
		}catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}
	
	
	

}
