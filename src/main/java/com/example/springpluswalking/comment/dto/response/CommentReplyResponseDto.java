package com.example.springpluswalking.comment.dto.response;

import java.time.LocalDateTime;

import com.example.springpluswalking.comment.entity.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentReplyResponseDto {

	@NotNull
	private final Long id;

	@NotBlank
	private final String userEmail;

	@NotBlank
	private final Long parentCommentId;

	private final String parentCommentContent;

	@NotBlank
	private final String content;

	private int childrenCount;

	@NotNull
	private final LocalDateTime createdAt;

	@NotNull
	private final LocalDateTime updatedAt;

	public CommentReplyResponseDto(Comment comment, int childrenCount){
		this.id = comment.getId();
		this.userEmail = comment.getUserEmail();
		this.content = comment.getContent();
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
		this.parentCommentId = comment.getParentComment().getId();
		this.parentCommentContent = comment.getParentComment().getContent();
		this.childrenCount = childrenCount;
	}
}
