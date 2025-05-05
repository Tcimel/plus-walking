package com.example.springpluswalking.comment.exception;

import lombok.Getter;

@Getter
public class CommentException extends RuntimeException {
	private final int code;

	public CommentException(CommentErrorCode errorCode){
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}

}
