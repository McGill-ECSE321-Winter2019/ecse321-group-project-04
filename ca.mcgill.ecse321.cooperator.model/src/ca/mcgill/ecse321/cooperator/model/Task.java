package ca.mcgill.ecse321.cooperator.model;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;


public class Task {
private TaskStatus status;
   
   public void setStatus(TaskStatus value) {
      this.status = value;
   }
   
   public TaskStatus getStatus() {
      return this.status;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Task ------------------------- StudentEnrollment
    *           courseTasks        &lt;       studentEnrollment
    * </pre>
    */
   private StudentEnrollment studentEnrollment;
   
   public void setStudentEnrollment(StudentEnrollment value) {
      this.studentEnrollment = value;
   }
   
   public StudentEnrollment getStudentEnrollment() {
      return this.studentEnrollment;
   }
   
   /**
    * <pre>
    *           1..1     1..*
    * Task ------------------------- Document
    *           task        &gt;       documents
    * </pre>
    */
   private Set<Document> documents;
   
   public Set<Document> getDocuments() {
      if (this.documents == null) {
         this.documents = new HashSet<Document>();
      }
      return this.documents;
   }
   
   private String description;
   
   public void setDescription(String value) {
      this.description = value;
   }
   
   public String getDescription() {
      return this.description;
   }
   
   private Date dueDate;
   
   public void setDueDate(Date value) {
      this.dueDate = value;
   }
   
   public Date getDueDate() {
      return this.dueDate;
   }
   
   }
