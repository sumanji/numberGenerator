package com.generator.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.business.IBusinessHelper;
import com.generator.entity.LogoStorage;
import com.generator.exception.ApplicationException;
import com.generator.pojo.ResponseBean;
import com.generator.utilities.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api/v1/generator")
public class LogoControllers {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	@GetMapping("/logo/{identifierId}/{logoId}")
	public Object logoDetails(@PathVariable("logoId") Integer logoId, @PathVariable("identifierId") String identifierId,
			HttpServletRequest request) throws Exception {
		try {
			boolean cookieAccessTokenEmpty = validaterequest(request, identifierId);
			cookieAccessTokenEmpty = false;
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED);
			}

		} catch (ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		return businessHelper.getLogoDetails(logoId);

	}

	@PostMapping("/logo/{identifierId}")
	public Object logoDetails(HttpServletRequest request, @PathVariable("identifierId") String identifierId,
			@RequestBody LogoStorage logoDetails) throws Exception {
		ResponseBean err_res = new ResponseBean();
		try {
			boolean cookieAccessTokenEmpty = validaterequest(request, identifierId);
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED);
			}

			businessHelper.saveApplicationLogo(logoDetails);

		} catch (ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		err_res.setResponseStatus(HttpStatus.ACCEPTED);
		err_res.setMessage("Successfully saved details");
		return err_res;
	}

	private boolean validaterequest(HttpServletRequest request, String identifierId) throws Exception {
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
