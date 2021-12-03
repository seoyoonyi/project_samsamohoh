package com.server.dto;

import com.server.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinDto {
	
	private String id;
	private String password;
	private String name;
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
