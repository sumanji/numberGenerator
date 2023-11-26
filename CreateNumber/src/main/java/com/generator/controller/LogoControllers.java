package com.generator.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.business.IBusinessHelper;
import com.generator.entity.LogoStorage;
import com.generator.exception.LogoException;
import com.generator.pojo.ResponseBean;
import com.generator.utilities.JwtService;

@RestController
@RequestMapping("/api/v1/generator")
public class LogoControllers {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	@GetMapping("/logo/{identifierId}/{logoId}")
	public Object logoDetails(@PathVariable("logoId") Integer logoId,@PathVariable("identifierId") String identifierId,HttpServletRequest request) throws Exception {
		//ResponseBean err_res = new ResponseBean();
		try {
		boolean cookieAccessTokenEmpty = validaterequest(request,identifierId);
		cookieAccessTokenEmpty = false;
		if (cookieAccessTokenEmpty) {
			//err_res.setResponseStatus(Status.UNAUTHORIZED);
			//err_res.setError("Unauthorized Access");
			//return err_res;
			throw new LogoException("Unauthorized Access");
		}
		
		}catch (Exception e) {
			//err_res.setResponseStatus(Status.INTERNAL_SERVER_ERROR);
			//err_res.setError("Error while fetching  logo details with error message as :: "+e.getMessage());
			//return err_res;
			throw new LogoException(e.getMessage());
		}
		return businessHelper.getLogoDetails(logoId);
		
	}
	
	
	
	@PostMapping("/logo/{identifierId}")
	public Object  logoDetails(HttpServletRequest request,@PathVariable("identifierId") String identifierId,@RequestBody LogoStorage logoDetails) throws Exception {
		ResponseBean err_res = new ResponseBean();
		try {
		boolean cookieAccessTokenEmpty = validaterequest(request,identifierId);
		if (cookieAccessTokenEmpty) {
			//err_res.setResponseStatus(Status.UNAUTHORIZED);
			//err_res.setError("Unauthorized Access");
			//return err_res;
			throw new LogoException("Unauthorized Access");
		}
		
		businessHelper.saveApplicationLogo(logoDetails);
		
		}catch(Exception e) {
			//err_res.setResponseStatus(Status.INTERNAL_SERVER_ERROR);
			//err_res.setError("Error While Saving");
			//return err_res;
			throw new LogoException("Error While Saving");
		}
		err_res.setResponseStatus(Status.ACCEPTED);
		err_res.setMessage("Successfully saved details");
		return err_res;
	}
    public boolean validaterequest(HttpServletRequest request,String identifierId) throws Exception {
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
