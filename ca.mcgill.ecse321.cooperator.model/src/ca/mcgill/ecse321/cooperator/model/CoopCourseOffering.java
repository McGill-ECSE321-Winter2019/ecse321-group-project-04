package ca.mcgill.ecse321.cooperator.model;

import java.util.Set;
import java.util.HashSet;

public class CoopCourseOffering {
   private Integer year;
   
   public void setYear(Integer value) {
      this.year = value;
   }
   
   public Integer getYear() {
      return this.year;
   }
   
   private Term term;
   
   public void setTerm(Term value) {
      this.term = value;
   }
   
   public Term getTerm() {
      return this.term;
   }
   
   private Boolean active;
   
   public void setActive(Boolean value) {
      this.active = value;
   }
   
   public Boolean getActive() {
      return this.active;
   }
   
   /**
    * <pre>
    *           1..1     1..*
    * CoopCourseOffering ------------------------- StudentEnrollment
    *           coopCourseOffering        &gt;       studentEnrollments
    * </pre>
    */
   private Set<StudentEnrollment> studentEnrollments;
   
   public Set<StudentEnrollment> getStudentEnrollments() {
      if (this.studentEnrollments == null) {
         this.studentEnrollments = new HashSet<StudentEnrollment>();
      }
      return this.studentEnrollments;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * CoopCourseOffering ------------------------- CoopCourse
    *           coopCourseOffering        &gt;       coopCourse
    * </pre>
    */
   private CoopCourse coopCourse;
   
   public void setCoopCourse(CoopCourse value) {
      this.coopCourse = value;
   }
   
   public CoopCourse getCoopCourse() {
      return this.coopCourse;
   }
   
   /**
    * <pre>
    *           1..1     1..*
    * CoopCourseOffering ------------------------- Document
    *           coopCourseOffering        &lt;       documents
    * </pre>
    */
   private Set<Document> documents;
   
   public Set<Document> getDocuments() {
      if (this.documents == null) {
         this.documents = new HashSet<Document>();
      }
      return this.documents;
   }
   
   }
