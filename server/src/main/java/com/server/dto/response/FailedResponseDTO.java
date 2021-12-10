package com.server.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FailedResponseDTO<T> {
	private int code;
	private T message;
	
	public FailedResponseDTO(final int code,T message) {
		this.code = code;
		this.message = message;
	}
	
	
}
