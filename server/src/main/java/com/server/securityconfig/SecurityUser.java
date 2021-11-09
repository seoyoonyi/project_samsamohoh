package com.server.securityconfig;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.server.domain.Member;

public class SecurityUser extends User {
	
	public SecurityUser(Member member) {
		super(member.getId(),member.getPassword(),AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
