package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    private String firstName;
    private String lastName;
    private Integer mcgillID;
    private String mcgillEmail;
    private Set<StudentEnrollment> courseEnrollments;

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setMcgillID(Integer value) {
        this.mcgillID = value;
    }

    @Id
    public Integer getMcgillID() {
        return this.mcgillID;
    }

    public void setMcgillEmail(String value) {
        this.mcgillEmail = value;
    }

    public String getMcgillEmail() {
        return this.mcgillEmail;
    }

    @OneToMany(mappedBy = "enrolledStudent")
    public Set<StudentEnrollment> getCourseEnrollments() {
        return this.courseEnrollments;
    }
    
	public void addCourseEnrollment(StudentEnrollment se) {
		if (courseEnrollments == null)
			courseEnrollments = new HashSet<StudentEnrollment>();
		courseEnrollments.add(se);
	}

    public void setCourseEnrollments(Set<StudentEnrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

}
