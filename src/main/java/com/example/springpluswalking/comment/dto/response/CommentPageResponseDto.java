package com.example.springpluswalking.comment.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentPageResponseDto {
	private final Long id;
	private final String userEmail;
	private final String content;
	private final LocalDateTime createdAt;
}
