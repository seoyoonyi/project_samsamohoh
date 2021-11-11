package com.server.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
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
	public ResponseEntity login(@RequestBody Member member) {
		Optional<Member> option = memberService.getMember(member);
		
		if (option.isPresent() && option.get().getPassword().equals(member.getPassword())) {
			HashMap<String,String> loginInfor = new HashMap<String,String>();
			loginInfor.put("name", option.get().getName());
			loginInfor.put("token",jwtTokenProvider.createToken(member.getId(), member.getRole()));
			return new ResponseEntity(SuccessResponse.res(StatusCode.STATUS_OK, ResponseMessage.LOGIN_SUCCESS,loginInfor),HttpStatus.OK);
		} else {
			
			return new ResponseEntity(new FailResponse(StatusCode.STATUS_FAIL,ResponseMessage.LOGIN_FAIL),HttpStatus.OK);
		}

	}
	
	@GetMapping("hi")
	public String hi() {
		return "hi";
	}

}
