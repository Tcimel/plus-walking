package com.example.springpluswalking.common.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springpluswalking.user.dto.response.UserExceptionResponseDto;
import com.example.springpluswalking.user.exception.UserException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<UserExceptionResponseDto> handleUserException(UserException ex) {
		// UserException 에 code 가 null 일 경우, 500 에러
		HttpStatus httpStatus = Optional.ofNullable(HttpStatus.resolve(ex.getCode()))
			.orElse(HttpStatus.INTERNAL_SERVER_ERROR);

		// 예외 응답 DTO 생성
		UserExceptionResponseDto response = new UserExceptionResponseDto(ex.getCode(), httpStatus.getReasonPhrase(),
			ex.getMessage());

		return ResponseEntity.status(ex.getCode()).body(response);
	}
}
