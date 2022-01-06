package com.server.dto.member;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.server.domain.Member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description="이 DTO는 회원가입을 위한 DTO입니다.")
public class MemberJoinDTO {
	
	@ApiModelProperty(notes="아이디")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{5,10}$",message="아이디는 영문과 숫자조합의 5글자이상 10글자 이하입니다.")
	private String id;
	
	@ApiModelProperty(notes="비밀번호")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",message="비밀번호는 영문소문자,영문대문자,숫자,특수문자를 포함한 8글자이상 15글자 이하입니다.")
	private String password;
	
	/*@ApiModelProperty(notes="닉네임")
	@Pattern(regexp="^[가-힣]{2,8}$",message="이름은 2글자이상 8글자이하의 자음과 모음이 갖춰진 한글만 가능합니다.")
	private String name;*/
	
	@ApiModelProperty(notes="이메일")
	@Size(min=1,max=20,message="이메일은 20글자 이하입니다.")
	@Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",message="이메일 형식이 올바르지 않습니다.")
	private String email;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				//.nickName(name)
				.email(email)
				.build();
	}
	
}
