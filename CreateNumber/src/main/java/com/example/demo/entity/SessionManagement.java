package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.utilities.HelperConstants;

@Entity
public class SessionManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer sessionId;
	String userName;
	String cookie;
	String userUUID;
	private LocalDateTime sessionStartDateTime = LocalDateTime.now();
	private LocalDateTime sessionEndDateTime = LocalDateTime.now().plusMinutes(HelperConstants.sessionElapseTime);
	
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	public LocalDateTime getSessionStartDateTime() {
		return sessionStartDateTime;
	}
	public void setSessionStartDateTime(LocalDateTime sessionStartDateTime) {
		this.sessionStartDateTime = sessionStartDateTime;
	}
	public LocalDateTime getSessionEndDateTime() {
		return sessionEndDateTime;
	}
	public void setSessionEndDateTime(LocalDateTime sessionEndDateTime) {
		this.sessionEndDateTime = sessionEndDateTime;
	}
	
	

}
