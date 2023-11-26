package com.generator.pojo;

import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpStatus;

public class ResponseBean {
	
	Status responseStatus;
	String systemInfo;
	String message;
	
	String uniqueIdentifier;
	public Status getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(Status ok) {
		this.responseStatus = ok;
	}
	public String getSystemInfo() {
		return systemInfo;
	}
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}
	
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

