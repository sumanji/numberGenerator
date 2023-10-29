package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDao;
import com.example.demo.entity.UserInfo;

@Service
public class UserServiceInfo implements IUser {

	@Autowired
    private IUserDao userDao;
    private String userName;
	
	@Override
	public Optional<UserInfo> getUser(String userName, String password) {
		// TODO Auto-generated method stub
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
	
	
	
	
	
	

}
