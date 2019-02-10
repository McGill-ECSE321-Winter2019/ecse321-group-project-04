package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Student{
   private String firstName;

private void setFirstName(String value) {
    this.firstName = value;
}
private String getFirstName() {
    return this.firstName;
}
private String lastName;

private void setLastName(String value) {
    this.lastName = value;
}
private String getLastName() {
    return this.lastName;
}
private Integer mcgillID;

private void setMcgillID(Integer value) {
    this.mcgillID = value;
}
@Id
private Integer getMcgillID() {
    return this.mcgillID;
}
private String mcgillEmail;

private void setMcgillEmail(String value) {
    this.mcgillEmail = value;
}
private String getMcgillEmail() {
    return this.mcgillEmail;
}
   private Set<StudentEnrollment> courseEnrollments;
   
   @OneToMany(mappedBy="enrolledStudent" )
   public Set<StudentEnrollment> getCourseEnrollments() {
      return this.courseEnrollments;
   }
   
   public void setCourseEnrollments(Set<StudentEnrollment> courseEnrollmentss) {
      this.courseEnrollments = courseEnrollmentss;
   }
   
   }
