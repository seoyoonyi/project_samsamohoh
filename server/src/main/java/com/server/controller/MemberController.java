package com.server.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.domain.Member;
import com.server.dto.member.MemberJoinDTO;
import com.server.dto.member.MemberLoginDTO;
import com.server.dto.member.UpdateMemberDTO;
import com.server.dto.response.FailedResponseDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.responsecode.StatusCode;
import com.server.securityconfig.TokenProvider;
import com.server.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@Api(description = "회원 관련 REST API")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	TokenProvider tokenProvider;

	@ApiOperation(value = "로그인")
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody @Valid MemberLoginDTO memberLoginDTO) {

		Member findMember = memberService.getMember(memberLoginDTO.getId());

		if (findMember != null) {
			HashMap<String, String> loginInfor = new HashMap<String, String>();
			loginInfor.put("nickName", findMember.getNickName());
			loginInfor.put("token", tokenProvider.create(findMember));
			SuccessfulResponseDTO<HashMap<String, String>> response = SuccessfulResponseDTO
					.<HashMap<String, String>>builder().code(StatusCode.STATUS_OK).message("로그인 성공").data(loginInfor)
					.build();
			return ResponseEntity.ok().body(response);
		} else {

			FailedResponseDTO<String> response = FailedResponseDTO.<String>builder().code(StatusCode.STATUS_FAIL)
					.message("로그인 실패").build();

			return ResponseEntity.ok().body(response);

		}

	}

	@ApiOperation(value = "회원가입")
	@PostMapping("/signup")
	public ResponseEntity<?> member (@RequestBody @Valid MemberJoinDTO memberJoinDTO) {

		memberService.saveMember(memberJoinDTO.toEntity());

		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("회원가입 성공").build();

		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "회원정보 수정")
	@PutMapping("/member/{id}")
	public ResponseEntity<?> updateMember(@PathVariable String id, @RequestPart MultipartFile file,
			@RequestPart UpdateMemberDTO dto) {

		System.out.println(dto.getNickName());

		try {
			memberService.updateMember(id, file, dto.getNickName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("회원정보 수정 성공").build();

		return ResponseEntity.ok().body(response);

	}
}
