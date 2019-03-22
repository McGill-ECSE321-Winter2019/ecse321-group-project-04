package ca.mcgill.ecse321.cooperator.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employer {

  private String name;
  private String address; 
  private String email;
  private Set<StudentEnrollment> studentEnrollments;

  public void setName(String value) {
    this.name = value;
  }

  public String getName() {
    return this.name;
  }

  public void setEmail(String value) {
    this.email = value;
  }
  
  public void setAddress(String value) {
	this.address = value;
  }
  
  public String getAddress() {
	return this.address;
  }

  @Id
  public String getEmail() {
    return this.email;
  }

  @OneToMany(mappedBy = "studentEmployer")
  public Set<StudentEnrollment> getStudentEnrollments() {
    return this.studentEnrollments;
  }

  public void addStudentEnrollment(StudentEnrollment se) {
    if (studentEnrollments == null)
      studentEnrollments = new HashSet<StudentEnrollment>();
    studentEnrollments.add(se);
  }

  public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollments) {
    this.studentEnrollments = studentEnrollments;
  }

}
