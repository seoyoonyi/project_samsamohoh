package com.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.domain.Member;
import com.server.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepo;
	
	public Member getMember(Member member) {
		return memberRepo.findById(member.getId()).get();
	}
}
