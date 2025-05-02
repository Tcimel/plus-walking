package com.example.springpluswalking.user.service;

import org.springframework.stereotype.Service;

import com.example.springpluswalking.user.dto.request.LoginRequestDto;
import com.example.springpluswalking.user.dto.request.SignupRequestDto;
import com.example.springpluswalking.user.entity.User;
import com.example.springpluswalking.user.exception.UserErrorCode;
import com.example.springpluswalking.user.exception.UserException;
import com.example.springpluswalking.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public void signUp(@Valid SignupRequestDto requestDto) {
		//이메일 중복 검증
		if(userRepository.existsByEmail(requestDto.getEmail())){
			throw new UserException(UserErrorCode.USER_DUPLICATION_EMAIL);
		}

		userRepository.save(
			User.builder()
				.email(requestDto.getEmail())
				.password(requestDto.getPassword())
				.build()
		);
	}

	public Long login(@Valid LoginRequestDto requestDto) {
		User findUser = userRepository.findByEmailOrElseThrow(requestDto.getEmail());

		if(!findUser.getPassword().equals(requestDto.getPassword())){
			throw new UserException(UserErrorCode.USER_WRONG_PW);
		}

		return findUser.getId();
	}
}
