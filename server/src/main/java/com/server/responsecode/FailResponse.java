package com.server.responsecode;

import lombok.Data;

@Data
public class FailResponse {
	private int code;
	private String message;
	
	public FailResponse(final int code,final String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
