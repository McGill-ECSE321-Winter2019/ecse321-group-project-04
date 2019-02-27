package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class StudentEnrollment {

    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    
    private Boolean active;    
    private Employer studentEmployer;
    private Student enrolledStudent;
    private Set<Task> courseTasks;
    private CoopCourseOffering coopCourseOffering;
    private String enrollmentID;

    public void setActive(Boolean value) {
        this.active = value;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setStatus(CourseStatus value) {
        this.status = value;
    }

    public CourseStatus getStatus() {
        return this.status;
    }

    @ManyToOne(optional = false)
    public Employer getStudentEmployer() {
        return this.studentEmployer;
    }

    public void setStudentEmployer(Employer studentEmployer) {
        this.studentEmployer = studentEmployer;
    }

    @ManyToOne(optional = false)
    public Student getEnrolledStudent() {
        return this.enrolledStudent;
    }

    public void setEnrolledStudent(Student enrolledStudent) {
        this.enrolledStudent = enrolledStudent;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    public Set<Task> getCourseTasks() {
        return this.courseTasks;
    }

    public void addCourseTasks(Task t) {
            if (courseTasks == null)
                    courseTasks = new HashSet<Task>();
            courseTasks.add(t);
    }

    public void setCourseTasks(Set<Task> courseTasks) {
        this.courseTasks = courseTasks;
    }

    @ManyToOne(optional = false)
    public CoopCourseOffering getCoopCourseOffering() {
        return this.coopCourseOffering;
    }

    public void setCoopCourseOffering(CoopCourseOffering coopCourseOffering) {
        this.coopCourseOffering = coopCourseOffering;
    }

    public void setEnrollmentID(String value) {
        this.enrollmentID = value;
    }

    public void setEnrollmentID(Student s, CoopCourseOffering cco) {
        this.enrollmentID = s.getMcgillID() + "-" + cco.getOfferID();
    }

    @Id
    public String getEnrollmentID() {
        return this.enrollmentID;
    }

}
