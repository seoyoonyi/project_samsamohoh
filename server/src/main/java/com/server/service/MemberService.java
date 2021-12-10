package com.server.service;

import java.util.Optional;

import com.server.domain.Member;


public interface MemberService {

	public Optional<Member> getMember(String id);

	public void saveMember(Member member);

	public Member updateMember(String id, Member member);
}
