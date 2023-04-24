package com.cognizant.app.lms.dashboard.communication;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.lms.dashboard.model.UserResponseModel;

import com.cognizant.app.lms.dashboard.model.UserUpdateRequestModel;
import com.cognizant.app.lms.dashboard.model.UserRequestModel;


@FeignClient("lmsusers")
public interface UsersServiceFeignClient {

	//Create
	@PostMapping("/user")
	public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel createRequest);
	
	//Get
	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponseModel> getUserById(@PathVariable(name = "id")String id );

	@GetMapping("/user/email/{email}")
	public ResponseEntity<UserResponseModel> getUserByEmailId(@PathVariable(name = "email")String email);

	@GetMapping("/user")
	public ResponseEntity<List<UserResponseModel>> getAllUsers();
	
	//Update
	@PutMapping("/user/{id}")
	public ResponseEntity<UserResponseModel> updateUserById(@RequestBody UserUpdateRequestModel updateRequestModel,@PathVariable(name = "id")String id);
	
	//Delete
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map<String, UserResponseModel>> deleteUserById(@PathVariable(name = "id")String id);
	
	@DeleteMapping("/user")
	public String deleteAllUsers();
}
