package com.example.springpluswalking.schedule.dto.response;

import java.time.LocalDateTime;

import com.example.springpluswalking.schedule.entity.Schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ScheduleResponseDto {

	@NotNull
	private final Long id;

	@NotNull
	private final String userEmail;

	@NotNull
	private final String title;

	private final String content;

	private final int commentCount;

	@NotNull
	private final LocalDateTime createdAt;

	@NotNull
	private final LocalDateTime updatedAt;

	public ScheduleResponseDto(Schedule sc, int commentCount){
		this.id = sc.getId();
		this.userEmail = sc.getUserEmail();
		this.title = sc.getTitle();
		this.content = sc.getContent();
		this.createdAt = sc.getCreatedAt();
		this.updatedAt = sc.getUpdatedAt();
		this.commentCount = commentCount;
	}
}
