package com.server.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.aws.S3ImageFileUploader;
import com.server.domain.Member;
import com.server.dto.member.MemberLoginDTO;
import com.server.dto.member.UpdateMemberDTO;
import com.server.exception.member.AlreadyExistMemberException;
import com.server.exception.member.IdDismatchException;
import com.server.exception.member.InvalidMemberException;
import com.server.exception.member.MemberNotExistException;
import com.server.exception.member.NickNameValidationException;
import com.server.exception.member.PasswordDismatchException;
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
			if(option.get().isEnabled()==false) {
				throw new InvalidMemberException();
			}
			
			throw new MemberNotExistException();
		} else {
			return option.get();
		}

	}

	public Member signup(Member member) {

		Optional<Member> option = memberRepo.findById(member.getId());
		if (option.isPresent()) {
			throw new AlreadyExistMemberException();
		} else {
			return memberRepo.save(member);
		}
	}

	public Member updateMember(String id, MultipartFile file, UpdateMemberDTO dto) throws IOException {
		
		if(!dto.getNickName().matches("^[가-힣]{2,8}$")) {
			throw new NickNameValidationException();
		}

		Optional<Member> option = memberRepo.findById(id);
		if (!option.isPresent()) {
			if(option.get().isEnabled()==false) {
				throw new InvalidMemberException();
			}
			throw new MemberNotExistException();
		} else {
			Member findMember = option.get();
			findMember.setImagePath(s3ImageFileUploader.uplode(file));
			findMember.setNickName(dto.getNickName());
			return memberRepo.save(findMember);
		}

	}

	public Member signin(MemberLoginDTO dto) {

		Optional<Member> findMember = memberRepo.findById(dto.getId());

		if (findMember.isEmpty()) {
			
			throw new IdDismatchException();
		} else {
			if (findMember.get().getPassword().equals(dto.getPassword())) {
				if (findMember.get().isEnabled() == false) {
					throw new InvalidMemberException();
				} else {
					return findMember.get();
				}
			} else {
				
				throw new PasswordDismatchException();
			}
		}
	}
	
	
	public void deleteMember(String id) {
		
		Member findMember = memberRepo.findById(id).get();
		findMember.setEnabled(false);
		memberRepo.save(findMember);
		
	}
	

}
