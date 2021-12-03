package com.server.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.server.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinDto {
	
	@NotBlank(message="아이디는 필수 입력값입니다.")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{5,10}$",message="아이디는 영문과 숫자조합의 5~10글자만 가능합니다.")
	private String id;
	
	@NotBlank(message="비밀번호는 필수 입력값입니다.")
	@Pattern(regexp="^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",message="비밀번호는 영문,숫자,특수문자를 포함한 8~15사이의 문자여야합니다.")
	private String password;
	
	@NotBlank(message="이름은 필수 입력값입니다.")
	@Pattern(regexp="^[가-힣]{2,8}$",message="이름은 2~8글자의 자음과 모음이 갖춰진 한글만 가능합니다.")
	private String name;
	
	@NotBlank(message="이메일은 필수 입력값입니다.")
	@Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",message="이메일 형식이 올바르지 않습니다.")
	private String email;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.name(name)
				.email(email)
				.build();
	}
	
}
