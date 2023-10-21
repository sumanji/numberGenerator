package com.example.demo;

import javax.ws.rs.core.Response.Status;

public class ResponseBean {
	
	Status responseStatus;
	String systemInfo;
	String error;
	String uniqueIdentifier;
	public Status getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(Status responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getSystemInfo() {
		return systemInfo;
	}
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
	
	

}

