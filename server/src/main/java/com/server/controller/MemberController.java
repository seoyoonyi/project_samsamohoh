package com.server.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.domain.Member;
import com.server.dto.member.AfterUpdateMemberDTO;
import com.server.dto.member.MemberJoinDTO;
import com.server.dto.member.MemberLoginDTO;
import com.server.dto.member.SearchMemberDTO;
import com.server.dto.member.SignInDTO;
import com.server.dto.member.UpdateMemberDTO;
import com.server.dto.response.SimpleResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.exception.ApiException;
import com.server.securityconfig.TokenProvider;
import com.server.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Member", description = "로그인 및 회원 관련 API")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	TokenProvider tokenProvider;

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "로그인 성공"),
			@ApiResponse(responseCode = "400", description = "예외 발생", content = @Content(schema = @Schema(implementation = ApiException.class))) })
	@Operation(summary = "로그인", description = "사용자 아이디와 비밀번호를 입력")
	@PostMapping("/auth/signin")
	public ResponseEntity<SuccessfulResponseDTO<SignInDTO>> signin(
			@Parameter(description = "아이디와 비밀번호 입력",name="memberLoginDTO") @RequestBody @Valid MemberLoginDTO memberLoginDTO) {

		Member findMember = memberService.signin(memberLoginDTO);

		SignInDTO dto = new SignInDTO();
		dto.setToken(tokenProvider.create(findMember));
		dto.setNickName(findMember.getNickName());
		SuccessfulResponseDTO<SignInDTO> response = SuccessfulResponseDTO.<SignInDTO>builder().code("1")
				.message("로그인 성공").data(dto).build();
		return ResponseEntity.ok().body(response);

	}
	
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "회원가입 성공"),
		@ApiResponse(responseCode = "400", description = "예외 발생", content = @Content(schema = @Schema(implementation = ApiException.class))) })
	@Operation(summary = "회원가입", description = "사용자 아이디,비밀번호,이메일을 입력하여 회원가입")
	
	@PostMapping("/auth/signup")
	public ResponseEntity<SimpleResponseDTO> signup(
			@Parameter(description = "사용자 아이디,비밀번호,이메일 입력") @RequestBody @Valid MemberJoinDTO memberJoinDTO) {

		memberService.signup(memberJoinDTO.toEntity());

		SimpleResponseDTO response = SimpleResponseDTO.builder().code("1").message("회원가입 성공").build();

		return ResponseEntity.ok().body(response);

	}
	
	
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 프로필 수정 성공"),
		@ApiResponse(responseCode = "400", description = "예외 발생", content = @Content(schema = @Schema(implementation = ApiException.class))) })
	@Operation(summary = "사용자 프로필 변경", description = "사용자 이미지파일과 닉네임을 입력하여 프로필변경")
	@Parameters(
				
			)
	@PutMapping(path = "initialization/member",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<SimpleResponseDTO> updateMemberProfile(
			@Parameter(description = "Bearer {token}",in=ParameterIn.HEADER)@RequestHeader("x-api-key") String token,
			@Parameter(description = "이미지 파일") @RequestPart MultipartFile file,
			@Parameter(description = "닉네임") @RequestPart String userInfor) {
		
		System.out.println(token);
		ObjectMapper om = new ObjectMapper();

		try {
			String memberId = tokenProvider.getMemberId(token.substring(7));
			UpdateMemberDTO dto = om.readValue(userInfor, UpdateMemberDTO.class);
			memberService.updateProfileMember(memberId, file, dto);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SimpleResponseDTO response = SimpleResponseDTO.builder().code("1").message("회원정보 수정 성공").build();

		return ResponseEntity.ok().body(response);

	}
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "사용자 정보 수정 성공"),
		@ApiResponse(responseCode = "400", description = "예외 발생", content = @Content(schema = @Schema(implementation = ApiException.class))) })
	@Operation(summary = "사용자 정보 변경", description = "사용자 이메일,비밀번호 변경")
	@PutMapping(path = "/member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<SimpleResponseDTO> updateMember(
			@Parameter(description = "Bearer {token}") @RequestHeader(value = "x-api-key") String token,
			@RequestPart MultipartFile file, @RequestPart String userInfo) {
		ObjectMapper om = new ObjectMapper();

		try {
			String memberId = tokenProvider.getMemberId(token.substring(7));
			AfterUpdateMemberDTO dto = om.readValue(userInfo, AfterUpdateMemberDTO.class);
			memberService.updateMember(memberId, file, dto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleResponseDTO response = SimpleResponseDTO.builder().code("1").message("회원정보 수정 성공").build();
		return ResponseEntity.ok().body(response);

	}
	
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "사용자정보 조회 성공"),
		@ApiResponse(responseCode = "400", description = "예외 발생", content = @Content(schema = @Schema(implementation = ApiException.class))) })

	@Operation(summary = "사용자 정보 가져오기", description = "사용자의 아이디,이메일,이미지 파일 경로,닉네임,아이디 생성일,권한을 조회")
	@GetMapping("/member")
	public ResponseEntity<SuccessfulResponseDTO<SearchMemberDTO>> getMember(
			@Parameter(description = "Bearer {token}") @RequestHeader(value = "x-api-key") String token) {
		 
		String memberId = tokenProvider.getMemberId(token.substring(7));
		SearchMemberDTO dto = new SearchMemberDTO(memberService.getMember(memberId));
		SuccessfulResponseDTO<SearchMemberDTO> response = SuccessfulResponseDTO.<SearchMemberDTO>builder().code("1")
				.message("회원조회 성공").data(dto).build();
		return ResponseEntity.ok().body(response);

	}
	
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "사용자계정 삭제 성공"),
		@ApiResponse(responseCode = "400", description = "예외 발생", content = @Content(schema = @Schema(implementation = ApiException.class))) })
	@Operation(summary = "사용자 계정 삭제", description = "토큰값을 이용하여 사용자 계정 삭제")
	@DeleteMapping("/member")
	public ResponseEntity<SimpleResponseDTO> deleteMember(
			@Parameter(description = "Bearer {token}") @RequestHeader(value = "x-api-key") String token) {
		String memberId = tokenProvider.getMemberId(token.substring(7));
		memberService.deleteMember(memberId);
		SimpleResponseDTO response = SimpleResponseDTO.builder().code("1").message("회원정보 삭제 성공").build();

		return ResponseEntity.ok().body(response);

	}
}
