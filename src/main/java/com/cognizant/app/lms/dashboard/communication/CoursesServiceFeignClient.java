package com.cognizant.app.lms.dashboard.communication;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.app.lms.dashboard.model.CourseRequestModel;
import com.cognizant.app.lms.dashboard.model.CourseResponseModel;

@FeignClient(name = "lmscourses", fallbackFactory = CoursesServiceFeignClientFallbackFactory.class)
public interface CoursesServiceFeignClient {
	
	@PostMapping("/course")
	public ResponseEntity<CourseResponseModel> registerCourse(@RequestBody CourseRequestModel registerRequest) throws Throwable;

	@GetMapping("/course")
	public ResponseEntity<List<CourseResponseModel>> getAllCourses() throws Throwable;
	
	@GetMapping("/course/{id}")
	public ResponseEntity<CourseResponseModel> getCourseById(@PathVariable(name = "id")String id) throws Throwable;

	@PutMapping("/course/{id}")
	public ResponseEntity<CourseResponseModel> updateCourseById(@RequestBody CourseRequestModel updateRequest, @PathVariable(name = "id")String id) throws Throwable;
	
	@DeleteMapping("/course")
	public String deleteAllCourses();
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity<Map<String,CourseResponseModel>> deleteCourseById(@PathVariable(name = "id")String id) throws Throwable;

	@GetMapping("/course/{course_param}/{param_search}")
	public ResponseEntity<List<CourseResponseModel>> getCourseInfo(@PathVariable(name = "course_param")String courseParam, @PathVariable(name = "param_search")String paramSearch)throws Throwable;

//	@GetMapping("/course/name")
//	public ResponseEntity<?> getCourseByName(@RequestParam(name = "name")String name)throws Throwable;
//	
//	@GetMapping("/course/description")
//	public ResponseEntity<?> getCoursesByDescription(@RequestParam(name = "description")String description)throws Throwable;
//	
//	@GetMapping("/course/duration")
//	public ResponseEntity<?> getCoursesByDuration(@RequestParam(name = "duration")String duration)throws Throwable;
//	
//	@GetMapping("/course/technology")
//	public ResponseEntity<?> getCoursesByTechnology(@RequestParam(name = "technology")String technology)throws Throwable;
	
}
