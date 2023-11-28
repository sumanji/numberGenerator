package com.generator.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.generator.dao.IUserDao;
import com.generator.entity.UserInfo;
import com.generator.utilities.HelperClass;
import com.generator.utilities.HelperConstants;

@Service
public class UserServiceInfo implements IUser {

	@Autowired
    private IUserDao userDao;
    private String userName;
    
    
    
	
	@Override
	public Optional<UserInfo> getUser(String userName, String password) {
		password = HelperClass.encrypt(password);
		Optional<UserInfo> result = Optional.ofNullable(userDao.findUserByuserNameandPassword(userName, password));
		if(result.isPresent()) {
			setUserName(userName);
		}
		return result;
	}	
	
	@Override
	public String getUserName() {
		return userName;
	}
	
	private void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void saveUser(UserInfo user) {
			userDao.save(user);
	}

	@Override
	public List<UserInfo> getAllUser() {
		return  userDao.findAll();
	}

	@Override
	public UserInfo getUserById(Integer userId) {
		return userDao.findById(userId).get();
		
	}

	@Override
	public UserInfo getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
