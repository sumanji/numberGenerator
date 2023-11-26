package com.generator.exception;

import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.generator.pojo.ResponseBean;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler({CreateNumberException.class})
    public ResponseEntity<ResponseBean> handleCreateNumberException(CreateNumberException exception) {
		return returnResponse(exception.getMessage());
    }
	
	
	@ExceptionHandler({LoginException.class})
    public ResponseEntity<ResponseBean> handleLoginException(LoginException exception) {
		return returnResponse(exception.getMessage());
	}
	
	@ExceptionHandler({LogoException.class})
    public ResponseEntity<ResponseBean> handleLogoException(LogoException exception) {
		return returnResponse(exception.getMessage());
    }
	
	@ExceptionHandler({UserException.class})
    public ResponseEntity<ResponseBean> handleUserException(UserException exception) {
       return returnResponse(exception.getMessage());
    }

	
	private ResponseEntity<ResponseBean> returnResponse(String message){
		ResponseBean responseBean = new ResponseBean();
		responseBean.setResponseStatus(Status.OK);
		responseBean.setMessage(message);
		ResponseEntity<ResponseBean> response = new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		return response;
		
	}
	
}
