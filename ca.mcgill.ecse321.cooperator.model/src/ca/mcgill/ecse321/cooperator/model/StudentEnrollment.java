package ca.mcgill.ecse321.cooperator.model;

import java.util.Set;
import java.util.HashSet;

public class StudentEnrollment {
   private Boolean active;
   
   public void setActive(Boolean value) {
      this.active = value;
   }
   
   public Boolean getActive() {
      return this.active;
   }
   
   private CourseStatus status;
   
   public void setStatus(CourseStatus value) {
      this.status = value;
   }
   
   public CourseStatus getStatus() {
      return this.status;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * StudentEnrollment ------------------------- Employer
    *           studentEnrollments        &lt;       studentEmployer
    * </pre>
    */
   private Employer studentEmployer;
   
   public void setStudentEmployer(Employer value) {
      this.studentEmployer = value;
   }
   
   public Employer getStudentEmployer() {
      return this.studentEmployer;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * StudentEnrollment ------------------------- Student
    *           courseEnrollment        &lt;       enrolledStudent
    * </pre>
    */
   private Student enrolledStudent;
   
   public void setEnrolledStudent(Student value) {
      this.enrolledStudent = value;
   }
   
   public Student getEnrolledStudent() {
      return this.enrolledStudent;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * StudentEnrollment ------------------------- Task
    *           studentEnrollment        &gt;       courseTasks
    * </pre>
    */
   private Set<Task> courseTasks;
   
   public Set<Task> getCourseTasks() {
      if (this.courseTasks == null) {
         this.courseTasks = new HashSet<Task>();
      }
      return this.courseTasks;
   }
   
   /**
    * <pre>
    *           1..*     1..1
    * StudentEnrollment ------------------------- CoopCourseOffering
    *           studentEnrollments        &lt;       coopCourseOffering
    * </pre>
    */
   private CoopCourseOffering coopCourseOffering;
   
   public void setCoopCourseOffering(CoopCourseOffering value) {
      this.coopCourseOffering = value;
   }
   
   public CoopCourseOffering getCoopCourseOffering() {
      return this.coopCourseOffering;
   }
   
   }
