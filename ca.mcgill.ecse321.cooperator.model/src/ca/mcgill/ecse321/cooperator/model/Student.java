package ca.mcgill.ecse321.cooperator.model;


public class Student {
   private String firstName;
   
   public void setFirstName(String value) {
      this.firstName = value;
   }
   
   public String getFirstName() {
      return this.firstName;
   }
   
   private String lastName;
   
   public void setLastName(String value) {
      this.lastName = value;
   }
   
   public String getLastName() {
      return this.lastName;
   }
   
   private Integer mcgillID;
   
   public void setMcgillID(Integer value) {
      this.mcgillID = value;
   }
   
   public Integer getMcgillID() {
      return this.mcgillID;
   }
   
   private String mcgillEmail;
   
   public void setMcgillEmail(String value) {
      this.mcgillEmail = value;
   }
   
   public String getMcgillEmail() {
      return this.mcgillEmail;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Student ------------------------- StudentEnrollment
    *           enrolledStudent        &gt;       courseEnrollment
    * </pre>
    */
   private StudentEnrollment courseEnrollment;
   
   public void setCourseEnrollment(StudentEnrollment value) {
      this.courseEnrollment = value;
   }
   
   public StudentEnrollment getCourseEnrollment() {
      return this.courseEnrollment;
   }
   
   }
