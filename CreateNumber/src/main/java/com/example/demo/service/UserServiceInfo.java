package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDao;
import com.example.demo.entity.UserInfo;
import com.example.demo.utilities.HelperClass;
import com.example.demo.utilities.HelperConstants;

@Service
public class UserServiceInfo implements IUser {

	@Autowired
    private IUserDao userDao;
    private String userName;
    
    
    
	
	@Override
	public Optional<UserInfo> getUser(String userName, String password) {
		// TODO Auto-generated method stub
		password = HelperClass.encrypt(password);
		Optional<UserInfo> result = Optional.ofNullable(userDao.findUserByuserNameandPassword(userName, password));
		//return userDao.findById(1);
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
	@PostConstruct
	public void saveUser() {
		
		UserInfo dummy_user = new UserInfo();
		Date currentDate = new Date(System.currentTimeMillis());
		dummy_user.setEmail("test@email.com");
		dummy_user.setPassword(HelperClass.encrypt("root"));
		dummy_user.setName("Application Flow bite");
		dummy_user.setPasswordCreationDate(currentDate);
		dummy_user.setSecurityAnswer("test");
		dummy_user.setUserName("root");
		// TODO Auto-generated method stub
		userDao.save(dummy_user);
		
	}
	
	
	
	
	
	

}
