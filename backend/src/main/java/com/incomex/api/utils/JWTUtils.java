package com.incomex.api.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.incomex.api.config.Configurations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
	
	@Autowired
	private Configurations configurations;

	public String getJWTToken(String username, List<GrantedAuthority> grantedAuthorities) {

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + configurations.getExpiration()))
				.signWith(SignatureAlgorithm.HS512, configurations.getSecretKey().getBytes()).compact();

		return "Bearer " + token;
	}
}
