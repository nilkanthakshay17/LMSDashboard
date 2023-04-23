package com.cognizant.app.lms.dashboard.dto;

public class CourseDTO {
	private String courseId;
	
	private String courseName;
	
	private String courseDuration;
	
	private String courseDescription;
	
	private String technology;
	
	private String launchURL;

	public CourseDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CourseDTO(String courseId, String courseName, String courseDuration, String courseDescription,
			String technology, String launchURL) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.technology = technology;
		this.launchURL = launchURL;
	}



	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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
