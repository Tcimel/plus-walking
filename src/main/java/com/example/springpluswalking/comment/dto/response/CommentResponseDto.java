package com.example.springpluswalking.comment.dto.response;

import java.time.LocalDateTime;

import com.example.springpluswalking.comment.entity.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentResponseDto {

	@NotBlank
	private final Long id;

	@NotBlank
	private final String userEmail;

	@NotBlank
	private final String content;

	@NotNull
	private final LocalDateTime createdAt;

	@NotNull
	private final LocalDateTime updatedAt;

	public CommentResponseDto(Comment comment){
		this.id = comment.getId();
		this.userEmail = comment.getUserEmail();
		this.content = comment.getContent();
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
	}
}
