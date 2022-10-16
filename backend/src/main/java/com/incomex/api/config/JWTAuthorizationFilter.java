package com.incomex.api.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
		
		@Value("${security.token.secret.header}")
		private static final String HEADER = "Authorization";
		
		@Value("${security.token.secret.prefix}")
		private static final String PREFIX  = "Bearer ";
		
		@Value("${security.token.secret.key}")
		private static final String SECRET = "Asjfwol2asf123142Ags1k23hnSA36as6f4qQ324FEsvb";
		
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
			try {
				if (existJwt(request)) {
					Claims claims = validateToken(request);
					if (claims.get("authorities") != null) {
						setUpSpringAuthentication(claims);
					} else {
						SecurityContextHolder.clearContext();
					}
				} else {
						SecurityContextHolder.clearContext();
				}
				chain.doFilter(request, response);
			} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			}
		}	

		Claims validateToken(HttpServletRequest request) {
			String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
			return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
		}
		private void setUpSpringAuthentication(Claims claims) {
			@SuppressWarnings("unchecked")
			List<String> authorities =  (List<String>) claims.get("authorities");

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
					authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			SecurityContextHolder.getContext().setAuthentication(auth);

		}

		private boolean existJwt(HttpServletRequest request) {
			String authenticationHeader = request.getHeader(HEADER);
			return authenticationHeader != null && authenticationHeader.startsWith(PREFIX) ;
		}
		
		public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		    String token = request.getHeader(HEADER);
		    if (token != null) {
		        String user;
		        try {
		            user = Jwts.parser()
		                    .setSigningKey(SECRET.getBytes())
		                    .parseClaimsJws(token.replace(PREFIX, ""))
		                    .getBody()
		                    .getSubject();
		        } catch (SignatureException e) {
		            return null;
		        }

		        if (user != null) {
		             return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		        }

		        return null;
		    }
		    return null;
		}
}