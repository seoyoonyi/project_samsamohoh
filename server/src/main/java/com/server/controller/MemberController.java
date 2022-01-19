package com.server.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.domain.Member;
import com.server.dto.member.MemberJoinDTO;
import com.server.dto.member.MemberLoginDTO;
import com.server.dto.member.SearchMemberDTO;
import com.server.dto.member.UpdateMemberDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.responsecode.StatusCode;
import com.server.securityconfig.TokenProvider;
import com.server.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/auth")
@Api(description = "회원 관련 REST API")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	TokenProvider tokenProvider;

	@ApiOperation(value = "로그인", notes = "아이디와,패스워드를 이용하여 자격증명을 얻는 API")
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody @Valid MemberLoginDTO memberLoginDTO) {

		Member findMember = memberService.signin(memberLoginDTO);

		HashMap<String, String> loginInfor = new HashMap<String, String>();
		loginInfor.put("nickName", findMember.getNickName());
		loginInfor.put("token", tokenProvider.create(findMember));
		SuccessfulResponseDTO<HashMap<String, String>> response = SuccessfulResponseDTO
				.<HashMap<String, String>>builder().code(StatusCode.STATUS_OK).message("로그인 성공").data(loginInfor)
				.build();
		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "회원가입", notes = "이메일,아이디,비밀번호를 입력하여 회원가입 API")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid MemberJoinDTO memberJoinDTO) {

		memberService.signup(memberJoinDTO.toEntity());

		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("회원가입 성공").build();

		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "회원의 이미지,닉네임 수정", notes = "초기 회원가입후 사용자의 프로필,닉네임을 설정하는 API")
	@PutMapping("/member/{id}")
	public ResponseEntity<?> updateMember(@PathVariable String id, @RequestPart MultipartFile file,
			@RequestPart String userInfor) {

		ObjectMapper om = new ObjectMapper();

		try {
			UpdateMemberDTO dto = om.readValue(userInfor, UpdateMemberDTO.class);
			memberService.updateMember(id, file, dto);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}


		SimpleResponseDTO response = SimpleResponseDTO.builder().code(1).message("회원정보 수정 성공").build();

		return ResponseEntity.ok().body(response);

	}

	@ApiOperation(value = "회원정보 불러오기")
	@GetMapping("/member")
	public ResponseEntity<?> getMember(@ApiIgnore @AuthenticationPrincipal String id) {

		SearchMemberDTO dto = new SearchMemberDTO(memberService.getMember(id));
		SuccessfulResponseDTO<SearchMemberDTO> response = SuccessfulResponseDTO.<SearchMemberDTO>builder().code(1)
				.message("회원조회 성공").data(dto).build();
		return ResponseEntity.ok().body(response);

	}
}
