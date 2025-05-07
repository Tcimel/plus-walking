package com.example.springpluswalking.comment.dto.response;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentResponseWithMessage {
	private final String message;
	private final List<CommentResponseDto> comments;
}
