package com.cognizant.app.lms.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.lms.dashboard.communication.service.CoursesCommunicationService;
import com.cognizant.app.lms.dashboard.model.CourseRequestModel;
import com.cognizant.app.lms.dashboard.model.CourseResponseModel;
import com.cognizant.app.lms.dashboard.util.CoursesConstant;


@RestController
@RequestMapping("/api/v1.0/course")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardCoursesController {

	@Autowired
	private CoursesCommunicationService coursesCommunicationService;
	
	@GetMapping("/status")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getStatus() {
		return "Working...";
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<CourseResponseModel> registerCourse(@RequestBody CourseRequestModel registerRequest)throws Throwable{
		return coursesCommunicationService.registerCourse(registerRequest);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<CourseResponseModel>> getAllCourses()throws Throwable{
		return coursesCommunicationService.getAllCourses();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<CourseResponseModel> getCourseById(@PathVariable(name = "id")String id)throws Throwable{
		return coursesCommunicationService.getCourseById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<CourseResponseModel> updateCourseById(@RequestBody CourseRequestModel updateRequest, @PathVariable(name = "id")String id)throws Throwable{
		return coursesCommunicationService.updateCourseById(updateRequest, id);
	}
	
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteAllCourses()throws Throwable{
		return coursesCommunicationService.deleteAllCourses();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Map<String, CourseResponseModel>> deleteCourseById(@PathVariable(name = "id") String id)throws Throwable{
		return coursesCommunicationService.deleteCourseById(id);
	}
	
	@GetMapping("/loggedInUser")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getLoggedInUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName()+
				SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	
	
//	@GetMapping("/name")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public ResponseEntity<?> getCourseInfoByName( @RequestParam(name = "name")String name)throws Throwable{
//	 return coursesCommunicationService.getCourseByName(name);
//	}
//	
//	@GetMapping("/description")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public ResponseEntity<?> getCourseInfoByDescription( @RequestParam(name = "description")String description)throws Throwable{
//	 return coursesCommunicationService.getCourseByDescription(description);
//		
//	}
//	
//	@GetMapping("/duration")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public ResponseEntity<?> getCourseInfoByDuration( @RequestParam(name = "duration")String duration)throws Throwable{
//	 return coursesCommunicationService.getCourseByDuration(duration);
//		
//	}
//	
//	@GetMapping("/technology")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public ResponseEntity<?> getCourseInfoByTechnology( @RequestParam(name = "technology")String technology)throws Throwable{
//	 return coursesCommunicationService.getCourseTechnology(technology);
//		
//	}
	
	@GetMapping("/{course_param}/{search_param}")
	public ResponseEntity<List<CourseResponseModel>> getCoursesInfo(@PathVariable(name = "course_param")String courseParam, @PathVariable(name = "search_param")String searchParam) throws Throwable{
		return coursesCommunicationService.getCoursesInfo(courseParam, searchParam);
	}

}
