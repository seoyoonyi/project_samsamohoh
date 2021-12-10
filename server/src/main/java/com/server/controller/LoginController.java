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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.domain.Member;
import com.server.dto.member.MemberLoginDTO;
import com.server.dto.response.FailedResponseDTO;
import com.server.dto.response.SuccessfulResponseDTO;
import com.server.responsecode.StatusCode;
import com.server.securityconfig.JwtTokenProvider;
import com.server.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(description="로그인 관련 REST API")
public class LoginController {

	@Autowired
	MemberService memberService;

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@ApiOperation(value="로그인")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid MemberLoginDTO memberLoginDTO,@ApiIgnore Errors errors) {

		if (errors.hasErrors()) {
			List<FieldError> errorList = errors.getFieldErrors();
			List<String> errorMessageList = new ArrayList<String>();
			for (FieldError fe : errorList) {
				errorMessageList.add(fe.getDefaultMessage());
			}

			FailedResponseDTO<List<String>> response = FailedResponseDTO.<List<String>>builder()
					.code(StatusCode.STATUS_FAIL).message(errorMessageList).build();

			return ResponseEntity.ok().body(response);
		}
		
		Optional<Member> option = memberService.getMember(memberLoginDTO.getId());
		
		if (option.isPresent() && option.get().getPassword().equals(memberLoginDTO.getPassword())) {
			HashMap<String, String> loginInfor = new HashMap<String, String>();
			loginInfor.put("name", option.get().getName());
			loginInfor.put("token", jwtTokenProvider.createToken(option.get().getId(), option.get().getRole()));
			SuccessfulResponseDTO<HashMap<String, String>> response = SuccessfulResponseDTO
					.<HashMap<String, String>>builder().code(StatusCode.STATUS_OK).message("로그인 성공").data(loginInfor)
					.build();
			return ResponseEntity.ok().body(response);
		} else {

			FailedResponseDTO<String> response = FailedResponseDTO.<String>builder().code(StatusCode.STATUS_FAIL)
					.message("로그인 실패").build();

			return ResponseEntity.ok().body(response);

		}

	}
		
}
