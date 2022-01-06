package com.server.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.server.domain.Member;


public interface MemberService {

	public Member getMember(String id);

	public Member saveMember(Member member);

	public Member updateMember(String id, MultipartFile file, String nickName)throws IOException;
}
