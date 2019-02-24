package ca.mcgill.ecse321.cooperator.dto;

public class DocumentDto {
	private String name;
    private String url;
    
    public DocumentDto(){ 
    } 
    
    public DocumentDto(String name, String url) { 
    	this.name = name; 
    	this.url = url;
    }
    
    public String getName() { 
    	return this.name; 
    }
    
    public String getUrl() { 
    	return this.url; 
    }

}
