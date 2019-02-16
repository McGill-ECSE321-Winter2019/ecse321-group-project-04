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
    private StudentEnrollment studentEnrollment;
    private Set<Document> submitedDocuments;
    private Set<Document> documents;

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

    @ManyToOne(optional=false)
    public StudentEnrollment getStudentEnrollment() {
        return this.studentEnrollment;
    }

    public void setStudentEnrollment(StudentEnrollment studentEnrollment) {
        this.studentEnrollment = studentEnrollment;
    }

    @OneToMany(mappedBy="task" , cascade={CascadeType.ALL})
    public Set<Document> getSubmitedDocuments() {
        return this.submitedDocuments;
    }

    public void setSubmitedDocuments(Set<Document> documentss) {
        this.submitedDocuments = documentss;
    }

    public void addDocument(Document d) {
        if (submitedDocuments == null)
            submitedDocuments = new HashSet<Document>();
        submitedDocuments.add(d);
    }

    public void setTaskID(String value) {
        this.taskID = value;
    }

    @Id
    public String getTaskID() {
        return this.taskID;
    }
}
