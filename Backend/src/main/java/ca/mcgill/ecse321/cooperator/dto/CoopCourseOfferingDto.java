package ca.mcgill.ecse321.cooperator.dto;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Term;

public class CoopCourseOfferingDto {

	private Integer year;
	private Term term;
	private Boolean active;
	private Set<StudentEnrollment> studentEnrollments;
	private CoopCourse coopCourse;
	private String offerID;

	public CoopCourseOfferingDto() {
	}

	public CoopCourseOfferingDto(Integer year, Term term, Boolean active, CoopCourse coopCourse) {
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

	public CoopCourse getCoopCourse() {
		return this.coopCourse;
	}

	public Set<StudentEnrollment> getStudentEnrollments() {
		return this.studentEnrollments;
	}

	public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollmentss) {
		this.studentEnrollments = studentEnrollmentss;
	}

	public void addStudentEnrollment(StudentEnrollment se) {
		if (studentEnrollments == null)
			studentEnrollments = new HashSet<StudentEnrollment>();
		studentEnrollments.add(se);
	}

	public void setOfferID(String value) {
		this.offerID = value;
	}

	public String getOfferID() {
		return this.offerID;
	}

}
