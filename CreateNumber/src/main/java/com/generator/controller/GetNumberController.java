package com.generator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generator.business.IBusinessHelper;
import com.generator.entity.RandomNumberDetail;
import com.generator.exception.ApplicationException;
import com.generator.pojo.ResponseBean;
import com.generator.utilities.JwtService;
import com.generator.utilities.RequestValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;


@RestController
@RequestMapping("/api/v1/generator")
@CrossOrigin(value = "*")
public class GetNumberController {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;
	
	@GetMapping("/{identifierId}/allNumbers")
	public ResponseBean getAllNumbers(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,
			@PathVariable("identifierId") String identifierId,HttpServletRequest request) {
		ResponseBean response = null;
		try {
			boolean cookieAccessTokenEmpty = RequestValidator.validaterequest(request, identifierId,businessHelper,jwtservice);
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED.value());
			}
			response = businessHelper.findAllNumber(pageNum, pageSize);
		} catch (SignatureException | ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			throw new ApplicationException("Please login again. session expired!!!",
					e instanceof ApplicationException ? ((ApplicationException) e).getStatusCode()
							: HttpStatus.UNAUTHORIZED.value());
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
		return response;

	}

	@GetMapping("/{identifierId}/number/{date}")
	public RandomNumberDetail getNumber(@PathVariable("date") String date
			,@PathVariable("identifierId") String identifierId,HttpServletRequest request) {

		RandomNumberDetail res = null;
		try {
			boolean cookieAccessTokenEmpty = RequestValidator.validaterequest(request, identifierId,businessHelper,jwtservice);
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED.value());
			}
			res = businessHelper.findNumber(date);
		} catch (SignatureException | ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			throw new ApplicationException("Please login again. session expired!!!",
					e instanceof ApplicationException ? ((ApplicationException) e).getStatusCode()
							: HttpStatus.UNAUTHORIZED.value());
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
		return res;

	}
	
}
