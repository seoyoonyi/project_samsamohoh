package com.server.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.server.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginDto {
	//@NotBlank(message="아이디는 필수 입력값입니다.")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{5,10}$",message="아이디는 영문과 숫자조합의 5글자이상 10글자 이하입니다.")
	private String id;
	
	//@NotBlank(message="비밀번호는 필수 입력값입니다.")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",message="비밀번호는 영문소문자,영문대문자,숫자,특수문자를 포함한 8글자이상 15글자 이하입니다.")
	private String password;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.build();
	}
}
