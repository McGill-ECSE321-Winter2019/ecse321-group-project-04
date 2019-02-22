package ca.mcgill.ecse321.cooperator.dto;

import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.Task;

public class StudentEnrollmentDto {
	
    private CourseStatus status;
    
    private Boolean active;    
    private Employer studentEmployer;
    private Student enrolledStudent;
    private Set<Task> courseTasks;
    private CoopCourseOffering coopCourseOffering;
    private String enrollmentID;
    
    public StudentEnrollmentDto() {
    }
    
    public StudentEnrollmentDto(CourseStatus status, Boolean active, Employer studentEmployer,
    Student student, CoopCourseOffering cco) {
    	
    	this.status = status; 
    	this.active = active; 
    	this.studentEmployer = studentEmployer; 
    	this.enrolledStudent = student; 
    	this.coopCourseOffering = cco; 
    	
    }
    
    public void setStatus(CourseStatus value) {
        this.status = value;
    }

    public CourseStatus getStatus() {
        return this.status;
    }
    
    public Boolean getActive() {
        return this.active;
    }
    
    public Employer getStudentEmployer() {
        return this.studentEmployer;
    }
    
    public Student getEnrolledStudent() {
        return this.enrolledStudent;
    }
    
    public CoopCourseOffering getCoopCourseOffering() {
        return this.coopCourseOffering;
    }
    
    public Set<Task> getCourseTasks() {
        return this.courseTasks;
    }

    public void setCourseTasks(Set<Task> courseTasks) {
        this.courseTasks = courseTasks;
    }
    
    public void setEnrollmentID(String value) {
        this.enrollmentID = value;
    }

    public String getEnrollmentID() {
        return this.enrollmentID;
    }

}
