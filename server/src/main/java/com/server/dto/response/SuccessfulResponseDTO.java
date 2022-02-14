package com.server.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@Getter
@Setter
//@ApiModel("성공시 응답 DTO")
public class SuccessfulResponseDTO<T> {
	//@ApiModelProperty(name="코드",value="코드",example ="응답코드")
	private int code;
	//@ApiModelProperty(example ="응답코드")
	private String message;
	
	private T data;
	
	public SuccessfulResponseDTO(final int code, final String responseMessage,T data) {
		this.code = code;
		this.message = responseMessage;
		this.data = data;
	}
	
	
}
