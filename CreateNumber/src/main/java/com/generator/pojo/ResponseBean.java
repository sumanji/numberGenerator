package com.generator.pojo;

import com.generator.entity.UserInfo;

public class ResponseBean extends BaseBean {
	
	String uniqueIdentifier;
	UserInfo userInfo;
	
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	

}

