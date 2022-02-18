package com.server.dto.member;

import javax.validation.constraints.Pattern;

import com.server.domain.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="로그인 DTO")
public class MemberLoginDTO {
	@Schema(description="아이디",example="아이디")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{5,10}$",message="아이디는 영문과 숫자조합의 5글자이상 10글자 이하입니다.")
	private String id;
	
	@Schema(description="비밀번호",example="비밀번호")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",message="비밀번호는 영문소문자,영문대문자,숫자,특수문자를 포함한 8글자이상 15글자 이하입니다.")
	private String password;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.build();
	}
}
