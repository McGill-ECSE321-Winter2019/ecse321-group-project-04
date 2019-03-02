package ca.mcgill.ecse321.cooperator.model;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

public class Cooperator {
	private Set<CoopCourseOffering> coopCourseOffering;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<CoopCourseOffering> getCoopCourseOffering() {
		return this.coopCourseOffering;
	}

	public void setCoopCourseOffering(Set<CoopCourseOffering> coopCourseOfferings) {
		this.coopCourseOffering = coopCourseOfferings;
	}

	private Set<Student> student;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

}