package com.server.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.aws.S3ImageFileUploader;
import com.server.domain.Member;
import com.server.dto.member.MemberLoginDTO;
import com.server.exception.AlreadyExistMemberException;
import com.server.exception.LoginFailedException;
import com.server.exception.MemberNotExistException;
import com.server.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepo;

	@Autowired
	S3ImageFileUploader s3ImageFileUploader;

	public Member getMember(String id) {
		Optional<Member> option = memberRepo.findById(id);
		if (option.isEmpty()) {
			throw new MemberNotExistException();
		} else {
			return option.get();
		}

	}

	public Member saveMember(Member member) {

		Optional<Member> option = memberRepo.findById(member.getId());
		if (option.isPresent()) {
			throw new AlreadyExistMemberException();
		} else {
			return memberRepo.save(member);
		}
	}

	public Member updateMember(String id, MultipartFile file, String nickName) throws IOException {

		Optional<Member> option = memberRepo.findById(id);
		if (!option.isPresent()) {
			throw new MemberNotExistException();
		} else {
			Member findMember = option.get();
			findMember.setImagePath(s3ImageFileUploader.uplode(file));
			findMember.setNickName(nickName);
			return memberRepo.save(findMember);
		}

	}
	
	public Member signin(MemberLoginDTO dto) {
		
		Optional<Member> findMember = memberRepo.findById(dto.getId());
		
		if(findMember.isEmpty()) {
			throw new LoginFailedException();
		}else {
			if(findMember.get().getPassword().equals(dto.getPassword())) {
				return findMember.get();
			}else {
				throw new LoginFailedException();
			}
		}
	}

}
