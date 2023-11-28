package com.generator.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.business.IBusinessHelper;
import com.generator.exception.ApplicationException;
import com.generator.pojo.BaseBean;
import com.generator.pojo.ResponseBean;
import com.generator.utilities.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestController
@RequestMapping("/api/v1/generator")
public class CreateNumberController {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	@PostMapping("/{identifierId}/create")
	public BaseBean createNumber(@RequestBody Integer number, @PathVariable("identifierId") String identifierId,
			HttpServletRequest request) throws Exception {
		BaseBean res = new BaseBean();
		res.setResponseStatus(HttpStatus.OK);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Number saved successfully");

		try {
			boolean cookieAccessTokenEmpty = validaterequest(request, identifierId);
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED.value());
			}
			businessHelper.createNumber(number);

		} catch (SignatureException | ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			String loggedInuser = request.getRemoteUser();
			businessHelper.deleteSession(loggedInuser);
			throw new ApplicationException(e.getMessage(),
					e instanceof ApplicationException ? ((ApplicationException) e).getStatusCode()
							: HttpStatus.UNAUTHORIZED.value());
		} catch (Exception e) {
			throw new ApplicationException("Error While creating number", HttpStatus.METHOD_FAILURE.value());
		}
		return res;

	}

	public boolean validaterequest(HttpServletRequest request, String identifierId) throws Exception {
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
