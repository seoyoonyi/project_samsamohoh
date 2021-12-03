package com.server.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.dto.MemberLoginDto;
import com.server.responsecode.FailResponse;
import com.server.responsecode.ResponseMessage;
import com.server.responsecode.StatusCode;
import com.server.responsecode.SuccessResponse;
import com.server.securityconfig.JwtTokenProvider;
import com.server.service.MemberService;

@RestController
public class LoginController {

	@Autowired
	MemberService memberService;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid MemberLoginDto memberLoginDto,Errors errors) {
		
		if(errors.hasErrors()) {
			return ResponseEntity.ok(new FailResponse(StatusCode.STATUS_FAIL,errors.getFieldError().getDefaultMessage()));
		}

		Optional<Member> option = memberService.getMember(memberLoginDto.getId());

		if (option.isPresent() && option.get().getPassword().equals(memberLoginDto.getPassword())) {
			HashMap<String, String> loginInfor = new HashMap<String, String>();
			loginInfor.put("name", option.get().getName());
			loginInfor.put("token", jwtTokenProvider.createToken(option.get().getId(), option.get().getRole()));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccessResponse(StatusCode.STATUS_OK, "로그인 성공", loginInfor));
		} else {

			return new ResponseEntity(new FailResponse(StatusCode.STATUS_FAIL, ResponseMessage.LOGIN_FAIL),
					HttpStatus.OK);
		}

	}

	@GetMapping("hi")
	public String hi() {
		return "hi";
	}

}
