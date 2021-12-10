package com.server.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SimpleResponseDTO {
	int code;
	String message;
}
