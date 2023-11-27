package com.generator.pojo;

import org.springframework.http.HttpStatus;

public class ResponseBean {
	
	HttpStatus responseStatus;
	String message;
	
	String uniqueIdentifier;
	public HttpStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(HttpStatus ok) {
		this.responseStatus = ok;
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

