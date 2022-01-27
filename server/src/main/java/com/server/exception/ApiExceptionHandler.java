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

import com.server.exception.board.BoardListNotExistException;
import com.server.exception.board.BoardNotExistException;
import com.server.exception.board.InvalidBoardException;
import com.server.exception.board.InvalidPagingParameterException;
import com.server.exception.member.AlreadyExistMemberException;
import com.server.exception.member.IdDismatchException;
import com.server.exception.member.InvalidMemberException;
import com.server.exception.member.LoginFailedException;
import com.server.exception.member.MemberNotExistException;
import com.server.exception.member.NickNameValidationException;
import com.server.exception.member.PasswordDismatchException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errorMessageList = new ArrayList<String>();
		for (FieldError fe : e.getFieldErrors()) {
			errorMessageList.add(fe.getDefaultMessage());
		}

		ApiException<List<String>> apiException = ApiException.<List<String>>builder().message(errorMessageList)
				.code(ExceptionCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.code).build();

		return ResponseEntity.status(httpStatus).body(apiException);

	}

	@ExceptionHandler(value = { NickNameValidationException.class })
	public ResponseEntity<?> handleNickNameValidationException(NickNameValidationException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		ApiException<String> apiException = ApiException.<String>builder()
				.message(ExceptionCode.NICK_NAME_VALIDATION_EXCEPTION.message)
				.code(ExceptionCode.NICK_NAME_VALIDATION_EXCEPTION.code).build();
		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(value = { MemberNotExistException.class })
	public ResponseEntity<?> handleMemberNotExistException(MemberNotExistException e) {

		HttpStatus httpStatus = HttpStatus.OK;

		ApiException<String> apiException = ApiException.<String>builder()
				.message(ExceptionCode.MEMBER_NOT_EXIST_EXCEPTION.message)
				.code(ExceptionCode.MEMBER_NOT_EXIST_EXCEPTION.code).build();

		return ResponseEntity.status(httpStatus).body(apiException);

	}

	@ExceptionHandler(value = { IOException.class })
	public ResponseEntity<?> handleIOException(IOException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		ApiException<String> apiException = ApiException.<String>builder().message(ExceptionCode.IO_EXCEPTION.message)
				.code(ExceptionCode.IO_EXCEPTION.code).build();

		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(value = { AlreadyExistMemberException.class })
	public ResponseEntity<?> handleAlreadyExistMemberException(AlreadyExistMemberException e) {
		HttpStatus httpStatus = HttpStatus.OK;
		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.ALREADY_EXIST_MEMBER_EXCEPTION.code)
				.message(ExceptionCode.ALREADY_EXIST_MEMBER_EXCEPTION.message).build();

		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(value = { LoginFailedException.class })
	public ResponseEntity<?> handleLoginFailedException(LoginFailedException e) {
		HttpStatus httpStatus = HttpStatus.OK;
		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.LOGIN_FAILED_EXCEPTION.code).message(ExceptionCode.LOGIN_FAILED_EXCEPTION.message)
				.build();

		return ResponseEntity.status(httpStatus).body(apiException);

	}

	@ExceptionHandler(value = { InvalidMemberException.class })
	public ResponseEntity<?> handleInvalidMemberException(InvalidMemberException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.INVALID_MEMBER_EXCEPTION.code)
				.message(ExceptionCode.INVALID_MEMBER_EXCEPTION.message).build();

		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(value = { IdDismatchException.class })
	public ResponseEntity<?> handleIdDismatchException(IdDismatchException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.ID_DISMATCH_EXCEPTION.code).message(ExceptionCode.ID_DISMATCH_EXCEPTION.message)
				.build();

		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(value = { PasswordDismatchException.class })
	public ResponseEntity<?> handleInvalidMemberException(PasswordDismatchException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.PASSWORD_DISMATCH_EXCEPTOIN.code)
				.message(ExceptionCode.PASSWORD_DISMATCH_EXCEPTOIN.message).build();

		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(value = { InvalidBoardException.class })
	public ResponseEntity<?> handleBoardNotExistException(InvalidBoardException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.INVALID_BOARD_EXCEPTION.code).message(ExceptionCode.INVALID_BOARD_EXCEPTION.message)
				.build();

		return ResponseEntity.status(httpStatus).body(apiException);

	}

	@ExceptionHandler(value = { InvalidPagingParameterException.class })
	public ResponseEntity<?> handleInvalidPagingParameterException(InvalidPagingParameterException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		ApiException<String> apiException = ApiException.<String>builder()
				.code(ExceptionCode.INVALID_PAGING_PARAMETER_EXCEPTION.code)
				.message(ExceptionCode.INVALID_PAGING_PARAMETER_EXCEPTION.message).build();

		return ResponseEntity.status(httpStatus).body(apiException);

	}
	
	@ExceptionHandler(value= {BoardNotExistException.class})
	public ResponseEntity<?> handleBoardNotExistException(BoardNotExistException e){
		HttpStatus httpStatus = HttpStatus.OK;
		
		ApiException<String> apiException = ApiException.<String>builder().code(ExceptionCode.BOARD_NOT_EXIST_EXCEPTION.code)
				.message(ExceptionCode.BOARD_NOT_EXIST_EXCEPTION.message).build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
	}
	
	@ExceptionHandler(value= {BoardListNotExistException.class})
	public ResponseEntity<?> handleBoardListNotExistException(BoardListNotExistException e){
		HttpStatus httpStatus = HttpStatus.OK;
		
		ApiException<String> apiException = ApiException.<String>builder().code(ExceptionCode.BOARDLIST_NOT_EXIST_EXCEPTION.code)
				.message(ExceptionCode.BOARD_NOT_EXIST_EXCEPTION.message).build();
		
		return ResponseEntity.status(httpStatus).body(apiException);
	}
}
