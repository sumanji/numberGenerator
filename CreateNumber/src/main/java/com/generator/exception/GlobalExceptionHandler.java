package com.generator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.generator.pojo.BaseBean;
import com.generator.pojo.ResponseBean;




@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler({ApplicationException.class})
    public ResponseEntity<BaseBean> handleException(ApplicationException exception) {
		return returnResponse(exception.getMessage(),exception.getErrorStatus(),exception.getStatusCode());
    }
	
	private ResponseEntity<BaseBean> returnResponse(String message,HttpStatus status,Integer statusCode){
		BaseBean responseBean = new BaseBean();
		responseBean.setResponseStatus(status);
		responseBean.setMessage(message);
		responseBean.setStatusCode(statusCode);
		ResponseEntity<BaseBean> response = new ResponseEntity<BaseBean>(responseBean,HttpStatus.OK);
		return response;
		
	}
	
}
