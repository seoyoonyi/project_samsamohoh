package com.server.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@Getter
@Setter
@Schema(description="성공시 데이터를 포함한 응답 DTO")
public class SuccessfulResponseDTO<T> {
	@Schema(description="응답코드",example="응답코드")
	private String code;
	@Schema(description="응답 메시지",example="응답메시지")
	private String message;
	@Schema(description="응답 데이터")
	private T data;
	
	public SuccessfulResponseDTO(String code, String responseMessage,T data) {
		this.code = code;
		this.message = responseMessage;
		this.data = data;
	}
	
	
}
