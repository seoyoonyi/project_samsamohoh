package com.server.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Schema(description="예외 발생 응답 DTO")
public class ApiException<T> {
	@Schema(description="예외 코드",example="예외 코드")
	private String code;
	
	@Schema(description="예외 메시지",example="예외 메시지")
	private T message;
	
}
