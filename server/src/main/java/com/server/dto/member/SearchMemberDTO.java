package com.server.dto.member;

import java.util.Date;

import com.server.domain.Member;
import com.server.domain.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="사용자 정보 조회 DTO")
public class SearchMemberDTO {
	@Schema(description="아이디",example="아이디")
	String id;
	@Schema(description="이메일",example="이메일")
	String email;
	@Schema(description="이미지 파일 저장 경로",example="이미지 파일 저장 경로")
	String imagePath;
	@Schema(description="닉네임",example="닉네임")
	String nickName;
	@Schema(description="계정 생성 날짜",example="계정 생성 날짜")
	Date regisDate;
	@Schema(description="권한",example="권한")
	Role role;
	
	
	public SearchMemberDTO(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.imagePath = member.getImagePath();
		this.nickName = member.getNickName();
		this.regisDate = member.getRegisDate();
		this.role = member.getRole();
	}
	
}
