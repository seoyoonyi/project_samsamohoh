package com.server.responsecode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SuccessResponse<T> {
	private int code;
	private String message;
	private T data;
	
	public SuccessResponse(final int code, final String responseMessage) {
		this.code = code;
		this.message = responseMessage;
		this.data = null;
	}
	
	public static<T> SuccessResponse<T> res(final int code,final String responseMessage){
		return res(code,responseMessage,null);
	}
	
	public static<T> SuccessResponse<T> res(final int code,final String responseMessage,final T t){
		return SuccessResponse.<T>builder()
				.data(t)
				.code(code)
				.message(responseMessage)
				.build();
	}
}
