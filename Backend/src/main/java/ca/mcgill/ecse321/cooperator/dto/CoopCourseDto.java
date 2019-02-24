package ca.mcgill.ecse321.cooperator.dto;

import java.util.HashSet;
import java.util.Set;

public class CoopCourseDto {
	private String courseCode;
	private Integer coopTerm;
	private Set<CoopCourseOfferingDto> coopCourseOffering;

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

	public Set<CoopCourseOfferingDto> getCoopCourseOffering() {
		return this.coopCourseOffering;
	}

	public void addCourseOffering(CoopCourseOfferingDto cco) {
		if (coopCourseOffering == null)
			coopCourseOffering = new HashSet<CoopCourseOfferingDto>();
		coopCourseOffering.add(cco);
	}

	public void setCoopCourseOffering(Set<CoopCourseOfferingDto> coopCourseOfferings) {
		this.coopCourseOffering = coopCourseOfferings;
	}

}
