package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Employer {
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private String email;

	public void setEmail(String value) {
		this.email = value;
	}

	@Id
	public String getEmail() {
		return this.email;
	}

	private Set<StudentEnrollment> studentEnrollments;

	@OneToMany(mappedBy = "studentEmployer")
	public Set<StudentEnrollment> getStudentEnrollments() {
		return this.studentEnrollments;
	}

	public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollmentss) {
		this.studentEnrollments = studentEnrollmentss;
	}

}
