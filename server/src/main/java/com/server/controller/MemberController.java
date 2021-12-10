package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.dto.member.MemberJoinDTO;
import com.server.dto.member.UpdateMemberDTO;
import com.server.dto.response.FailedResponseDTO;
import com.server.responsecode.ResponseMessage;
import com.server.responsecode.StatusCode;
import com.server.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/members")
@Api(description="회원 관련 REST API")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@ApiOperation(value="회원가입")
	@PostMapping("/member")
	public ResponseEntity<?> member(@Valid @RequestBody MemberJoinDTO memberJoinDTO,@ApiIgnore Errors errors) {

		if (errors.hasErrors()) {
			List<FieldError> errorList = errors.getFieldErrors();
			List<String> errorMessageList = new ArrayList<String>();
			for (FieldError fe : errorList) {
				errorMessageList.add(fe.getDefaultMessage());
			}

			FailedResponseDTO<List<String>> response = FailedResponseDTO.<List<String>>builder()
					.code(StatusCode.RESOURSE_CREATE_FAILED).message(errorMessageList).build();

			return ResponseEntity.ok().body(response);

		}
		

		Optional<Member> option = memberService.getMember(memberJoinDTO.getId());
		if (option.isPresent()) {
			
			FailedResponseDTO<String> response = FailedResponseDTO.<String>builder()
					.code(StatusCode.DUPLICATED_ID).message("중복되는 아이디입니다.").build();
			
			return ResponseEntity.ok().body(response);
		}
		
		
		memberService.saveMember(memberJoinDTO.toEntity());
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("code", StatusCode.RESOURSE_CREATE_SUCCESS + "");
		response.put("message", ResponseMessage.CREATED_USER);
		return ResponseEntity.ok().body(response);

	}
	
	@ApiOperation(value="회원정보 수정")
	@PutMapping("{id}")
	public ResponseEntity<?> updateMember(@PathVariable String id,@Valid@RequestBody UpdateMemberDTO updateMemberDTO,@ApiIgnore Errors errors){
		
		if(errors.hasErrors()) {
			List<FieldError> errorList = errors.getFieldErrors();
			List<String> errorMessageList = new ArrayList<String>();
			for(FieldError fe : errorList) {
				errorMessageList.add(fe.getDefaultMessage());
			}
			FailedResponseDTO<List<String>> response = FailedResponseDTO.<List<String>>builder().code(-1).message(errorMessageList).build();
			return ResponseEntity.ok().body(response);
		}
		
		Member member = updateMemberDTO.toEntity();
		memberService.updateMember(id,member);
		HashMap<String,String> response = new HashMap<String,String>();
		response.put("code", "1");
		response.put("message","회원정보 수정 성공");
		
		return ResponseEntity.ok().body(response);
		
		
		
		
	}
}
