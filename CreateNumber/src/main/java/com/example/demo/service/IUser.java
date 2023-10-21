package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.UserInfo;

public interface IUser {
	
	Optional<UserInfo> getUser(String userId,String password);

	String getUserName();

}
