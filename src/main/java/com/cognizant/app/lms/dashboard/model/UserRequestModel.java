package com.cognizant.app.lms.dashboard.model;


public class UserRequestModel {

	private String userName;
	
	private String userEmail;
	
	private String password;
	
	private boolean userAdmin;
	
	private String roles;
	
	public UserRequestModel() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRequestModel(String userName, String userEmail, String password, boolean userAdmin, String roles) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.userAdmin = userAdmin;
		this.roles = roles;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
