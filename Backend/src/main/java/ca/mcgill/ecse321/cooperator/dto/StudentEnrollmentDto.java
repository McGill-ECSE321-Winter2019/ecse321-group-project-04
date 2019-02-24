package ca.mcgill.ecse321.cooperator.dto;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.CourseStatus;

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
	    
		public void addCourseTasks(TaskDto t) {
			if (courseTasks == null)
				courseTasks = new HashSet<TaskDto>();
			courseTasks.add(t);
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
