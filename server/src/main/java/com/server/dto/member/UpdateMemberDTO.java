package com.server.dto.member;

import javax.validation.constraints.Pattern;

import com.server.domain.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="회원 정보 업데이트 DTO")
public class UpdateMemberDTO {
	
	@Schema(description="닉네임",example="닉네임")
	@Pattern(regexp = "^[가-힣]{2,8}$", message = "닉네임은 2글자이상 8글자이하의 자음과 모음이 갖춰진 한글만 가능합니다.")
	private String nickName;
	
	@Schema(description="이메일",example="이메일")
	@Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	public  Member toEntity() {
		return Member.builder()
				.nickName(nickName)
				.email(email)
				.build();
	}

}
