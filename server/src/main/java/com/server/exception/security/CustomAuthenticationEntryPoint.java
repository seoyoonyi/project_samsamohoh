package com.server.exception.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.server.exception.ExceptionCode;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		String exception = (String) request.getAttribute("exception");

		if (exception.equals(ExceptionCode.UNKNOWN_ERROR.code)) {
			setResponse(response, ExceptionCode.UNKNOWN_ERROR);

		} else if (exception.equals(ExceptionCode.WRONG_TYPE_TOKEN.code)) {
			setResponse(response, ExceptionCode.WRONG_TYPE_TOKEN);

		} else if (exception.equals(ExceptionCode.EXPIRED_TOKEN.code)) {
			setResponse(response, ExceptionCode.EXPIRED_TOKEN);

		} else if (exception.equals(ExceptionCode.UNSUPPORTED_TOKEN.code)) {
			setResponse(response, ExceptionCode.UNSUPPORTED_TOKEN);

		} else if (exception.equals(ExceptionCode.DO_NOT_EXIST_TOKEN_EXCEPTION.code)) {
			setResponse(response, ExceptionCode.DO_NOT_EXIST_TOKEN_EXCEPTION);

		} else {
			setResponse(response, ExceptionCode.WRONG_TOKEN);
		}

	}

	private void setResponse(HttpServletResponse response, ExceptionCode exceptionCode)
			throws IOException, JSONException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		JSONObject responseJson = new JSONObject();
		responseJson.put("code", exceptionCode.code);
		responseJson.put("message", exceptionCode.message);
		response.getWriter().print(responseJson);
	}

}
