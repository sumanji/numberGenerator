package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseBean;
import com.example.demo.business.IBusinessHelper;
import com.example.demo.entity.RandomNumberDetail;
import com.example.demo.utilities.HelperClass;
import com.example.demo.utilities.JwtService;

@RestController
@RequestMapping("/api/v1")
public class CreateNumberController {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	/*
	 * This method will authenticate user
	 */
	@PostMapping("/login")
	public ResponseBean authenticateUser(HttpServletRequest request, HttpServletResponse response) {
		// create a cookie
		String token = jwtservice.generateToken();
		Cookie cookie = new Cookie("access_token", token);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		UUID uuid = UUID.randomUUID();
		String sessionDeatils = uuid.toString();
		sessionDetailsStorage.add(sessionDeatils);
		// add cookie to response
		response.addCookie(cookie);
		ResponseBean res = new ResponseBean();
		res.setResponseStatus(Status.OK);
		res.setUniqueIdentifier(sessionDeatils);
		res.setSystemInfo(HelperClass.getClientIpAddr(request));
		return res;
	}

	@PostMapping("/{identifierId}/create")
	public ResponseBean createNumber(@RequestBody Integer number, @PathVariable("identifierId") String identifierId,
			HttpServletRequest request) throws Exception {
		String token = "";
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("access_token")) {
					// do something
					token = cookie.getValue();
				}
			}
		}
		ResponseBean res = new ResponseBean();
		res.setResponseStatus(Status.OK);
		if (jwtservice.isTokenExpired(token)) {
			res.setResponseStatus(Status.NOT_ACCEPTABLE);
			res.setError("Token Expired");

		} else if (StringUtils.isEmpty(token) || !jwtservice.validateToken(token)
				|| !sessionDetailsStorage.contains(identifierId)) {
			res.setResponseStatus(Status.UNAUTHORIZED);
			res.setError("Unauthorized Access");
		} else {
			try {
				businessHelper.createNumber(number);
			} catch (Exception e) {
				res.setResponseStatus(Status.INTERNAL_SERVER_ERROR);
				res.setError("Error While creating number");
			}
		}
		return res;

	}

	@GetMapping("/allNumbers")
	public List<RandomNumberDetail> getAllNumbers() {
		List<RandomNumberDetail> response = null;
		try {
			response = businessHelper.findAllNumber();
		} catch (Exception e) {

		}
		return response;

	}

	@GetMapping("/number/{id}")
	public RandomNumberDetail getNumber(@PathVariable("id") Integer numberId) {
		RandomNumberDetail res = null;
		try {
			res = businessHelper.findNumber(numberId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;

	}
}
