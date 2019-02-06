package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.util.HashSet;

@Entity
public class CoopCourseOffering{

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

    @OneToMany(mappedBy="coopCourseOffering" , cascade={CascadeType.ALL})
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

    @ManyToOne(optional=false)
    public CoopCourse getCoopCourse() {
        return this.coopCourse;
    }

    public void setCoopCourse(CoopCourse coopCourse) {
        this.coopCourse = coopCourse;
    }

    public void setOfferID(String value) {
        this.offerID = value;
    }

    @Id
    public String getOfferID() {
        return this.offerID;
    }
}
