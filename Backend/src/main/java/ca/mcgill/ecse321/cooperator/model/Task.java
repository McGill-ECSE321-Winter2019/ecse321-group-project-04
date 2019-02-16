package ca.mcgill.ecse321.cooperator.model;
import javax.persistence.ManyToMany;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.sql.Date;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Task{
	@Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
	
    private String description;
    private Date dueDate;
    private String taskID;
    private Set<Document> document;


    public void setTaskStatus(TaskStatus value) {
        this.taskStatus = value;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }



    public void setTaskID(String value) {
        this.taskID = value;
    }

    @Id
    public String getTaskID() {
        return this.taskID;
    }
    
    @OneToMany
    public Set<Document> getDocument() {
       return this.document;
    }
    
    public void setDocument(Set<Document> documents) {
       this.document = documents;
    }
}