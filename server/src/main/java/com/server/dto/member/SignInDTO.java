package com.server.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(description="로그인 성공시 반환 DTO")
public class SignInDTO {
	//@ApiModelProperty(value = "토큰",name="token",example ="토큰")
	String token;
	//@ApiModelProperty(value = "닉네임",name="nickName",example ="닉네임")
	String nickName;
}
