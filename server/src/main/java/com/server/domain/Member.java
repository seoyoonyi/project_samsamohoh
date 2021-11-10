package com.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member {
	@Id
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String id;
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	private String password;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(insertable=false,columnDefinition="varchar(15) default 'ROLE_MEMBER'")
	private Role role;
	
	@Column(insertable=false,columnDefinition = "boolean default 1")
	private boolean enabled;

}
