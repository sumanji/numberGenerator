package com.generator.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpStatus errorStatus;
	Integer statusCode; 
	
	public ApplicationException(String message, int statuscode) {
		super(message);
		setErrorStatus(HttpStatus.valueOf(statuscode));
		setStatusCode(statuscode);
	}
	public HttpStatus getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(HttpStatus errorStatus) {
		this.errorStatus = errorStatus;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
	
