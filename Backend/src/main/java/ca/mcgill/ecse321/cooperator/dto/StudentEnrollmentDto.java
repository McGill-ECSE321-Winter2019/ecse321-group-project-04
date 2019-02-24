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
	    private EmployerDto studentEmployer;
	    private StudentDto enrolledStudent;
	    private Set<TaskDto> courseTasks;
	    private CoopCourseOfferingDto coopCourseOffering;
	    private String enrollmentID;
	    
	    public StudentEnrollmentDto() {
	    }
	    
	    public StudentEnrollmentDto(CourseStatus status, Boolean active, EmployerDto studentEmployer,
	    StudentDto enrolledStudent, CoopCourseOfferingDto cco) {
	    	
	    	this.status = status; 
	    	this.active = active; 
	    	this.studentEmployer = studentEmployer; 
	    	this.enrolledStudent = enrolledStudent; 
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
	    
	    public EmployerDto getStudentEmployer() {
	        return this.studentEmployer;
	    }
	    
	    public StudentDto getEnrolledStudent() {
	        return this.enrolledStudent;
	    }
	    
	    public CoopCourseOfferingDto getCoopCourseOffering() {
	        return this.coopCourseOffering;
	    }
	    
	    public Set<TaskDto> getCourseTasks() {
	        return this.courseTasks;
	    }

	    public void setCourseTasks(Set<TaskDto> courseTasks) {
	        this.courseTasks = courseTasks;
	    }
	    
	    public void setEnrollmentID(String value) {
	        this.enrollmentID = value;
	    }

	    public String getEnrollmentID() {
	        return this.enrollmentID;
	    }
}
