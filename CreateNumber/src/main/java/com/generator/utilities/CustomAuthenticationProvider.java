package com.generator.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.generator.entity.UserInfo;
import com.generator.exception.ApplicationException;
import com.generator.service.IUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUser user;

	@Override
	public Authentication authenticate(Authentication authentication) throws RuntimeException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		Optional<UserInfo> authenticatedUser = null;
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("User Name or Password is Invalid");
			// throw new LoginException("User Name or Password is Invalid");
		}
		try {
			authenticatedUser = user.getUser(name, password);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}

		if (!authenticatedUser.isPresent()) {
			throw new BadCredentialsException("User Name or Password is Invalid");
			// throw new LoginException("User Name or Password is Invalid");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
		// auth.setAuthenticated(false);
		return auth;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}