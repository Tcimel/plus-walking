package com.example.springpluswalking.common.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springpluswalking.schedule.exception.ScheduleException;
import com.example.springpluswalking.user.exception.UserException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ExceptionResponseDto> handleUserException(UserException ex) {
		// UserException 에 code 가 null 일 경우, 500 에러
		HttpStatus httpStatus = Optional.ofNullable(HttpStatus.resolve(ex.getCode()))
			.orElse(HttpStatus.INTERNAL_SERVER_ERROR);

		// 예외 응답 DTO 생성
		ExceptionResponseDto response = new ExceptionResponseDto(ex.getCode(), httpStatus.getReasonPhrase(),
			ex.getMessage());

		return ResponseEntity.status(ex.getCode()).body(response);
	}

	@ExceptionHandler(ScheduleException.class)
	public ResponseEntity<ExceptionResponseDto> handleScheduleException(ScheduleException ex) {
		// UserException 에 code 가 null 일 경우, 500 에러
		HttpStatus httpStatus = Optional.ofNullable(HttpStatus.resolve(ex.getCode()))
			.orElse(HttpStatus.INTERNAL_SERVER_ERROR);

		// 예외 응답 DTO 생성
		ExceptionResponseDto response = new ExceptionResponseDto(ex.getCode(), httpStatus.getReasonPhrase(),
			ex.getMessage());

		return ResponseEntity.status(ex.getCode()).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String >> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String fieldName = error.getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<String> handleSessionMissing(ServletRequestBindingException ex) {
		return ResponseEntity
			.status(HttpStatus.UNAUTHORIZED)
			.body("로그인이 필요합니다.");
	}
}
