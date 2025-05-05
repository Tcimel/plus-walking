package com.example.springpluswalking.schedule.dto.response;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SchedulePageResponseDto {
	private final Long id;
	private final String userEmail;
	private final String title;
	private final String content;
	private final int commentCount;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
