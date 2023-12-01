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
import com.generator.pojo.BaseBean;
import com.generator.pojo.ResponseBean;
import com.generator.utilities.JwtService;
import com.generator.utilities.RequestValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestController
@RequestMapping("/api/v1/generator")
public class LogoControllers {

	@Autowired
	private IBusinessHelper businessHelper;

	@Autowired
	private JwtService jwtservice;

	Set<String> sessionDetailsStorage = new HashSet<String>();

	@GetMapping("/logo/{identifierId}/{logoId}")
	public BaseBean logoDetails(@PathVariable("logoId") Integer logoId, @PathVariable("identifierId") String identifierId,
			HttpServletRequest request) throws Exception {
		try {
			boolean cookieAccessTokenEmpty = RequestValidator.validaterequest(request, identifierId,businessHelper,jwtservice);
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED.value());
			}

		} catch (SignatureException | ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			throw new ApplicationException("Please login again. session expired!!!",
					e instanceof ApplicationException ? ((ApplicationException) e).getStatusCode()
							: HttpStatus.UNAUTHORIZED.value());
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
		ResponseBean res = new ResponseBean();
		res.setResponseStatus(HttpStatus.OK);
		res.setStatusCode(HttpStatus.OK.value());
		res.setResponseData(businessHelper.getLogoDetails(logoId));
		res.setMessage("Application settings  fetched successfully");
		return res;

	}

	@PostMapping("/logo/{identifierId}")
	public BaseBean logoDetails(HttpServletRequest request, @PathVariable("identifierId") String identifierId,
			@RequestBody LogoStorage logoDetails) throws Exception {
		BaseBean res = new BaseBean();
		try {
			boolean cookieAccessTokenEmpty = RequestValidator.validaterequest(request, identifierId,businessHelper,jwtservice);
			if (cookieAccessTokenEmpty) {
				throw new ApplicationException("Unauthorized Access", HttpStatus.UNAUTHORIZED.value());
			}

			businessHelper.saveApplicationLogo(logoDetails);

		} catch (SignatureException | ExpiredJwtException | BadCredentialsException | ApplicationException e) {
			throw new ApplicationException("Please login again. session expired!!!",
					e instanceof ApplicationException ? ((ApplicationException) e).getStatusCode()
							: HttpStatus.UNAUTHORIZED.value());
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
		res.setResponseStatus(HttpStatus.OK);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Application settings  saved successfully");
		return res;
	}

}
