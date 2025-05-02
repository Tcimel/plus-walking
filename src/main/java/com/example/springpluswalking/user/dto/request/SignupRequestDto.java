package com.example.springpluswalking.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupRequestDto {

	@NotBlank(message = "이메일을 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	private final String email;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private final String password;

}
