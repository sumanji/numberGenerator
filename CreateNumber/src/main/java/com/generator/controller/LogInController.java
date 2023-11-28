package com.generator.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
import com.generator.entity.UserInfo;
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
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Logged in successfully");
		try {
			if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Basic")) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED.value());
			}
			String token = jwtservice.generateToken();
			Cookie cookie = new Cookie("access_token", token);
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			UUID uuid = UUID.randomUUID();
			String sessionDeatils = uuid.toString();
			businessHelper.createSession(request.getRemoteUser(), token, sessionDeatils);
			if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
				// Authorization: Basic base64credentials
				String base64Credentials = authorization.substring("Basic".length()).trim();
				byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
				String credentials = new String(credDecoded, StandardCharsets.UTF_8);
				// credentials = username:password
				final String[] values = credentials.split(":", 2);
				UserInfo user = businessHelper.getUser(values[0], values[1]);
				user.setPassword(null);
				user.setSecurityAnswer(null);
				res.setUserInfo(user);
			}
			response.addCookie(cookie);
			res.setUniqueIdentifier(sessionDeatils);
			return res;
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
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
			throw new ApplicationException(e.getMessage(), HttpStatus.METHOD_FAILURE.value());
		}
	}

}
