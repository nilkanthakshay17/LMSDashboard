package com.cognizant.app.lms.dashboard.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cognizant.app.lms.dashboard.model.CourseRequestModel;
import com.cognizant.app.lms.dashboard.model.CourseResponseModel;

import feign.FeignException;


//If the courses service is not available these methods are configured to return empty responses
@Component
public class CoursesServiceFeignClientFallback implements CoursesServiceFeignClient{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final Throwable cause; 
	
	CoursesServiceFeignClientFallback(Throwable cause){
		this.cause = cause;
	}
	
	@Override
	public ResponseEntity<CourseResponseModel> registerCourse(CourseRequestModel registerRequest) throws Throwable{
		// TODO Auto-generated method stub
		if(cause != null && cause instanceof FeignException) {
			logger.error("error: "+cause.getLocalizedMessage());
			throw cause;
		}
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CourseResponseModel());
	}

	@Override
	public ResponseEntity<List<CourseResponseModel>> getAllCourses() throws Throwable{
		// TODO Auto-generated method stub
		if(cause != null && cause instanceof FeignException) {
			logger.error("error: "+cause.getLocalizedMessage());
			throw cause;
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
	}

	@Override
	public ResponseEntity<CourseResponseModel> getCourseById(String id) throws Throwable {
		// TODO Auto-generated method stub
		if(cause != null && cause instanceof FeignException) {
			logger.error("error: "+cause.getLocalizedMessage());
			throw cause;
		}
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CourseResponseModel());
	}

	@Override
	public ResponseEntity<CourseResponseModel> updateCourseById(CourseRequestModel updateRequest, String id) throws Throwable{
		// TODO Auto-generated method stub
		if(cause != null && cause instanceof FeignException) {
			logger.error("error: "+cause.getLocalizedMessage());
			throw cause;
		}
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CourseResponseModel());
	}

	@Override
	public String deleteAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, CourseResponseModel>> deleteCourseById(String id) throws Throwable {
		if(cause != null && cause instanceof FeignException) {
			logger.error("error: "+cause.getLocalizedMessage());
			throw cause;
		}
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

//	@Override
//	public ResponseEntity<?> getCourseByName(String name) throws Throwable {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ResponseEntity<?> getCoursesByDescription(String description) throws Throwable {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ResponseEntity<?> getCoursesByDuration(String duration) throws Throwable {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ResponseEntity<?> getCoursesByTechnology(String technology) throws Throwable {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ResponseEntity<List<CourseResponseModel>> getCourseInfo(String courseParam, String paramSearch) throws Throwable {
		if(cause != null && cause instanceof FeignException) {
			logger.error("error: "+cause.getLocalizedMessage());
			throw cause;
		}
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
	}
	
	
}
