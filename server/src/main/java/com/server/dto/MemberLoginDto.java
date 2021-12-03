package com.server.dto;

import com.server.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginDto {
	private String id;
	private String password;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.build();
	}
}
