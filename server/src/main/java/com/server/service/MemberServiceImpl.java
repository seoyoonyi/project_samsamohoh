package com.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.domain.Member;
import com.server.persistence.MemberRepository;

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
	
	public Member updateMember(String id , Member member) {
		Member findMember = memberRepo.findById(id).get();
		findMember.setName(member.getName());
		findMember.setEmail(member.getEmail());
		return memberRepo.save(findMember);
		
	}
	
	
}
