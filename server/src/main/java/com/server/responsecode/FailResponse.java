package com.server.responsecode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailResponse<T> {
	private int code;
	private T message;
	
	public FailResponse(final int code,T message) {
		this.code = code;
		this.message = message;
	}
	
	
}
