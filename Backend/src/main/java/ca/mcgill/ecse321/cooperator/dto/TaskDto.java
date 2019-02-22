package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;

public class TaskDto {
	
	private TaskStatus taskStatus;
	
    private long taskID;
    private String description;
    private Date dueDate;
    private Set<Document> document;
    
    public TaskDto () { 
    }
    
    public TaskDto( String description, Date dueDate) { 
    	this.description = description; 
    	this.dueDate = dueDate; 
    }
    
    
    

}
