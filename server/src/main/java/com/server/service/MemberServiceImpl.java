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
	
	public Optional<Member> getMember(String id) {
		
		
		return memberRepo.findById(id);
	}

	
	public void saveMember(Member member) {
		memberRepo.save(member);
		
	}
	
	
}
