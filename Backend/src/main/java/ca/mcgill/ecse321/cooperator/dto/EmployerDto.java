package ca.mcgill.ecse321.cooperator.dto;

import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;

public class EmployerDto {

	private String name;
	private String email;
	private Set<StudentEnrollment> studentEnrollments;

	public EmployerDto() {
	}

	public EmployerDto(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public Set<StudentEnrollment> getStudentEnrollments() {
		return this.studentEnrollments;
	}

	public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollments) {
		this.studentEnrollments = studentEnrollments;
	}

}
