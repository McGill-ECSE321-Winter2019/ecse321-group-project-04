package ca.mcgill.ecse321.cooperator.dto;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.Term;

public class CoopCourseOfferingDto {

	private Integer year;
	private Term term;
	private Boolean active;
	private Set<StudentEnrollmentDto> studentEnrollments;
	private CoopCourseDto coopCourse;
	private String offerID;

	public CoopCourseOfferingDto() {
	}

	public CoopCourseOfferingDto(Integer year, Term term, Boolean active, CoopCourseDto coopCourse) {
		this.year = year;
		this.term = term;
		this.active = active;
		this.coopCourse = coopCourse;
	}

	public Term getTerm() {
		return this.term;
	}

	public Integer getYear() {
		return this.year;
	}

	public Boolean getActive() {
		return this.active;
	}

	public CoopCourseDto getCoopCourse() {
		return this.coopCourse;
	}

	public Set<StudentEnrollmentDto> getStudentEnrollments() {
		return this.studentEnrollments;
	}

	public void setStudentEnrollments(Set<StudentEnrollmentDto> studentEnrollments) {
		this.studentEnrollments = studentEnrollments;
	}

	public void addStudentEnrollment(StudentEnrollmentDto se) {
		if (studentEnrollments == null)
			studentEnrollments = new HashSet<StudentEnrollmentDto>();
		studentEnrollments.add(se);
	}

	public void setOfferID(String value) {
		this.offerID = value;
	}

	public String getOfferID() {
		return this.offerID;
	}

}
