package com.cognizant.app.lms.dashboard.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.app.lms.dashboard.communication.service.CoursesCommunicationService;
import com.cognizant.app.lms.dashboard.filter.JwtAuthFilter;
import com.cognizant.app.lms.dashboard.model.CourseRequestModel;
import com.cognizant.app.lms.dashboard.model.CourseResponseModel;
import com.cognizant.app.lms.dashboard.security.JwtService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DashboardCoursesController.class)
@AutoConfigureMockMvc(addFilters = false)
class DashboardCoursesControllerTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CoursesCommunicationService coursesCommunicationService;

	@MockBean
	JwtAuthFilter jwtAuthFilter;

	@MockBean
	JwtService jwtService;

	@Autowired
	ObjectMapper objectMapper;

	CourseRequestModel courseRequestModel;

	CourseResponseModel courseResponseModel;

	@BeforeEach
	public void setUp() {
		courseRequestModel = new CourseRequestModel("DMC-Digital Mobile Computing", "8",
				"Course includes advance technologies that are being used mainly for android and ios development, also includes the IOT hands-on training",
				"Android", "www.dmc.com");

		courseResponseModel = new CourseResponseModel("9999", "DMC-Digital Mobile Computing",
				"Course includes advance technologies that are being used mainly for android and ios development, also includes the IOT hands-on training",
				"8", "Android", "www.dmc.com");
	}

	@Test
	public void testRegisterCourse() throws Throwable {

		String registerCourseURI = "/api/v1.0/course";

		String inputJson = mapToJson(courseRequestModel);

		ResponseEntity<CourseResponseModel> respEntity = ResponseEntity.status(HttpStatus.CREATED)
				.body(courseResponseModel);

		when(coursesCommunicationService.registerCourse(any())).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(registerCourseURI)
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Register Course Response:{}", responseModel);

		CourseResponseModel receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<CourseResponseModel>() {
				});
		assertNotNull(receivedRespEntity);
		
		logger.info("Register Course Response:{}", receivedRespEntity);
		
		assertEquals(respEntity.getBody().getCourseId(), receivedRespEntity.getCourseId());
		assertEquals(respEntity.getBody().getCourseName(), receivedRespEntity.getCourseName());
		assertEquals(respEntity.getBody().getCourseDescription(), receivedRespEntity.getCourseDescription());
		assertEquals(respEntity.getBody().getCourseDuration(), receivedRespEntity.getCourseDuration());
		assertEquals(respEntity.getBody().getTechnology(), receivedRespEntity.getTechnology());
		assertEquals(respEntity.getBody().getLaunchURL(), receivedRespEntity.getLaunchURL());

	}

	@Test
	public void testGetAllCourses() throws Throwable {

		String getAllCourseDURI = "/api/v1.0/course";

		List<CourseResponseModel> allResponseCourses = new ArrayList<>();
		allResponseCourses.add(courseResponseModel);
		allResponseCourses.add(courseResponseModel);

		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK)
				.body(allResponseCourses);

		when(coursesCommunicationService.getAllCourses()).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(getAllCourseDURI).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Get All Courses Response:{}", responseModel);

		List<CourseResponseModel> receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<List<CourseResponseModel>>() {
				});

		assertNotNull(receivedRespEntity);
		
		logger.info("Get All Courses Response size:{}", receivedRespEntity.size());

	}

	@Test
	public void testGetCourseByCourseId() throws Throwable {

		String getCourseByCourseIDURI = "/api/v1.0/course/9999";

		ResponseEntity<CourseResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(courseResponseModel);

		when(coursesCommunicationService.getCourseById("9999")).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(getCourseByCourseIDURI).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Get Course By Id Response:{}", responseModel);

		CourseResponseModel receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<CourseResponseModel>() {
				});
		assertNotNull(receivedRespEntity);
		

		logger.info("Get Course By Id Response:{}", receivedRespEntity);

		assertEquals(respEntity.getBody().getCourseId(), receivedRespEntity.getCourseId());
		assertEquals(respEntity.getBody().getCourseName(), receivedRespEntity.getCourseName());
		assertEquals(respEntity.getBody().getCourseDescription(), receivedRespEntity.getCourseDescription());
		assertEquals(respEntity.getBody().getCourseDuration(), receivedRespEntity.getCourseDuration());
		assertEquals(respEntity.getBody().getTechnology(), receivedRespEntity.getTechnology());
		assertEquals(respEntity.getBody().getLaunchURL(), receivedRespEntity.getLaunchURL());
	}

	@Test
	public void testUpdateCourse() throws Throwable {

		String updateCourseURI = "/api/v1.0/course/9999";

		courseRequestModel.setCourseName("DAC-Digital Advanced Computing");
		courseRequestModel.setCourseDuration("12");
		courseRequestModel.setTechnology("Java");
		courseRequestModel.setLaunchURL("www.dac.com");

		String inputJson = mapToJson(courseRequestModel);

		courseResponseModel.setCourseName("DAC-Digital Advanced Computing");
		courseResponseModel.setCourseDuration("12");
		courseResponseModel.setTechnology("Java");
		courseResponseModel.setLaunchURL("www.dac.com");

		ResponseEntity<CourseResponseModel> respEntity = ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(courseResponseModel);

		when(coursesCommunicationService.updateCourseById(any(), eq("9999"))).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put(updateCourseURI).contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();

		assertEquals(202, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Update Course Response:{}", responseModel);

		CourseResponseModel receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<CourseResponseModel>() {
				});

		assertNotNull(receivedRespEntity);
		
		logger.info("Update Course Response:{}", receivedRespEntity);
		
		assertEquals(respEntity.getBody().getCourseId(), receivedRespEntity.getCourseId());
		assertEquals(respEntity.getBody().getCourseName(), receivedRespEntity.getCourseName());
		assertEquals(respEntity.getBody().getCourseDescription(), receivedRespEntity.getCourseDescription());
		assertEquals(respEntity.getBody().getCourseDuration(), receivedRespEntity.getCourseDuration());
		assertEquals(respEntity.getBody().getTechnology(), receivedRespEntity.getTechnology());
		assertEquals(respEntity.getBody().getLaunchURL(), receivedRespEntity.getLaunchURL());
	}

	@Test
	public void testGetCourseByCourseName_success() throws Throwable {

		String getCourseByCourseNameURI = "/api/v1.0/course/name/dmc";

		List<CourseResponseModel> allResponseCourses = new ArrayList<>();
		allResponseCourses.add(courseResponseModel);
		allResponseCourses.add(courseResponseModel);

		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK)
				.body(allResponseCourses);

		when(coursesCommunicationService.getCoursesInfo(eq("name"), eq("dmc"))).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(getCourseByCourseNameURI).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Get Course By Name Response:{}", responseModel);

		List<CourseResponseModel> receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<List<CourseResponseModel>>() {
				});

		assertNotNull(receivedRespEntity);
		
		logger.info("Get Course By Name Response size:{}", receivedRespEntity.size());

	}

	@Test
	public void testGetCourseByCourseDescription_success() throws Throwable {

		String getCourseByCourseDescriptionURI = "/api/v1.0/course/description/technologies";

		List<CourseResponseModel> allResponseCourses = new ArrayList<>();
		allResponseCourses.add(courseResponseModel);
		allResponseCourses.add(courseResponseModel);

		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK)
				.body(allResponseCourses);

		when(coursesCommunicationService.getCoursesInfo(eq("description"), eq("technologies"))).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(getCourseByCourseDescriptionURI).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Get Course By Description Response:{}", responseModel);

		List<CourseResponseModel> receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<List<CourseResponseModel>>() {
				});

		assertNotNull(receivedRespEntity);

		logger.info("Get Course By Description Response size:{}", receivedRespEntity.size());

	}

	@Test
	public void testGetCourseByCourseDuration_success() throws Throwable {

		String getCourseByCourseDurationURI = "/api/v1.0/course/duration/8";

		List<CourseResponseModel> allResponseCourses = new ArrayList<>();
		allResponseCourses.add(courseResponseModel);
		allResponseCourses.add(courseResponseModel);

		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK)
				.body(allResponseCourses);

		when(coursesCommunicationService.getCoursesInfo(eq("duration"), eq("8"))).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(getCourseByCourseDurationURI).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Get Course By Duration Response:{}", responseModel);

		List<CourseResponseModel> receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<List<CourseResponseModel>>() {
				});

		assertNotNull(receivedRespEntity);

		logger.info("Get Course By Duration Response:{}", receivedRespEntity.size());

	}

	@Test
	public void testGetCourseByCourseTechnology_success() throws Throwable {

		String getCourseByCourseNameURI = "/api/v1.0/course/technology/Android";

		List<CourseResponseModel> allResponseCourses = new ArrayList<>();
		allResponseCourses.add(courseResponseModel);
		allResponseCourses.add(courseResponseModel);

		ResponseEntity<List<CourseResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK)
				.body(allResponseCourses);

		when(coursesCommunicationService.getCoursesInfo(eq("technology"), eq("Android"))).thenReturn(respEntity);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(getCourseByCourseNameURI).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String responseModel = mvcResult.getResponse().getContentAsString();

		assertNotNull(responseModel);

		logger.info("Get Course By Technology Response:{}", responseModel);

		List<CourseResponseModel> receivedRespEntity = objectMapper.readValue(responseModel,
				new TypeReference<List<CourseResponseModel>>() {
				});

		assertNotNull(receivedRespEntity);
		logger.info("Get Course By Technology Response size:{}", receivedRespEntity.size());

	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
