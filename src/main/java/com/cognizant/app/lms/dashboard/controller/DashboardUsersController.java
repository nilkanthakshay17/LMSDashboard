package com.cognizant.app.lms.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.lms.dashboard.communication.service.UsersCommunicationService;
import com.cognizant.app.lms.dashboard.model.UserRequestModel;
import com.cognizant.app.lms.dashboard.model.UserResponseModel;
import com.cognizant.app.lms.dashboard.model.UserUpdateRequestModel;

@RestController
@RequestMapping("/api/v1.0/user")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardUsersController {

	@Autowired
	private UsersCommunicationService usersCommunicationService;
	
	@GetMapping("/status")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getStatus() {
		return "Working...";
	}
	
	@PostMapping
	public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRequestModel registerUserRequest){
		ResponseEntity<UserResponseModel> respEntity =usersCommunicationService.registerUser(registerUserRequest);
		return respEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseModel> getUserById(@PathVariable(name = "id")String id){
		return usersCommunicationService.getUserById(id);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponseModel> getUserByEmail(@PathVariable(name = "email")String email){
		return usersCommunicationService.getUserByEmail(email);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseModel>> getAllUsers(){
		return usersCommunicationService.getAllUsers();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseModel> updateUserById(@RequestBody UserUpdateRequestModel updateUserRequest, @PathVariable(name = "id")String id){
		return usersCommunicationService.updateUserById(updateUserRequest, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, UserResponseModel>> delteUserById(@PathVariable(name = "id")String id){
		return usersCommunicationService.deleteUserById(id);
	}
	
	@DeleteMapping
	public String deleteAllUsers(){
		usersCommunicationService.deleteAllUsers();
		return "Deleted All Users";
	}
}
