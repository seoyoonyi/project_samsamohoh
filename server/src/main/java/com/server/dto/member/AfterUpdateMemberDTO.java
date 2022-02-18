package com.server.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="개인정보 수정 DTO")
public class AfterUpdateMemberDTO {
	@Schema(description="이메일",example="이메일")
	String email;
	@Schema(description="닉네임",example="닉네임")
	String nickName;
	@Schema(description="패스워드",example="패스워드")
	String password;
	
	
}
