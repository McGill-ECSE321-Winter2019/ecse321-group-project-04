package ca.mcgill.ecse321.cooperator.dto;

import java.util.HashSet;
import java.util.Set;

public class EmployerDto {

	private String name;
	private String email;
	private Set<StudentEnrollmentDto> studentEnrollments;

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

	public Set<StudentEnrollmentDto> getStudentEnrollments() {
		return this.studentEnrollments;
	}
	public void addStudentEnrollment(StudentEnrollmentDto se) {
		if (studentEnrollments == null)
			studentEnrollments = new HashSet<StudentEnrollmentDto>();
		studentEnrollments.add(se);
	}

	public void setStudentEnrollments(Set<StudentEnrollmentDto> studentEnrollments) {
		this.studentEnrollments = studentEnrollments;
	}

}
