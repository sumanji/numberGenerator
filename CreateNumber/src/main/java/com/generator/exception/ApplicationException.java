package com.generator.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpStatus errorStatus;
	
	
	public ApplicationException(String message, HttpStatus status) {
		super(message);
		setErrorStatus(errorStatus);
	}
	public HttpStatus getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(HttpStatus errorStatus) {
		this.errorStatus = errorStatus;
	}

}
	
