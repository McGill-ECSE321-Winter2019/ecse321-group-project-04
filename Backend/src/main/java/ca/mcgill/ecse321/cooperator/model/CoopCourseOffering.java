package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.util.HashSet;

@Entity
public class CoopCourseOffering {
	
	@Enumerated(EnumType.STRING)
	private Term term;
	
	private Integer year;
	private Boolean active;
	private Set<StudentEnrollment> studentEnrollments;
	private CoopCourse coopCourse;
	private String offerID;

	public void setTerm(Term value) {
		this.term = value;
	}

	public Term getTerm() {
		return this.term;
	}

	public void setYear(Integer value) {
		this.year = value;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setActive(Boolean value) {
		this.active = value;
	}

	public Boolean getActive() {
		return this.active;
	}

	@OneToMany(mappedBy = "coopCourseOffering", cascade = { CascadeType.ALL })
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

	@ManyToOne(optional = false)
	public CoopCourse getCoopCourse() {
		return this.coopCourse;
	}

	public void setCoopCourse(CoopCourse coopCourse) {
		this.coopCourse = coopCourse;
	}

	public void setOfferID(String value) {
		this.offerID = value;
	}

        public void setOfferID(CoopCourse cc, Term term, Integer year) {
                String offerID = coopCourse.getCourseCode();
                switch (term) {
                case FALL:
                        offerID += "-F";
                        break;
                case WINTER:
                        offerID += "-W";
                        break;
                case SUMMER:
                        offerID += "-S";
                        break;
                }
                offerID += year % 2000;

                this.offerID = offerID;
        }

	@Id
	public String getOfferID() {
		return this.offerID;
	}
}
