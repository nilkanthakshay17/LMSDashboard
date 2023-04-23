package com.cognizant.app.lms.dashboard.model;



public class CourseRequestModel {

	private String courseName;
	
	private String courseDuration;
	
	private String courseDescription;
	
	private String technology;
	
	private String launchURL;

	public CourseRequestModel() {
		// TODO Auto-generated constructor stub
	}
	
	public CourseRequestModel(String courseName, String courseDuration, String courseDescription, String technology,
			String launchURL) {
		super();
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.technology = technology;
		this.launchURL = launchURL;
	}



	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getLaunchURL() {
		return launchURL;
	}

	public void setLaunchURL(String launchURL) {
		this.launchURL = launchURL;
	}
	
}
