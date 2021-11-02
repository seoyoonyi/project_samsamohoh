package com.server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.responsecode.Data;
import com.server.responsecode.ResponseCode;
import com.server.service.MemberService;

@RestController
public class LoginController {

	@Autowired
	MemberService memberService;

	@PostMapping("/login")
	public ResponseCode login(@RequestBody Member member) {
		Optional<Member> option = memberService.getMember(member);
		ResponseCode rc = new ResponseCode();

		if (option.isPresent() && option.get().getPassword().equals(member.getPassword())) {
			rc.setCode("0");
			rc.setMessage("ok");
			rc.setData(new Data(option.get().getName()));
			return rc;
		} else {
			rc.setCode("-1");
			rc.setMessage("fail");
			return rc;
		}

	}

	@RequestMapping("/hello")
	public String hello() {
		return "반갑습니다";
	}

}
