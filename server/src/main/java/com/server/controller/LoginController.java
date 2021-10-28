package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;

@RestController
public class LoginController {

	@RequestMapping("/hi")
	@ResponseBody
	public Map<Integer,Object> login() {

		Map<Integer,Object> members = new HashMap<>();
		
		for(int i = 1; i <=20; i++) {
			Map<String,Object> member = new HashMap<>();
			member.put("idx", i);
			member.put("nickname", i+"길동");
			member.put("height", i+20);
			member.put("weight",i+30);
			members.put(i,member);
			
		}
		
		return members;
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "반갑습니다";
	}
	
	@PostMapping("/member")
	public void member(@RequestBody Member member) {
		System.out.println(member.getId());
		System.out.println(member.getName());
		System.out.println(member.getPassword());
	}
	
	@GetMapping("/memberList")
	public List<Member> memberList(){
		List<Member> memberList = new ArrayList<>();
		
		for(int i = 1; i<=10; i++) {
			Member member = new Member();
			member.setId(i+"");
			member.setName("사용자 "+i);
			member.setPassword("123"+i);
			memberList.add(member);
		}
		return memberList;
	}

}
