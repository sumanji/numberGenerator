package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseBean;
import com.example.demo.business.IBusinessHelper;
import com.example.demo.entity.LogoStorage;
import com.example.demo.utilities.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api/v1/generator")
public class CreateNumberController {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	@PostMapping("/{identifierId}/create")
	public ResponseBean createNumber(@RequestBody Integer number, @PathVariable("identifierId") String identifierId,
			HttpServletRequest request) throws Exception {
		//String token = "";
		ResponseBean res = new ResponseBean();
		res.setResponseStatus(Status.OK);
		boolean cookieAccessTokenEmpty = validaterequest(request,identifierId);
		
		try {
 
			if (cookieAccessTokenEmpty) {
				res.setResponseStatus(Status.UNAUTHORIZED);
				res.setError("Unauthorized Access");
				return res;
			}
			businessHelper.createNumber(number);
			
		} catch (ExpiredJwtException | BadCredentialsException e) {
			String loggedInuser = request.getRemoteUser();
			businessHelper.deleteSession(loggedInuser);
			throw new BadCredentialsException("Unauthorized Access");
		} catch (Exception e) {
			res.setResponseStatus(Status.INTERNAL_SERVER_ERROR);
			res.setError("Error While creating number");
		}
		return res;

	}
	
	
	@GetMapping("/logo/{identifierId}/{logoId}")
	public Object logoDetails(@PathVariable("logoId") Integer logoId,@PathVariable("identifierId") String identifierId,HttpServletRequest request) {
		ResponseBean err_res = new ResponseBean();
		try {
		boolean cookieAccessTokenEmpty = validaterequest(request,identifierId);
		cookieAccessTokenEmpty = false;
		if (cookieAccessTokenEmpty) {
			err_res.setResponseStatus(Status.UNAUTHORIZED);
			err_res.setError("Unauthorized Access");
			return err_res;
		}
		
		}catch (Exception e) {
			err_res.setResponseStatus(Status.INTERNAL_SERVER_ERROR);
			err_res.setError("Error while fetching  logo details with error message as :: "+e.getMessage());
			return err_res;
		}
		return businessHelper.getLogoDetails(logoId);
		
	}
	
	
	
	@PostMapping("/logo/{identifierId}")
	public Object  logoDetails(HttpServletRequest request,@PathVariable("identifierId") String identifierId,@RequestBody LogoStorage logoDetails) {
		ResponseBean err_res = new ResponseBean();
		try {
		boolean cookieAccessTokenEmpty = validaterequest(request,identifierId);
		if (cookieAccessTokenEmpty) {
			err_res.setResponseStatus(Status.UNAUTHORIZED);
			err_res.setError("Unauthorized Access");
			return err_res;
		}
		
		businessHelper.saveApplicationLogo(logoDetails);
		
		}catch(Exception e) {
			err_res.setResponseStatus(Status.INTERNAL_SERVER_ERROR);
			err_res.setError("Error While Saving");
			return err_res;
		}
		err_res.setResponseStatus(Status.ACCEPTED);
		err_res.setError("Successfully saved details");
		return err_res;
	}
    public boolean validaterequest(HttpServletRequest request,String identifierId) {
    	Cookie[] cookies = request.getCookies();
    	boolean cookieAccessTokenEmpty = true;
    	String token = "";
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("access_token")) {
					token = cookie.getValue();
					cookieAccessTokenEmpty = token == null;
				}
			}
			
		}
		
		
		if (StringUtils.isEmpty(token) || !businessHelper.isSessionActive(identifierId)
				|| jwtservice.isTokenExpired(token) || !jwtservice.validateToken(token)) {
			cookieAccessTokenEmpty = true;
		}
		
		return cookieAccessTokenEmpty;
    }
}
