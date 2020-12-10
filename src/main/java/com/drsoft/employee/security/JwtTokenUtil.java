package com.drsoft.employee.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.drsoft.employee.entity.EmployeeEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//for performing jwt creation and validation

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${token.expire.time}")
	private Long JWT_TOKEN_VALIDITY;

	public String getUsernameFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(EmployeeEntity user) {
		return doGenerateToken(user);
	}

	private String doGenerateToken(EmployeeEntity user) {
		Claims claims = Jwts.claims().setSubject(user.getName());
		return Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String validateToken(String token) {
		  final String username = getUsernameFromToken(token);
	     if(username == null) return "username is null in token";
	        return username;
	}
}
