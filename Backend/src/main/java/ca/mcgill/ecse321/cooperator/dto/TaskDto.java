package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.TaskStatus;

public class TaskDto {
	
	private TaskStatus taskStatus;
	
    private long taskID;
    private String description;
    private Date dueDate;
    private Set<DocumentDto> document;
    
    public TaskDto () { 
    }
    
    public TaskDto( String description, Date dueDate, TaskStatus taskStatus) { 
    	this.description = description; 
    	this.dueDate = dueDate;
    	this.taskStatus = taskStatus; 
    }
    
    public String getDescription() {
        return this.description;
    }

    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setTaskStatus(TaskStatus value) {
        this.taskStatus = value;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }
    
    public void setTaskID(long value) {
        this.taskID = value;
    }

    public long getTaskID() {
        return this.taskID;
    }
    
    public Set<DocumentDto> getDocument() {
        return this.document;
     }
     
    public void setDocument(Set<DocumentDto> documents) {
       this.document = documents;
    }

}
