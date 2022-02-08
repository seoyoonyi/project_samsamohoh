package com.server.exception.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.server.exception.ExceptionCode;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		setResponse(response,ExceptionCode.PERMISSION_DENIED);
		
	}
	
	private void setResponse(HttpServletResponse response,ExceptionCode exceptionCode) throws IOException, JSONException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		JSONObject responseJson = new JSONObject();
		responseJson.put("code",exceptionCode.code);
		responseJson.put("message", exceptionCode.message);
		response.getWriter().print(responseJson);
		
	}
	
	

}
