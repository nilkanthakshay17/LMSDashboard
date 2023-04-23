package com.cognizant.app.lms.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class DashboardExceptionHandler {

	@ExceptionHandler(value = FeignException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleFeignException(FeignException exception){
		return exception.getMessage();
	}
}
