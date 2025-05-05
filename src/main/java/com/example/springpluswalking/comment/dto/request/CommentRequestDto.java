package com.example.springpluswalking.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {

	@NotBlank(message = "내용을 입력해주세요.")
	private final String content;
}
