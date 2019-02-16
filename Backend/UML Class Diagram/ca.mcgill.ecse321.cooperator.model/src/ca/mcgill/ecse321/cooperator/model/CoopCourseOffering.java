package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class CoopCourseOffering{
private Term term;

private void setTerm(Term value) {
this.term = value;
}
private Term getTerm() {
return this.term;
}
   private Integer year;

private void setYear(Integer value) {
    this.year = value;
}
private Integer getYear() {
    return this.year;
}
private Boolean active;

private void setActive(Boolean value) {
    this.active = value;
}
private Boolean getActive() {
    return this.active;
}
private Set<StudentEnrollment> studentEnrollments;

@OneToMany(mappedBy="coopCourseOffering" , cascade={CascadeType.ALL})
public Set<StudentEnrollment> getStudentEnrollments() {
   return this.studentEnrollments;
}

public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollmentss) {
   this.studentEnrollments = studentEnrollmentss;
}

private CoopCourse coopCourse;

@ManyToOne(optional=false)
public CoopCourse getCoopCourse() {
   return this.coopCourse;
}

public void setCoopCourse(CoopCourse coopCourse) {
   this.coopCourse = coopCourse;
}

private String offerID;

private void setOfferID(String value) {
    this.offerID = value;
}
@Id
private String getOfferID() {
    return this.offerID;
}
}
