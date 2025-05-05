package com.example.springpluswalking.comment.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.springpluswalking.comment.dto.request.CommentRequestDto;
import com.example.springpluswalking.comment.dto.response.CommentPageResponseDto;
import com.example.springpluswalking.comment.dto.response.CommentResponseDto;
import com.example.springpluswalking.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("{id}/comment")
	public ResponseEntity<CommentResponseDto> createComment(
		@SessionAttribute("userId") Long userId,
		@PathVariable Long id,
		@Valid @RequestBody CommentRequestDto requestDto
	){
		CommentResponseDto responseDto = commentService.createComment(id, requestDto, userId);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping("{id}/comments")
	public ResponseEntity<Page<CommentPageResponseDto>> findAllComment(
		@SessionAttribute("userId") Long userId,
		@PathVariable Long id,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "5") int size
	){
		Page<CommentPageResponseDto> list = commentService.findAll(id,page,size);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@GetMapping("comment/{id}")
	public ResponseEntity<CommentResponseDto> findById(
		@SessionAttribute("userId") Long userId,
		@PathVariable Long id
	){
		CommentResponseDto responseDto = commentService.findById(id);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}

	@PatchMapping("comment/{id}")
	public ResponseEntity<CommentResponseDto> updateComment(
		@SessionAttribute("userId") Long userId,
		@PathVariable Long id,
		@Valid @RequestBody CommentRequestDto requestDto
	){
		CommentResponseDto responseDto = commentService.updateComment(id, userId, requestDto);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}

	@DeleteMapping("comment/{id}")
	public ResponseEntity<String> deleteComment(
		@SessionAttribute("userId") Long userId,
		@PathVariable Long id
	){
		commentService.deleteComment(id, userId);
		return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
	}

}
