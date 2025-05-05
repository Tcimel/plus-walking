package com.example.springpluswalking.schedule.exception;


import lombok.Getter;

@Getter
public class ScheduleException extends RuntimeException {
	private final int code;

	public ScheduleException(ScheduleErrorCode errorCode){
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}
}
