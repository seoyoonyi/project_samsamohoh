package com.server.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.responsecode.FailResponse;
import com.server.responsecode.ResponseMessage;
import com.server.responsecode.StatusCode;
import com.server.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

	@PostMapping("/member")
	public ResponseEntity member(@Valid @RequestBody Member member, Errors errors) {

		if (errors.hasErrors()) {

			return new ResponseEntity(
					new FailResponse(StatusCode.RESOURSE_CREATE_FAILED, errors.getFieldError().getDefaultMessage()),
					HttpStatus.OK);
		}

		Optional<Member> option = memberService.getMember(member);
		if (option.isPresent()) {
			return new ResponseEntity(
					new FailResponse(StatusCode.DUPLICATED_ID, ResponseMessage.DUPLICATED_ID),
					HttpStatus.OK);
		}
		memberService.saveMember(member);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("code", StatusCode.RESOURSE_CREATE_SUCCESS + "");
		response.put("message", ResponseMessage.CREATED_USER);
		return new ResponseEntity(response, HttpStatus.CREATED);

	}
}
