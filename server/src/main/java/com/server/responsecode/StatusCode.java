package com.server.responsecode;

public class StatusCode {
	public static final int STATUS_OK = 0; // 요청 성공
	public static final int RESOURSE_CREATE_SUCCESS = 1; // 데이터 생성에 성공한 경우
	public static final int STATUS_FAIL = -1; // 요청 실패
	public static final int RESOURSE_CREATE_FAILED = 1001; // 데이터 생성에 실패한 경우
	public static final int RESOURSE_UPDATE_FAILED = 1002; // 데이터 변경에 실패한 경우
	public static final int RESOURSE_DELETE_FAILED = 1003; // 데이터 삭제에 실패한 경우
	public static final int BAD_REQUEST = 1004; // 요청 형식이 잘못된 경우
	public static final int RESOUSE_NOT_FOUND = 1005; // 요청한 데이터를 찾을 수 없을 경우
	public static final int API_NOT_FOUND = 1006; // API 요청을 찾을 수 없는 경우,
	public static final int CONFLICT = 1007; // 이미 처리한 요청에 대해 재요청하는 경우
	public static final int INTERNAL_SEVER_ERROR = 1008; // 서버에러
	public static final int RECORD_NOT_UNIQUE = 1009; // 동일한 데이터가 이미 등록되어 있는 경우
	public static final int MOVED_PERMANENTLY = 1010; // 영구적으로 이동한 경우
	public static final int FOUND_ERROR = 1011; // 임시적으로 이동한 경우
	public static final int UNAUTHORIZED_ERROR = 2001; // 인증값이 유효하지 않은 경우
	public static final int DUPLICATED_ID = 1012; // 회원가입시 아이디 중복

}
