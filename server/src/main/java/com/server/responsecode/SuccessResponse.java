package com.server.responsecode;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SuccessResponse<T> {
	private int code;
	private String message;
	private T data;
	
	public SuccessResponse(final int code, final String responseMessage,T data) {
		this.code = code;
		this.message = responseMessage;
		this.data = data;
	}
	
	
}
