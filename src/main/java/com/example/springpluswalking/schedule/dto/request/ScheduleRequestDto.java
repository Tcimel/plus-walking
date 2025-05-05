package com.example.springpluswalking.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {

	@NotBlank(message = "제목을 입력해주세요.")
	private final String title;

	@NotNull
	private final String content;

}
