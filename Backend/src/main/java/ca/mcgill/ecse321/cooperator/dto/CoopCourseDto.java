package ca.mcgill.ecse321.cooperator.dto;

import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;

public class CoopCourseDto {
	private String courseCode;
	private Integer coopTerm;
	private Set<CoopCourseOffering> coopCourseOffering;

	public CoopCourseDto() {
	}

	public CoopCourseDto(String courseCode, Integer coopTerm) {
		this.courseCode = courseCode;
		this.coopTerm = coopTerm;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public Integer getCoopTerm() {
		return this.coopTerm;
	}

	public Set<CoopCourseOffering> getCoopCourseOffering() {
		return this.coopCourseOffering;
	}

	public void setCoopCourseOffering(Set<CoopCourseOffering> coopCourseOfferings) {
		this.coopCourseOffering = coopCourseOfferings;
	}

}
