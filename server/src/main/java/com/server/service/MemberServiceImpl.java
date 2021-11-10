package com.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.domain.Member;
import com.server.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepo;
	
	public Optional<Member> getMember(Member member) {
		System.out.println(member.getId());
		
		return memberRepo.findById(member.getId());
	}

	
	public void saveMember(Member member) {
		memberRepo.save(member);
		
	}
	
	
}
