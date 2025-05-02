package com.example.springpluswalking.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springpluswalking.user.dto.request.LoginRequestDto;
import com.example.springpluswalking.user.dto.request.SignupRequestDto;
import com.example.springpluswalking.user.entity.User;
import com.example.springpluswalking.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequestDto requestDto){
		System.out.println("가입 진행 🚀🚀🚀🚀");
		userService.signUp(requestDto);
		System.out.println("가입 성공 🚀🚀🚀🚀");
		return new ResponseEntity<>("회원가입 완료",HttpStatus.CREATED);
	}

	// 세션 방식 로그인
	@PostMapping("/login")
	public ResponseEntity<String> login(
		@Valid @RequestBody LoginRequestDto requestDto,
		HttpServletRequest httpServletRequest){
		System.out.println("로그인 진행 🚀🚀🚀🚀");
		// 서비스에서 유저 조회 성공 시 세션 저장
		Long loginUserId = userService.login(requestDto);
		HttpSession session = httpServletRequest.getSession();
		System.out.println("유저 정보 조회 성공 🚀🚀🚀🚀");
		// 세션에 로그인 유저 아이디 정보 저장
		session.setAttribute("userId",loginUserId);
		System.out.println("로그인 성공 🚀🚀🚀🚀");
		return new ResponseEntity<>("로그인 성공",HttpStatus.OK);
	}


}
