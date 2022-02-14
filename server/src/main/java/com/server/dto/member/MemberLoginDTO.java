package com.server.dto.member;

import javax.validation.constraints.Pattern;

import com.server.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(description="이 DTO는 로그인 DTO입니다.")
public class MemberLoginDTO {
	//@ApiModelProperty(notes="아이디")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{5,10}$",message="아이디는 영문과 숫자조합의 5글자이상 10글자 이하입니다.")
	private String id;
	
	//@ApiModelProperty(notes="비밀번호")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",message="비밀번호는 영문소문자,영문대문자,숫자,특수문자를 포함한 8글자이상 15글자 이하입니다.")
	private String password;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.build();
	}
}
