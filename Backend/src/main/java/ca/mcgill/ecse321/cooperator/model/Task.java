package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;

import java.sql.Date;
import javax.persistence.OneToMany;
import javax.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Task{
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
	
    private long taskID;
    private String description;
    private Date dueDate;
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

    public void setTaskID(long value) {
        this.taskID = value;
    }

    @Id
    @GeneratedValue
    public long getTaskID() {
        return this.taskID;
    }
    
    @OneToMany
    public Set<Document> getDocument() {
       return this.document;
    }
    
	public void addDocument(Document doc) {
		if (document == null)
			document = new HashSet<Document>();
		document.add(doc);
	}
    
    public void setDocument(Set<Document> documents) {
       this.document = documents;
    }
}
