package com.example.springpluswalking.schedule.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.springpluswalking.comment.dto.response.CommentPageResponseDto;
import com.example.springpluswalking.comment.dto.response.CommentResponseWithMessage;
import com.example.springpluswalking.comment.entity.Comment;
import com.example.springpluswalking.schedule.entity.Schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ScheduleDetailResponseDto {

	@NotNull
	private final Long id;

	@NotNull
	private final String userEmail;

	@NotNull
	private final String title;

	private final String content;

	private final CommentResponseWithMessage comments;

	@NotNull
	private final LocalDateTime createdAt;

	@NotNull
	private final LocalDateTime updatedAt;

	public ScheduleDetailResponseDto(Schedule sc, CommentResponseWithMessage comments){
		this.id = sc.getId();
		this.userEmail = sc.getUserEmail();
		this.title = sc.getTitle();
		this.content = sc.getContent();
		this.createdAt = sc.getCreatedAt();
		this.updatedAt = sc.getUpdatedAt();
		this.comments = comments;
	}
}
