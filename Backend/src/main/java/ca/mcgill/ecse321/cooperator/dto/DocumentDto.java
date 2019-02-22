package ca.mcgill.ecse321.cooperator.dto;

import ca.mcgill.ecse321.cooperator.model.Task;

public class DocumentDto {
	private String name;
    private String url;
    private Task task;
    
    public DocumentDto(){ 
    } 
    
    public DocumentDto(String name, Task task) { 
    	this.name = name; 
    	this.task = task; 
    }
    
    public String getName() { 
    	return this.name; 
    }
  
    public Task getTask() {
    	return this.task; 
    }
    
    public void setUrl(String value) { 
    	this.url = value; 
    }
    
    public String getUrl() { 
    	return this.url; 
    }

}
