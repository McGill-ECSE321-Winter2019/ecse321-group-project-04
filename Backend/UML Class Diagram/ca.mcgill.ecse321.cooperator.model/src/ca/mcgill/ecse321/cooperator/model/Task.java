package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Task{
private TaskStatus status;

private void setStatus(TaskStatus value) {
this.status = value;
}
private TaskStatus getStatus() {
return this.status;
}
   private String description;

private void setDescription(String value) {
    this.description = value;
}
private String getDescription() {
    return this.description;
}
private Date dueDate;

@OneToOne(optional=false)
private Date getDueDate() {
   return this.dueDate;
}

private void setDueDate(Date dueDate) {
   this.dueDate = dueDate;
}

private String taskID;

private void setTaskID(String value) {
    this.taskID = value;
}
@Id
private String getTaskID() {
    return this.taskID;
}
   private Set<Document> document;
   
   @OneToMany
   public Set<Document> getDocument() {
      return this.document;
   }
   
   public void setDocument(Set<Document> documents) {
      this.document = documents;
   }
   
   }
