package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {
	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	private Integer mcgillID;

	public void setMcgillID(Integer value) {
		this.mcgillID = value;
	}

	@Id
	public Integer getMcgillID() {
		return this.mcgillID;
	}

	private String mcgillEmail;

	public void setMcgillEmail(String value) {
		this.mcgillEmail = value;
	}

	public String getMcgillEmail() {
		return this.mcgillEmail;
	}

	private StudentEnrollment courseEnrollment;

	@OneToOne(mappedBy = "enrolledStudent", optional = false)
	public StudentEnrollment getCourseEnrollment() {
		return this.courseEnrollment;
	}

	public void setCourseEnrollment(StudentEnrollment courseEnrollment) {
		this.courseEnrollment = courseEnrollment;
	}

}
