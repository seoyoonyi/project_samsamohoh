package com.server.dto.member;

import java.util.Date;

import com.server.domain.Member;
import com.server.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemberDTO {
	String id;
	String email;
	String imagePath;
	String nickName;
	Date regisDate;
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
