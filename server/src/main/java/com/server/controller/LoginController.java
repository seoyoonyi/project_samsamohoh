package com.server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseCode login(Member member) {
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

	/*
	 * @RequestMapping("/getMemberList1")
	 * 
	 * @ResponseBody public Map<Integer,Object> memberList1() {
	 * 
	 * Map<Integer,Object> members = new HashMap<>();
	 * 
	 * for(int i = 1; i <=20; i++) { Map<String,Object> member = new HashMap<>();
	 * member.put("idx", i); member.put("nickname", i+"길동"); member.put("height",
	 * i+20); member.put("weight",i+30); members.put(i,member);
	 * 
	 * }
	 * 
	 * return members; }
	 * 
	 * @RequestMapping("/hello") public String hello() { return "반갑습니다"; }
	 * 
	 * @GetMapping("/member") public Member getMember( ) { Member member = new
	 * Member(); member.setId("koma1416"); member.setName("이재원");
	 * member.setPassword("123456");
	 * 
	 * return member; }
	 * 
	 * @GetMapping("/getMemberList2") public List<Member> memberList2(){
	 * List<Member> memberList = new ArrayList<>();
	 * 
	 * for(int i = 1; i<=10; i++) { Member member = new Member();
	 * member.setId(i+""); member.setName("사용자 "+i); member.setPassword("123"+i);
	 * memberList.add(member); } return memberList; }
	 */

}
