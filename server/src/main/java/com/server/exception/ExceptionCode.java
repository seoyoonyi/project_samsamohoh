package com.server.exception;

public enum ExceptionCode {
	//Member관련 예외 코드
	ALREADY_EXIST_MEMBER_EXCEPTION("1000","이미 존재하는 사용자입니다."),
	ID_DISMATCH_EXCEPTION("1001","아이디가 일치하지 않습니다."),
	INVALID_MEMBER_EXCEPTION("1002","유효하지 않은 사용자입니다."),
	LOGIN_FAILED_EXCEPTION("1003","로그인 실패"),
	MEMBER_NOT_EXIST_EXCEPTION("1004","존재하지 않는 사용자입니다."),
	PASSWORD_DISMATCH_EXCEPTOIN("1005","비밀번호가 일치하지 않습니다."),
	NICK_NAME_VALIDATION_EXCEPTION("1006","닉네임은 2글자이상 8글자이하의 자음과 모음이 갖춰진 한글만 가능합니다."),
	EMAIL_VALIDATION_EXCEPTION("1007","이메일형식이 올바르지 않습니다."),
	PASSWORD_VALIDATION_EXCEPTION("1008","비밀번호는 영문소문자,영문대문자,숫자,특수문자를 포함한 8글자이상 15글자 이하입니다."),
	
	//Board관련 예외 코드
	INVALID_BOARD_EXCEPTION("2000","해당 게시글PK에대한 게시글이 존재하지않습니다."),
	BOARD_NOT_EXIST_EXCEPTION("2001","조회된 게시글이 없습니다."),
	BOARDLIST_NOT_EXIST_EXCEPTION("2003","조회된 게시글리스트가 없습니다."),
	INVALID_PAGING_PARAMETER_EXCEPTION("2004","유효하지 않은 페이징 파라미터입니다."),
	//유효성 검사 예외 코드
	METHOD_ARGUMENT_NOT_VALID_EXCEPTION("-1","유효성 검사 실패"),
	
	//파일 입출력 예외 코드
	IO_EXCEPTION("-2","파일 입출력 에러"),
	
	//알수 없는 에러
	UNKNOWN_ERROR("-3","알수없는 에러 발생"),
	
	//JWT 및 SpringSecurity 관련 예외 코드
	WRONG_TYPE_TOKEN("9000","토큰의 구조에 문제가있습니다."),
	EXPIRED_TOKEN("9001","유효기간이 만료된 토큰입니다."),
	UNSUPPORTED_TOKEN("9002","지원하지않는 토큰입니다."),
	WRONG_TOKEN("9003","유효하지않은 토큰입니다."),
	PERMISSION_DENIED("9004","리소스에대한 권한이 없습니다."),
	DO_NOT_EXIST_TOKEN_EXCEPTION("9005","토큰값을 읽어올 수 없거나 토큰값이 존재하지 않습니다.");
	
	
	public final String code;
	public final String message;
	
	ExceptionCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
