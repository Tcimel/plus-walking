package com.example.springpluswalking.schedule.controller;

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

import com.example.springpluswalking.schedule.dto.request.ScheduleRequestDto;
import com.example.springpluswalking.schedule.dto.request.ScheduleupdateRequestDto;
import com.example.springpluswalking.schedule.dto.response.ScheduleDetailResponseDto;
import com.example.springpluswalking.schedule.dto.response.SchedulePageResponseDto;
import com.example.springpluswalking.schedule.dto.response.ScheduleResponseDto;
import com.example.springpluswalking.schedule.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PostMapping("/schedule")
	public ResponseEntity<ScheduleResponseDto> createSchedule(
		@Valid @RequestBody ScheduleRequestDto requestDto,
		@SessionAttribute("userId") Long userId
	){
		ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, userId);

		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping("/schedule") // 파람추가필요, ?page=2&size=20
	public ResponseEntity<Page<SchedulePageResponseDto>> findAllSchedules(
		@SessionAttribute(name = "userId", required = true) Long userId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size
	){
		Page<SchedulePageResponseDto> list = scheduleService.findAll(page, size, userId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@GetMapping("/schedule/{id}")
	public ResponseEntity<ScheduleDetailResponseDto> findById(
		@PathVariable Long id

	){
		ScheduleDetailResponseDto responseDto = scheduleService.findById(id);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@PatchMapping("/schedule/{id}")
	public ResponseEntity<ScheduleResponseDto> updateSchedule(
		@PathVariable Long id,
		@Valid @RequestBody ScheduleupdateRequestDto requestDto,
		@SessionAttribute(name = "userId", required = true) Long userId
	){
		ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto, userId);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}

	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<String> deleteSchedule(
		@PathVariable Long id,
		@SessionAttribute(name = "userId", required = true) Long userId
	){
		scheduleService.deleteSchedule(id, userId);
		return new ResponseEntity<>("삭제 완료",HttpStatus.OK);
	}
}
