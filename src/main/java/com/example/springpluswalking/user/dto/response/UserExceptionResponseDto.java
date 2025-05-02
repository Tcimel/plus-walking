package com.example.springpluswalking.user.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder({"code", "status", "message"}) // Jackson 라이브러리에서 사용하는 어노테이션으로, 객체를 JSON으로 직렬화할 때 필드의 출력 순서를 지정해주는 역할
public class UserExceptionResponseDto {
	private final int code;
	private final String status;
	private final String message;
}
