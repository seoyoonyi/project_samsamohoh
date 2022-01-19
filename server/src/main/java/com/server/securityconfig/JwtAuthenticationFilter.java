package com.server.securityconfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.server.exception.ExceptionCode;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;

	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException,IOException{
		try {
			String token = parseBearerToken(request);
			if(token != null) {
				Authentication authentication =  tokenProvider.getAuthnetication(token);
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);
				System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
			}else {
				request.setAttribute("exception", ExceptionCode.DO_NOT_EXIST_TOKEN_EXCEPTION.code);
			}
						
		}catch( MalformedJwtException e) {
			request.setAttribute("exception",ExceptionCode.WRONG_TYPE_TOKEN.code);
		}catch(ExpiredJwtException e) {
			request.setAttribute("exception", ExceptionCode.EXPIRED_TOKEN.code);
		}catch(UnsupportedJwtException e) {
			request.setAttribute("exception", ExceptionCode.UNSUPPORTED_TOKEN.code);
		}catch(IllegalArgumentException e){
			request.setAttribute("exception", ExceptionCode.WRONG_TOKEN.code);
		}catch(SignatureException e) {
			request.setAttribute("exception", ExceptionCode.WRONG_TOKEN.code);
		}catch(Exception e) {
			request.setAttribute("exception", ExceptionCode.UNKNOWN_ERROR.code);
		}
		
		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		return null;
	}
}
