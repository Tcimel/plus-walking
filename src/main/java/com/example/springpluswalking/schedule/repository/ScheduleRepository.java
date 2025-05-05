package com.example.springpluswalking.schedule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springpluswalking.schedule.entity.Schedule;
import com.example.springpluswalking.schedule.exception.ScheduleErrorCode;
import com.example.springpluswalking.schedule.exception.ScheduleException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	default Schedule findByIdOrElseThrow(Long id){
		return findById(id).orElseThrow(()->new ScheduleException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
	}

	default Page<Schedule> findAllOrElseThrow(PageRequest request){
		Page<Schedule> page = findAll(request);
		if(page.isEmpty()){
			throw new ScheduleException(ScheduleErrorCode.SCHEDULE_NOT_FOUND);
		}
		return page;
	}
}
