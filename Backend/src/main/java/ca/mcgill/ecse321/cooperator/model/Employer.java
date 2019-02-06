package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Employer {

    private String name;
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

    @Id
    public String getEmail() {
        return this.email;
    }

    @OneToMany(mappedBy = "studentEmployer")
    public Set<StudentEnrollment> getStudentEnrollments() {
        return this.studentEnrollments;
    }

    public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
    }

}
