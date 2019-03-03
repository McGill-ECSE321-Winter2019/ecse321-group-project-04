package ca.mcgill.ecse321.cooperator.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

  @OneToMany(mappedBy = "coopCourseOffering", cascade = {CascadeType.ALL})
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

  // This method requires that the instance already has
  // populated coopCourse, term and year attributes
  public void setOfferID() {
    if (this.coopCourse == null || this.term == null || this.year == null) {
      throw new IllegalArgumentException(
          "The student enrollment must have a term and a year to generate the ID.");
    }

    String offerID = coopCourse.getCourseCode();
    switch (this.term) {
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
    offerID += this.year % 2000;

    this.offerID = offerID;
  }

  @Id
  public String getOfferID() {
    return this.offerID;
  }
}
