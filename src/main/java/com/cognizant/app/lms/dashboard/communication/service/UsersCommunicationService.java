package com.cognizant.app.lms.dashboard.communication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.app.lms.dashboard.model.UserInfoUserDetails;
import com.cognizant.app.lms.dashboard.model.UserRequestModel;
import com.cognizant.app.lms.dashboard.model.UserResponseModel;
import com.cognizant.app.lms.dashboard.model.UserUpdateRequestModel;
import com.cognizant.app.lms.dashboard.communication.*;
import java.util.List;
import java.util.Map;

@Service
public class UsersCommunicationService implements UserDetailsService{

	@Autowired
	private UsersServiceFeignClient usersServiceFeignClient;
	
	public ResponseEntity<UserResponseModel> registerUser(UserRequestModel registerUser){
		return usersServiceFeignClient.createUser(registerUser);
	}
	
	public ResponseEntity<UserResponseModel> getUserById(String id){
		return usersServiceFeignClient.getUserById(id);
	}
	
	public ResponseEntity<UserResponseModel> getUserByEmail(String email){
		return usersServiceFeignClient.getUserByEmailId(email);
	}

	public ResponseEntity<List<UserResponseModel>> getAllUsers() {
		return usersServiceFeignClient.getAllUsers();
	}
	
	
	public ResponseEntity<UserResponseModel> updateUserById( UserUpdateRequestModel updateRequestModel, String id){
		return usersServiceFeignClient.updateUserById(updateRequestModel, id);
	}
	
	public ResponseEntity<Map<String, UserResponseModel>> deleteUserById(String id){
		return usersServiceFeignClient.deleteUserById(id);
	}
	
	public String deleteAllUsers() {
		return usersServiceFeignClient.deleteAllUsers();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<UserResponseModel> userDetailsReceived = usersServiceFeignClient.getUserByEmailId(username);

		
		UserInfoUserDetails infoUserDetails = new UserInfoUserDetails(userDetailsReceived.getBody());
		
//		System.out.println(infoUserDetails.getUserEmail());
//		System.out.println(infoUserDetails.getUserName());
//		System.out.println(infoUserDetails.getPassword());
//		
		
		//		UserResponseModel userResponseModel = null;
//		if(null != userDetailsReceived) {
//			userResponseModel = userDetailsReceived.getBody();
//		}
		
		return infoUserDetails;	
	}

	
}
