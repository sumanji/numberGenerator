package com.generator.service;

import java.util.List;
import java.util.Optional;

import com.generator.entity.UserInfo;

public interface IUser {
	
	Optional<UserInfo> getUser(String userId,String password);

	String getUserName();
	
	void saveUser(UserInfo user);
	
	List<UserInfo> getAllUser();
	
	UserInfo getUserById(Integer userId);

}
