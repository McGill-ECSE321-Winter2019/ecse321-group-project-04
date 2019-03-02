package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;

@Entity
public class CoopCourse {
	
	private String courseCode;
	private Integer coopTerm;
	private Set<CoopCourseOffering> coopCourseOffering;

	public void setCourseCode(String value) {
		this.courseCode = value;
	}

	@Id
	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCoopTerm(Integer value) {
		this.coopTerm = value;
	}

	public Integer getCoopTerm() {
		return this.coopTerm;
	}

	@OneToMany(mappedBy = "coopCourse")
	public Set<CoopCourseOffering> getCoopCourseOffering() {
		return this.coopCourseOffering;
	}

	public void addCourseOffering(CoopCourseOffering cco) {
		if (coopCourseOffering == null)
			coopCourseOffering = new HashSet<CoopCourseOffering>();
		coopCourseOffering.add(cco);
	}

	public void setCoopCourseOffering(Set<CoopCourseOffering> coopCourseOfferings) {
		this.coopCourseOffering = coopCourseOfferings;
	}

}
