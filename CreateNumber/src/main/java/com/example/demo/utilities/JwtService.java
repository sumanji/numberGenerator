package com.example.demo.utilities;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.IUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	 @Autowired
	private IUser user;
	 

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; 
    public String generateToken() { 
    	String userName = user.getUserName();
    	if(StringUtils.isEmpty(userName)) {
    		throw new JwtException("No user name available");
    	}
        Map<String, Object> claims = new HashMap<>(); 
        String token =  createToken(claims, userName);
        System.out.println("Token is "+ token);
        return token;
    } 
  
    private String createToken(Map<String, Object> claims, String userName) { 
        return Jwts.builder() 
                .setClaims(claims) 
                .setSubject(userName) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
    } 
  
    private Key getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(SECRET); 
        return Keys.hmacShaKeyFor(keyBytes); 
    } 
  
    public String extractUsername(String token) { 
        return extractClaim(token, Claims::getSubject); 
    } 
  
    public Date extractExpiration(String token) { 
        return extractClaim(token, Claims::getExpiration); 
    } 
  
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    } 
  
    private Claims extractAllClaims(String token) { 
        return Jwts 
                .parserBuilder() 
                .setSigningKey(getSignKey()) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
    } 
  
    public Boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date()); 
    } 
  
	
	public Boolean validateToken(String token) {
		String userLoggedInName = user.getUserName();
		final String username = extractUsername(token);
		return (username.equals(userLoggedInName) && !isTokenExpired(token));
	}
	 
  

}
