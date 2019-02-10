package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class CoopCourse{
   private String courseCode;

private void setCourseCode(String value) {
    this.courseCode = value;
}
private String getCourseCode() {
    return this.courseCode;
}
private Integer coopTerm;

private void setCoopTerm(Integer value) {
    this.coopTerm = value;
}
private Integer getCoopTerm() {
    return this.coopTerm;
}
private Set<CoopCourseOffering> coopCourseOffering;

@OneToMany(mappedBy="coopCourse" )
public Set<CoopCourseOffering> getCoopCourseOffering() {
   return this.coopCourseOffering;
}

public void setCoopCourseOffering(Set<CoopCourseOffering> coopCourseOfferings) {
   this.coopCourseOffering = coopCourseOfferings;
}

private String coopCourseID;

private void setCoopCourseID(String value) {
    this.coopCourseID = value;
}
@Id
private String getCoopCourseID() {
    return this.coopCourseID;
}
}
