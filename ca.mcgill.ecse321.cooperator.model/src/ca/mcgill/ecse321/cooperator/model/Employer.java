package ca.mcgill.ecse321.cooperator.model;

import java.util.Set;
import java.util.HashSet;

public class Employer {
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   private String email;
   
   public void setEmail(String value) {
      this.email = value;
   }
   
   public String getEmail() {
      return this.email;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Employer ------------------------- StudentEnrollment
    *           studentEmployer        &gt;       studentEnrollments
    * </pre>
    */
   private Set<StudentEnrollment> studentEnrollments;
   
   public Set<StudentEnrollment> getStudentEnrollments() {
      if (this.studentEnrollments == null) {
         this.studentEnrollments = new HashSet<StudentEnrollment>();
      }
      return this.studentEnrollments;
   }
   
   }
