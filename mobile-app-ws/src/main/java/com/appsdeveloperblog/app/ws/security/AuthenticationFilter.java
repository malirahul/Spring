package com.appsdeveloperblog.app.ws.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdeveloperblog.app.ws.ui.model.request.UserLoggingRequestModel;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
		UserLoggingRequestModel creds = new ObjectMapper()
					.readValue(request.getInputStream(), 
							UserLoggingRequestModel.class);
		
		return authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken
						(creds.getEmail(),
								creds.getPassword(),
								new ArrayList<>()));

		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override 
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		String userName = ((User)authResult.getPrincipal()).getUsername();
		//String tokenSecret = new SecurityConstant().getTokenSecret();
		
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() +SecurityConstant.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512,SecurityConstant.TOKEN_SECRET).compact();
		
	response.addHeader(SecurityConstant
			.HEADER_STRING, 
			SecurityConstant.TOKEN_PREFIX + token);
		
	}
}
