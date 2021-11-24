package com.server.responsecode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailResponse {
	private int code;
	private String message;
	
	public FailResponse(final int code,String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
