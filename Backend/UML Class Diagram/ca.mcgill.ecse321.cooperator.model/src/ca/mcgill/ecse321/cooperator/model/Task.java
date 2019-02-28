package ca.mcgill.ecse321.cooperator.model;
import javax.persistence.CascadeType;

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
private String name;

private void setName(String value) {
this.name = value;
}
private String getName() {
return this.name;
}
private Set<Document> documents;

@OneToMany(cascade={CascadeType.ALL})
public Set<Document> getDocuments() {
   return this.documents;
}

public void setDocuments(Set<Document> documentss) {
   this.documents = documentss;
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

private long taskID;

private void setTaskID(long value) {
    this.taskID = value;
}
@Id
private long getTaskID() {
    return this.taskID;
}
   
   }
