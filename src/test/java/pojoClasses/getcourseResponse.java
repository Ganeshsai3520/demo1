package pojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class getcourseResponse {
	
	private String url;
	private String services;
	private String expertise;
	private courses courses;
	private String instructor;
	private String linkedIn;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public courses getCourses() {
		return courses;
	}
	public void setCourses(courses courses) {
		this.courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedin() {
		return linkedIn;
	}
	public void setLinkedin(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}

