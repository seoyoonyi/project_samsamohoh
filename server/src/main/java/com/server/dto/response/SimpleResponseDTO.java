package com.server.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

@Schema(description="성공시 응답 DTO")
public class SimpleResponseDTO {
	@Schema(description="응답코드",example="응답코드")
	String code;
	@Schema(description="응답메시지",example="응답메시지")
	String message;
}
