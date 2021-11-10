package com.server.service;

import java.util.Optional;

import com.server.domain.Member;


public interface MemberService {
	public Optional<Member> getMember(Member member);
	
	public void saveMember(Member member);
}
