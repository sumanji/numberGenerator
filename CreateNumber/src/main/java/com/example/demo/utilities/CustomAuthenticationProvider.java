package com.example.demo.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
    @Autowired
	private IUser user;
   
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<UserInfo> authenticatedUser = user.getUser(name, password);
        
        if(!authenticatedUser.isPresent()){
            throw new BadCredentialsException("Unauthorized Access");
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