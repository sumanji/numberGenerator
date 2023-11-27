package com.generator.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.business.IBusinessHelper;
import com.generator.exception.ApplicationException;
import com.generator.pojo.ResponseBean;
import com.generator.utilities.JwtService;

@RestController
@RequestMapping("/api/v1/generator")
public class LogInController {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	/*
	 * This method will authenticate user
	 */
	@PostMapping("/login")
	public ResponseBean authenticateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// create a cookie
		String authorization = request.getHeader("Authorization");
		ResponseBean res = new ResponseBean();
		res.setResponseStatus(HttpStatus.OK);
		try {
			if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Basic")) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED);
			}
			String token = jwtservice.generateToken();
			Cookie cookie = new Cookie("access_token", token);
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			UUID uuid = UUID.randomUUID();
			String sessionDeatils = uuid.toString();
			businessHelper.createSession(request.getRemoteUser(), token, sessionDeatils);
			// add cookie to response
			response.addCookie(cookie);
			res.setUniqueIdentifier(sessionDeatils);
			return res;
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String loggedInuser = request.getRemoteUser();
			businessHelper.deleteSession(loggedInuser);
			Cookie cookie = new Cookie("access_token", null);
			cookie.setHttpOnly(true);
			cookie.setSecure(false);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.METHOD_FAILURE);
		}
	}

}
