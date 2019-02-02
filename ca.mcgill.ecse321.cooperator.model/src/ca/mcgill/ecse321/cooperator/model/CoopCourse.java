package ca.mcgill.ecse321.cooperator.model;

import java.util.Set;
import java.util.HashSet;

public class CoopCourse {
   private String courseCode;
   
   public void setCourseCode(String value) {
      this.courseCode = value;
   }
   
   public String getCourseCode() {
      return this.courseCode;
   }
   
   private Integer coopTerm;
   
   public void setCoopTerm(Integer value) {
      this.coopTerm = value;
   }
   
   public Integer getCoopTerm() {
      return this.coopTerm;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * CoopCourse ------------------------- CoopCourseOffering
    *           coopCourse        &lt;       coopCourseOffering
    * </pre>
    */
   private Set<CoopCourseOffering> coopCourseOffering;
   
   public Set<CoopCourseOffering> getCoopCourseOffering() {
      if (this.coopCourseOffering == null) {
         this.coopCourseOffering = new HashSet<CoopCourseOffering>();
      }
      return this.coopCourseOffering;
   }
   
   }
