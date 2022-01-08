package com.server.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value= {MethodArgumentNotValidException.class})
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errorMessageList = new ArrayList<String>();
		for(FieldError fe : e.getFieldErrors()) {
			errorMessageList.add(fe.getDefaultMessage());
		}
		
		ApiException<List<String>> apiException = ApiException.<List<String>>builder()
									.message(errorMessageList)
									.code(1)
									.build();
									
		return ResponseEntity.status(httpStatus).body(apiException);

	}
	
	@ExceptionHandler(value= {MemberNotExistException.class})
	public ResponseEntity<?> handleMemberNotExistException(MemberNotExistException e){
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		ApiException<String> apiException = ApiException.<String>builder()
									.message("해당 사용자가 존재하지 않습니다.")
									.code(1)
									.build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
									
		
	}
	
	@ExceptionHandler(value= {IOException.class})
	public ResponseEntity<?> handleIOException(IOException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		ApiException<String> apiException = ApiException.<String>builder()
									.message("파일 입출력에 문제가 있습니다")
									.code(1)
									.build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
	}
	
	@ExceptionHandler(value = {AlreadyExistMemberException.class})
	public ResponseEntity<?> handleAlreadyExistMemberException(AlreadyExistMemberException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder()
														.code(-1)
														.message("이미 존재하는 아이디입니다")
														.build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
	}
	
	@ExceptionHandler(value= {LoginFailedException.class})
	public ResponseEntity<?> handleLoginFailedException(LoginFailedException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder().code(-1).message("로그인 실패").build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
		
										
	}
	
	@ExceptionHandler(value= {InvalidMemberException.class})
	public ResponseEntity<?> handleInvalidMemberException(InvalidMemberException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder().code(-1).message("유효하지 않은 계정입니다.").build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
	}
	
	@ExceptionHandler(value= {IdDismatchException.class})
	public ResponseEntity<?> handleIdDismatchException(IdDismatchException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder().code(-1).message("아이디가 일치하지 않습니다.").build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
	}
	
	@ExceptionHandler(value= {PasswordDismatchException.class})
	public ResponseEntity<?> handleInvalidMemberException(PasswordDismatchException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder().code(-1).message("비밀번호가 일치하지 않습니다.").build();
	
		return ResponseEntity.status(httpStatus).body(apiException);
	}
}
