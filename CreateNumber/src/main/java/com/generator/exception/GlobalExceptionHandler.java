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
	
	
	/*
	 * @ExceptionHandler({LoginException.class}) public ResponseEntity<ResponseBean>
	 * handleLoginException(LoginException exception) { return
	 * returnResponse(exception.getMessage(),exception.getErrorStatus()); }
	 * 
	 * @ExceptionHandler({LogoException.class}) public ResponseEntity<ResponseBean>
	 * handleLogoException(LogoException exception) { return
	 * returnResponse(exception.getMessage(),exception.getErrorStatus()); }
	 * 
	 * @ExceptionHandler({UserException.class}) public ResponseEntity<ResponseBean>
	 * handleUserException(UserException exception) { return
	 * returnResponse(exception.getMessage(),exception.getErrorStatus()); }
	 */
	
	private ResponseEntity<BaseBean> returnResponse(String message,HttpStatus status,Integer statusCode){
		BaseBean responseBean = new BaseBean();
		responseBean.setResponseStatus(status);
		responseBean.setMessage(message);
		responseBean.setStatusCode(statusCode);
		ResponseEntity<BaseBean> response = new ResponseEntity<BaseBean>(responseBean,HttpStatus.OK);
		return response;
		
	}
	
}
