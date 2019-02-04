package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class CoopCourseOffering {
	private Integer year;

	public void setYear(Integer value) {
		this.year = value;
	}

	public Integer getYear() {
		return this.year;
	}

	private Term term;

	public void setTerm(Term value) {
		this.term = value;
	}

	public Term getTerm() {
		return this.term;
	}

	private Boolean active;

	public void setActive(Boolean value) {
		this.active = value;
	}

	public Boolean getActive() {
		return this.active;
	}

	private Set<StudentEnrollment> studentEnrollments;

	@OneToMany(mappedBy = "coopCourseOffering", cascade = { CascadeType.ALL })
	public Set<StudentEnrollment> getStudentEnrollments() {
		return this.studentEnrollments;
	}

	public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollmentss) {
		this.studentEnrollments = studentEnrollmentss;
	}

	private CoopCourse coopCourse;

	@ManyToOne(optional = false)
	public CoopCourse getCoopCourse() {
		return this.coopCourse;
	}

	public void setCoopCourse(CoopCourse coopCourse) {
		this.coopCourse = coopCourse;
	}

	private Set<Document> documents;

	@OneToMany(mappedBy = "coopCourseOffering")
	public Set<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(Set<Document> documentss) {
		this.documents = documentss;
	}

	private String offerID;

	public void setOfferID(String value) {
		this.offerID = value;
	}

	@Id
	public String getOfferID() {
		return this.offerID;
	}
}
