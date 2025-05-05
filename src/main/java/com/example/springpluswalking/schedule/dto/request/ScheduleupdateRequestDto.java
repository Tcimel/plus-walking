package com.example.springpluswalking.schedule.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleupdateRequestDto {
	@NotNull(message = "내용을 입력해주세요.")
	private String title;

	@NotNull(message = "내용을 입력해주세요.")
	private String content;
}
