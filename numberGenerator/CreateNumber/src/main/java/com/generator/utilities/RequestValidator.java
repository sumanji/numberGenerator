package com.generator.utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.generator.business.IBusinessHelper;

public class RequestValidator {

	public static boolean validaterequest(HttpServletRequest request, String identifierId ,IBusinessHelper businessHelper,JwtService jwtservice) throws Exception {
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
