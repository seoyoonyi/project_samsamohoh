package com.server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.responsecode.DefaultRes;
import com.server.responsecode.ResponseMessage;
import com.server.responsecode.StatusCode;
import com.server.securityconfig.JwtTokenProvider;
import com.server.service.MemberService;

@RestController
public class LoginController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Member member) {
		Optional<Member> option = memberService.getMember(member);
		
		if (option.isPresent() && option.get().getPassword().equals(member.getPassword())) {
			
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS,jwtTokenProvider.createToken(member.getId(), member.getRole())),HttpStatus.OK);
		} else {
			
			return new ResponseEntity(DefaultRes.res(StatusCode.OK,ResponseMessage.LOGIN_FAIL),HttpStatus.OK);
		}

	}


}
