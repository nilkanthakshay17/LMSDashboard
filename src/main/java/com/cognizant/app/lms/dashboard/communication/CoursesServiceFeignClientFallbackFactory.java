package com.cognizant.app.lms.dashboard.communication;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CoursesServiceFeignClientFallbackFactory implements FallbackFactory<CoursesServiceFeignClientFallback>{

	@Override
	public CoursesServiceFeignClientFallback create(Throwable cause) {
		// TODO Auto-generated method stub
		return new CoursesServiceFeignClientFallback(cause);
	}

}
