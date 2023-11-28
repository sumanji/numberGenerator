package com.generator.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.business.IBusinessHelper;
import com.generator.entity.UserInfo;
import com.generator.pojo.BaseBean;
import com.generator.pojo.ResponseBean;

@RestController
@RequestMapping("/api/v1/generator")
public class UserController {

	@Autowired
	private IBusinessHelper businessHelper;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	@PostMapping("/user")
	public BaseBean createUser(@RequestBody UserInfo user) throws Exception {
		BaseBean res = new BaseBean();
		res.setResponseStatus(HttpStatus.OK);
		businessHelper.createuser(user);
		res.setMessage("User Created SuccessFully");
        return res;
	}
	
	
	@GetMapping("/user/{userId}")
	public UserInfo userDetails(@PathVariable("userId") Integer userId) throws Exception {
		return businessHelper.getUserById(userId);		
	}
	
	@GetMapping("/allUsers")
	public List<UserInfo> getAllUsers() throws Exception {
		return businessHelper.getAllUser();
		
	}
	
	
   
}
	
	

