package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.dto.MemberJoinDto;
import com.server.responsecode.FailResponse;
import com.server.responsecode.ResponseMessage;
import com.server.responsecode.StatusCode;
import com.server.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

	@PostMapping("/member")
	public ResponseEntity member(@Valid @RequestBody MemberJoinDto memberJoinDto, Errors errors) {

		if (errors.hasErrors()) {
			List<FieldError> errorList = errors.getFieldErrors();
			List<String> errorMessageList = new ArrayList<String>();
			for(FieldError fe : errorList) {
				errorMessageList.add(fe.getDefaultMessage());
			}
			return new ResponseEntity(
					new FailResponse(StatusCode.RESOURSE_CREATE_FAILED, errorMessageList),
					HttpStatus.OK);
		}

		Optional<Member> option = memberService.getMember(memberJoinDto.getId());
		if (option.isPresent()) {
			return new ResponseEntity(new FailResponse(StatusCode.DUPLICATED_ID, ResponseMessage.DUPLICATED_ID),
					HttpStatus.OK);
		}
		memberService.saveMember(memberJoinDto.toEntity());
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("code", StatusCode.RESOURSE_CREATE_SUCCESS + "");
		response.put("message", ResponseMessage.CREATED_USER);
		return new ResponseEntity(response, HttpStatus.CREATED);

	}
}
