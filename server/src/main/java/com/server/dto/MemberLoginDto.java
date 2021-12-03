package com.server.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.server.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginDto {
	@NotBlank(message="아이디는 필수 입력값입니다.")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{5,10}$",message="아이디는 영문과 숫자조합의 5~10글자입니다.")
	private String id;
	
	@NotBlank(message="비밀번호는 필수 입력값입니다.")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",message="비밀번호는 영문 소문자,영문 대문자,숫자,특수문자를 포함한 8~15사이의 문자여야합니다.")
	private String password;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.build();
	}
}
