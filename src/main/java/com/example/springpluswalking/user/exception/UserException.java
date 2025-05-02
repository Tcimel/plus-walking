package com.example.springpluswalking.user.exception;

public class UserException extends RuntimeException {

	private final int code;

	public UserException(UserErrorCode errorCode){
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}
}
