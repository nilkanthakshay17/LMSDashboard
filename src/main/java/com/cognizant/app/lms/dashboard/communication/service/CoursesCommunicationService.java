package com.cognizant.app.lms.dashboard.communication.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.app.lms.dashboard.communication.CoursesServiceFeignClient;
import com.cognizant.app.lms.dashboard.model.CourseRequestModel;
import com.cognizant.app.lms.dashboard.model.CourseResponseModel;

@Service
public class CoursesCommunicationService {

	@Autowired
	private CoursesServiceFeignClient coursesServiceFeignClient;
	
	public ResponseEntity<CourseResponseModel> registerCourse(CourseRequestModel registerRequest)throws Throwable{
		return coursesServiceFeignClient.registerCourse(registerRequest);
	}
	
	public ResponseEntity<List<CourseResponseModel>> getAllCourses()throws Throwable{
		return coursesServiceFeignClient.getAllCourses();
	}
	
	public ResponseEntity<CourseResponseModel> getCourseById(String id)throws Throwable{
		return coursesServiceFeignClient.getCourseById(id);
	}
	
	public ResponseEntity<CourseResponseModel> updateCourseById(CourseRequestModel updateRequest, String id)throws Throwable{
		return coursesServiceFeignClient.updateCourseById(updateRequest, id);
	}
	
	
	public String deleteAllCourses() {
		return coursesServiceFeignClient.deleteAllCourses();
	}
	
	
	public ResponseEntity<Map<String,CourseResponseModel>> deleteCourseById(String id)throws Throwable{
		return coursesServiceFeignClient.deleteCourseById(id);
	}
	
	public ResponseEntity<List<CourseResponseModel>> getCoursesInfo(String courseParam,String searchParam)throws Throwable{
		return coursesServiceFeignClient.getCourseInfo(courseParam, searchParam);
	}
	
//	public ResponseEntity<?> getCourseByName(String name) throws Throwable{
//		return coursesServiceFeignClient.getCourseByName(name);
//	}
//	
//
//	public ResponseEntity<?> getCourseByDescription(String description) throws Throwable{
//		return coursesServiceFeignClient.getCoursesByDescription(description);
//	}
//	
//	public ResponseEntity<?> getCourseByDuration(String duration) throws Throwable{
//		return coursesServiceFeignClient.getCoursesByDuration(duration);
//	}
//	
//	public ResponseEntity<?> getCourseTechnology(String technology) throws Throwable{
//		return coursesServiceFeignClient.getCoursesByTechnology(technology);
//	}
}
