package ca.mcgill.ecse321.cooperator.dto;

import java.util.HashSet;
import java.util.Set;

public class StudentDto {
	private String firstName;
	private String lastName;
	private Integer mcgillID;
	private String mcgillEmail;
	private Set<StudentEnrollmentDto> courseEnrollments;

	public StudentDto() {
	}

	public StudentDto(String firstName, String lastName, Integer mcgillID, String mcgillEmail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mcgillID = mcgillID;
		this.mcgillEmail = mcgillEmail;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Integer getMcgillID() {
		return this.mcgillID;
	}

	public String getMcgillEmail() {
		return this.mcgillEmail;
	}

	public Set<StudentEnrollmentDto> getCourseEnrollments() {
		return this.courseEnrollments;
	}
	
	public void addStudentEnrollment(StudentEnrollmentDto se) {
		if (courseEnrollments == null)
			courseEnrollments = new HashSet<StudentEnrollmentDto>();
		courseEnrollments.add(se);
	}


	public void setCourseEnrollments(Set<StudentEnrollmentDto> courseEnrollments) {
		this.courseEnrollments = courseEnrollments;
	}

}