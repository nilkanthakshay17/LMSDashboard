package com.cognizant.app.lms.dashboard.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoUserDetails implements UserDetails{

private String userId;
	
	private String userName;
	
	private String userEmail;
	
	private String encryptedPassword;

	private List<GrantedAuthority> authorities;
	
	
	public UserInfoUserDetails(UserResponseModel userResponseModel){
		this.userId = userResponseModel.getUserId();
		this.userName = userResponseModel.getUserName();
		this.userEmail = userResponseModel.getUserEmail();
		this.encryptedPassword = userResponseModel.getEncryptedPassword();
		this.authorities = Arrays.stream(userResponseModel.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.encryptedPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
