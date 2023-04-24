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
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.app.lms.dashboard.communication.service.UsersCommunicationService;
import com.cognizant.app.lms.dashboard.dto.UserDTO;
import com.cognizant.app.lms.dashboard.filter.JwtAuthFilter;
import com.cognizant.app.lms.dashboard.model.UserRequestModel;
import com.cognizant.app.lms.dashboard.model.UserResponseModel;
import com.cognizant.app.lms.dashboard.model.UserUpdateRequestModel;
import com.cognizant.app.lms.dashboard.security.JwtService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(DashboardUsersController.class)
@AutoConfigureMockMvc(addFilters = false)
class DashboardUsersControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	private UsersCommunicationService usersCommunicationService;
	
	@MockBean
	JwtAuthFilter jwtAuthFilter;
	
	@MockBean
	JwtService jwtService;
	
	UserRequestModel userRequestModel;
	UserDTO userDTO;
	UserResponseModel userResponseModel;
	UserUpdateRequestModel userUpdateRequestModel;
	
	@BeforeEach
	public void setup() {
		userRequestModel = new UserRequestModel(
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				"akshay@123",
				true,
				"ROLE_USER,ROLE_ADMIN"
				);
		
	
		
		userResponseModel = new UserResponseModel(
				"9999",
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				true,
				"encryptedpass",
				"ROLE_USER,ROLE_ADMIN"
				);
		
		
		userUpdateRequestModel = new UserUpdateRequestModel();
	}
	
	@Test
	public void testCreateUser() throws Exception{
		String registerUserURI = "/api/v1.0/user";
		
		String inputJson = mapToJson(userRequestModel);
		
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
		
		when(usersCommunicationService.registerUser(any())).thenReturn(respEntity);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(registerUserURI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andReturn();
		
		assertEquals(201, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}
	
	
	@Test
	public void testGetUserByUserId() throws Exception{
		String getUserByUserIdURI = "/api/v1.0/user/9999";
		
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
		when(usersCommunicationService.getUserById(eq("9999"))).thenReturn(respEntity);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(getUserByUserIdURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}
	
	@Test
	public void testGetAllUsers() throws Exception{
		String getAllUsersURI = "/api/v1.0/user";
		
		List<UserResponseModel> userResponseList = new ArrayList<>();
		userResponseList.add(userResponseModel);
		userResponseList.add(userResponseModel);
		ResponseEntity<List<UserResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseList);
		
		when(usersCommunicationService.getAllUsers()).thenReturn(respEntity);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(getAllUsersURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
		UserResponseModel[] userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel[].class);
		
		assertNotNull(userResponseModelResult);
	}
	
	@Test
	public void testGetUserByEmail() throws Exception{
		String getUserByEmailURI = "/api/v1.0/user/email/nilkanthakshay17@gmail.com";
		
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
		when(usersCommunicationService.getUserByEmail(eq("nilkanthakshay17@gmail.com"))).thenReturn(respEntity);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(getUserByEmailURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}
	
	
	@Test
	public void testUpdateUser() throws Exception{
		String updateUserURI = "/api/v1.0/user/9999";
		
		userUpdateRequestModel.setUserName("Akshay Nilkanth999");
		userUpdateRequestModel.setUserEmail("nilkanthakshay9999@gmail.com");
		String inputJson = mapToJson(userUpdateRequestModel);
		

		userResponseModel.setUserName("Akshay Nilkanth999");
		userResponseModel.setUserEmail("nilkanthakshay9999@gmail.com");
		
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponseModel);
		
		when(usersCommunicationService.updateUserById(any(),eq("9999"))).thenReturn(respEntity);
				
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(updateUserURI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andReturn();
		
		assertEquals(202, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
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
