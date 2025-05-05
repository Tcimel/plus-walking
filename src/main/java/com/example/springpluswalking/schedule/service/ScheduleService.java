package com.example.springpluswalking.schedule.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springpluswalking.schedule.dto.request.ScheduleRequestDto;
import com.example.springpluswalking.schedule.dto.request.ScheduleupdateRequestDto;
import com.example.springpluswalking.schedule.dto.response.SchedulePageResponseDto;
import com.example.springpluswalking.schedule.dto.response.ScheduleResponseDto;
import com.example.springpluswalking.schedule.entity.Schedule;
import com.example.springpluswalking.schedule.exception.ScheduleErrorCode;
import com.example.springpluswalking.schedule.exception.ScheduleException;
import com.example.springpluswalking.schedule.repository.ScheduleRepository;
import com.example.springpluswalking.user.entity.User;
import com.example.springpluswalking.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;

	public ScheduleResponseDto createSchedule(@Valid ScheduleRequestDto requestDto, Long userId) {
		Schedule schedule = Schedule.builder()
			.userEmail(userRepository.findByIdOrElseThrow(userId).getEmail())
			.title(requestDto.getTitle())
			.content(requestDto.getContent())
			.build();

		scheduleRepository.save(schedule);
		return new ScheduleResponseDto(schedule);
	}

	public Page<SchedulePageResponseDto> findAll(int page, int size, Long userId) {

		int adjustPage = (page > 0) ? page - 1 : 0; // 0부터 시작해서, 사용자가 1로 요청해도 0으로 바꿔주기

		// 페이징 위치, 크기, 정렬 설정
		PageRequest pageable = PageRequest.of(adjustPage, size, Sort.by("createdAt").descending());

		Page<Schedule> schedulePage = scheduleRepository.findAllOrElseThrow(pageable);

		return schedulePage.map(schedule -> SchedulePageResponseDto.builder()
			.id(schedule.getId())
			.userEmail(schedule.getUserEmail())
			.title(schedule.getTitle())
			.content(schedule.getContent())
			.commentCount(0)
			.createdAt(schedule.getCreatedAt())
			.updatedAt(schedule.getUpdatedAt())
			.build()
		);
	}

	public ScheduleResponseDto findById(Long id) {
		Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

		return new ScheduleResponseDto(findSchedule);
	}

	public ScheduleResponseDto updateSchedule(Long id, @Valid ScheduleupdateRequestDto requestDto,Long userId) {
		Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
		User loginUserInfo = userRepository.findByIdOrElseThrow(userId);
		String scheduleWriterEmail = loginUserInfo.getEmail();
		if(!findSchedule.getUserEmail().equals(scheduleWriterEmail)){
			throw new ScheduleException(ScheduleErrorCode.NO_PERMISSION);
		}
		if(!requestDto.getTitle().isEmpty()){
			findSchedule.updateTitle(requestDto.getTitle());
		}
		if(!requestDto.getContent().isEmpty()){
			findSchedule.updateContent(requestDto.getContent());
		}
		scheduleRepository.save(findSchedule);
		return new ScheduleResponseDto(findSchedule);
	}

	public void deleteSchedule(Long id, Long userId) {
		Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
		User loginUserInfo = userRepository.findByIdOrElseThrow(userId);
		String scheduleWriterEmail = loginUserInfo.getEmail();
		if(!findSchedule.getUserEmail().equals(scheduleWriterEmail)){
			throw new ScheduleException(ScheduleErrorCode.NO_PERMISSION);
		}
		scheduleRepository.delete(findSchedule);
	}
}
