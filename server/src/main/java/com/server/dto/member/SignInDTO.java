package com.server.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="로그인 성공시 반환 DTO")
public class SignInDTO {
	@Schema(description="토큰",example="토큰")
	String token;
	@Schema(description="닉네임",example="닉네임")
	String nickName;
}
