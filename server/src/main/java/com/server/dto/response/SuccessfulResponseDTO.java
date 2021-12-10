package com.server.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@Getter
@Setter
public class SuccessfulResponseDTO<T> {
	private int code;
	private String message;
	private T data;
	
	public SuccessfulResponseDTO(final int code, final String responseMessage,T data) {
		this.code = code;
		this.message = responseMessage;
		this.data = data;
	}
	
	
}
