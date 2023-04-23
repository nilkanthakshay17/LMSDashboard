package com.cognizant.app.lms.dashboard.communication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.app.lms.dashboard.communication.CoursesServiceFeignClient;
import com.cognizant.app.lms.dashboard.model.CourseRequestModel;
import com.cognizant.app.lms.dashboard.model.CourseResponseModel;

@SpringBootTest
class CoursesCommunicationServiceTest {

	@MockBean
	private CoursesServiceFeignClient coursesServiceFeignClient;
	
	CourseRequestModel courseRequestModel;
	CourseResponseModel courseResponseModel;
	
	@BeforeEach
	public void setUp() {
		courseRequestModel = new CourseRequestModel(
				"DAC-Digital Advanced Computing",
				"9999",
				"This course contains all the latest technologies and related stack for advanced computing",
				"Java999",
				"www.dac999.com");
		
		courseResponseModel = new CourseResponseModel(
				"9999",
				"DAC-Digital Advanced Computing",
				"999",
				"This course contains all the latest technologies and related stack for advanced computing",
				"Java999",
				"www.dac999.com");
	}
	
	@Test
	public void testRegisterCourse() throws Throwable {
		ResponseEntity<CourseResponseModel> respEntity = ResponseEntity.status(HttpStatus.CREATED).body(courseResponseModel);
		when(coursesServiceFeignClient.registerCourse(any())).thenReturn(respEntity);
		
		ResponseEntity<CourseResponseModel> receivedRespEntity= coursesServiceFeignClient.registerCourse(courseRequestModel);

		assertEquals(respEntity.getBody().getCourseId(), receivedRespEntity.getBody().getCourseId());
		assertEquals(respEntity.getBody().getCourseName(), receivedRespEntity.getBody().getCourseName());
		assertEquals(respEntity.getBody().getCourseDescription(), receivedRespEntity.getBody().getCourseDescription());
		assertEquals(respEntity.getBody().getCourseDuration(), receivedRespEntity.getBody().getCourseDuration());
		assertEquals(respEntity.getBody().getTechnology(), receivedRespEntity.getBody().getTechnology());
		assertEquals(respEntity.getBody().getLaunchURL(), receivedRespEntity.getBody().getLaunchURL());
	}

	@Test
	public void testGetCourseByCourseId() throws Throwable {
		ResponseEntity<CourseResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(courseResponseModel);
		when(coursesServiceFeignClient.getCourseById(eq("9999"))).thenReturn(respEntity);
		
		ResponseEntity<CourseResponseModel> receivedRespEntity= coursesServiceFeignClient.getCourseById("9999");

		assertEquals(respEntity.getBody().getCourseId(), receivedRespEntity.getBody().getCourseId());
		assertEquals(respEntity.getBody().getCourseName(), receivedRespEntity.getBody().getCourseName());
		assertEquals(respEntity.getBody().getCourseDescription(), receivedRespEntity.getBody().getCourseDescription());
		assertEquals(respEntity.getBody().getCourseDuration(), receivedRespEntity.getBody().getCourseDuration());
		assertEquals(respEntity.getBody().getTechnology(), receivedRespEntity.getBody().getTechnology());
		assertEquals(respEntity.getBody().getLaunchURL(), receivedRespEntity.getBody().getLaunchURL());
	}
	
	@Test
	public void testGetAllCourses() throws Throwable {
		List<CourseResponseModel> allCoursesResponse = new ArrayList<>();
		allCoursesResponse.add(courseResponseModel);
		allCoursesResponse.add(courseResponseModel);
		
		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(allCoursesResponse);
		when(coursesServiceFeignClient.getAllCourses()).thenReturn(respEntity);
		
		ResponseEntity<List<CourseResponseModel>> receivedRespEntity= coursesServiceFeignClient.getAllCourses();

		assertNotNull(receivedRespEntity);
		assertEquals(2, receivedRespEntity.getBody().size());
	}
	
	@Test
	public void testUpdateCourse() throws Throwable {
		
		courseRequestModel.setCourseName("DMC-Digital Mobile Computing");
		courseRequestModel.setCourseDuration("12");
		
		courseResponseModel.setCourseName("DMC-Digital Mobile Computing");
		courseResponseModel.setCourseDuration("12");
		
		ResponseEntity<CourseResponseModel> respEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(courseResponseModel);
		when(coursesServiceFeignClient.updateCourseById(eq(courseRequestModel), eq("9999"))).thenReturn(respEntity);
		
		ResponseEntity<CourseResponseModel> receivedRespEntity= coursesServiceFeignClient.updateCourseById(courseRequestModel, "9999");

		assertEquals(respEntity.getBody().getCourseId(), receivedRespEntity.getBody().getCourseId());
		assertEquals(respEntity.getBody().getCourseName(), receivedRespEntity.getBody().getCourseName());
		assertEquals(respEntity.getBody().getCourseDescription(), receivedRespEntity.getBody().getCourseDescription());
		assertEquals(respEntity.getBody().getCourseDuration(), receivedRespEntity.getBody().getCourseDuration());
		assertEquals(respEntity.getBody().getTechnology(), receivedRespEntity.getBody().getTechnology());
		assertEquals(respEntity.getBody().getLaunchURL(), receivedRespEntity.getBody().getLaunchURL());
	}
	
	@Test
	public void testGetCourseByName() throws Throwable{
		
		List<CourseResponseModel> allCoursesResponse = new ArrayList<>();
		allCoursesResponse.add(courseResponseModel);
		allCoursesResponse.add(courseResponseModel);
		
		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(allCoursesResponse);
		when(coursesServiceFeignClient.getCourseInfo(eq("name"),eq("dac"))).thenReturn(respEntity);
		
		ResponseEntity<List<CourseResponseModel>> receivedRespEntity= coursesServiceFeignClient.getCourseInfo("name", "dac");

		assertNotNull(receivedRespEntity);
		assertEquals(2, receivedRespEntity.getBody().size());
	}
	
	@Test
	public void testGetCourseByDescription() throws Throwable{
		
		List<CourseResponseModel> allCoursesResponse = new ArrayList<>();
		allCoursesResponse.add(courseResponseModel);
		allCoursesResponse.add(courseResponseModel);
		
		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(allCoursesResponse);
		when(coursesServiceFeignClient.getCourseInfo(eq("description"),eq("technologies"))).thenReturn(respEntity);
		
		ResponseEntity<List<CourseResponseModel>> receivedRespEntity= coursesServiceFeignClient.getCourseInfo("description", "technologies");

		assertNotNull(receivedRespEntity);
		assertEquals(2, receivedRespEntity.getBody().size());
	}
	
	@Test
	public void testGetCourseByDuration() throws Throwable{
		
		List<CourseResponseModel> allCoursesResponse = new ArrayList<>();
		allCoursesResponse.add(courseResponseModel);
		allCoursesResponse.add(courseResponseModel);
		
		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(allCoursesResponse);
		when(coursesServiceFeignClient.getCourseInfo(eq("duration"),eq("9999"))).thenReturn(respEntity);
		
		ResponseEntity<List<CourseResponseModel>> receivedRespEntity= coursesServiceFeignClient.getCourseInfo("duration", "9999");

		assertNotNull(receivedRespEntity);
		assertEquals(2, receivedRespEntity.getBody().size());
	}
	
	@Test
	public void testGetCourseByTechnology() throws Throwable{
		
		List<CourseResponseModel> allCoursesResponse = new ArrayList<>();
		allCoursesResponse.add(courseResponseModel);
		allCoursesResponse.add(courseResponseModel);
		
		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(allCoursesResponse);
		when(coursesServiceFeignClient.getCourseInfo(eq("technology"),eq("Java999"))).thenReturn(respEntity);
		
		ResponseEntity<List<CourseResponseModel>> receivedRespEntity= coursesServiceFeignClient.getCourseInfo("technology", "Java999");

		assertNotNull(receivedRespEntity);
		assertEquals(2, receivedRespEntity.getBody().size());
	}
}
