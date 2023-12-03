package com.generator.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.generator.entity.LogoStorage;
import com.generator.entity.RandomNumberDetail;
import com.generator.entity.SessionManagement;
import com.generator.entity.UserInfo;
import com.generator.exception.ApplicationException;
import com.generator.pojo.ResponseBean;
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
	public boolean createNumber(Integer number) throws ApplicationException {
		RandomNumberDetail response = null;
		if (number != null) {

			if (numberService.isCreateNumberAllowed()) {
				try {
					Date currentDate = new Date(System.currentTimeMillis());
					RandomNumberDetail numberEntity = new RandomNumberDetail();
					numberEntity.setNumberInsertionDate(currentDate);
					numberEntity.setRandomNumber(number);
					response = numberService.createNumber(numberEntity);
				} catch (Exception e) {
					throw new ApplicationException("Error while saving  number.", HttpStatus.BAD_REQUEST.value());
				}
			} else {
				return false;
				//throw new ApplicationException("You have already created number today.Please try again tomorrow!!!", HttpStatus.BAD_REQUEST.value());
			}

		}
		return response != null;
	}

	@Override
	public boolean isSessionActive(String uuid) throws ApplicationException {
		boolean flag = false;
		try {
			flag = sessionService.isSessionActive(uuid);
		} catch (Exception e) {
			throw new ApplicationException("Session Expired", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
		}
		return flag;
	}

	@Override
	public void createSession(String userName, String cookie, String uuid) throws ApplicationException {
		// TODO Auto-generated method stub
		SessionManagement session = new SessionManagement();
		session.setUserName(userName);
		session.setCookie(cookie);
		session.setUserUUID(uuid);
		try {
			sessionService.createSession(session);
		} catch (Exception e) {
			throw new ApplicationException("Error while creating session", HttpStatus.BAD_REQUEST.value());
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteSession(String userName) throws ApplicationException {
		try {
			sessionService.deleteSession(userName);
		} catch (Exception e) {
			throw new ApplicationException("Error while deleting session.", HttpStatus.METHOD_FAILURE.value());
		}
	}

	@Override
	public LogoStorage getLogoDetails(Integer logoId) throws ApplicationException {
		LogoStorage response = null;
		try {
			Optional<LogoStorage> result = logoService.getLogoInfo(logoId);
			if (result.isPresent()) {
				response = result.get();
			}
			return response;
		} catch (Exception e) {
			throw new ApplicationException("Error while fetching logo details.", HttpStatus.METHOD_FAILURE.value());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void saveApplicationLogo(LogoStorage entity) throws ApplicationException {
		try {
			if (entity != null) {
				logoService.saveLogoInfo(entity);
			}
		} catch (Exception e) {
			throw new ApplicationException("Error while saving logo.", HttpStatus.BAD_REQUEST.value());
		}

	}

	@Override
	public void createuser(UserInfo user) throws ApplicationException {
		try {
			if (user != null && user.getPassword() != null) {
				String password = user.getPassword();
				Date currentDate = new Date(System.currentTimeMillis());
				user.setPassword(HelperClass.encrypt(password));
				user.setPasswordCreationDate(currentDate);
				userService.saveUser(user);
			}
		} catch (Exception e) {
			throw new ApplicationException("Error while creating user.", HttpStatus.BAD_REQUEST.value());
		}
	}

	@Override
	public List<UserInfo> getAllUser() throws ApplicationException {
		try {
			return userService.getAllUser();
		} catch (Exception e) {
			throw new ApplicationException("Error while fetching all users.", HttpStatus.METHOD_FAILURE.value());
		}
	}

	@Override
	public UserInfo getUserById(Integer userId) throws ApplicationException {
		try {
			return userService.getUserById(userId);
		} catch (Exception e) {
			throw new ApplicationException("Error while fetching user.", HttpStatus.METHOD_FAILURE.value());
		}
	}

	@Override
	public UserInfo getUser(String userName, String password) throws ApplicationException {
		try {
			return userService.getUser(userName, password).get();
		} catch (Exception e) {
			throw new ApplicationException("Error while fetching user.", HttpStatus.METHOD_FAILURE.value());
		}
	}

	@Override
	public ResponseBean findAllNumber(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		return numberService.getAllNumberDetails(pageNumber, pageSize);
	}

	@Override
	public RandomNumberDetail findNumber(String date) {   
		RandomNumberDetail  numberEntity = numberService.getNumberDetails(date);
		return numberEntity;
	}

}
