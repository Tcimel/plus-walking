package com.example.springpluswalking.comment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode {
	USER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
	COMMENT_NOT_FOUND(404, "댓글이 없습니다."),
	NO_PERMISSION(401, "권한이 없습니다.");

	private final int code;
	private final String message;
}
