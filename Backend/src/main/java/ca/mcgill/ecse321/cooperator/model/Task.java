package ca.mcgill.ecse321.cooperator.model;
import javax.persistence.ManyToMany;

import javax.persistence.Entity;
import java.sql.Date;
//import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Task{
    private Set<Document> documents;

    @ManyToMany(mappedBy="tasks" )
    public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documentss) {
        this.documents = documentss;
    }

    private TaskStatus taskStatus;

    public void setTaskStatus(TaskStatus value) {
        this.taskStatus = value;
    }
    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }
    private String description;

    public void setDescription(String value) {
        this.description = value;
    }
    public String getDescription() {
        return this.description;
    }
    private Date dueDate;

    //@OneToOne(optional=false)
    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    private StudentEnrollment studentEnrollment;

    @ManyToOne(optional=false)
    public StudentEnrollment getStudentEnrollment() {
        return this.studentEnrollment;
    }

    public void setStudentEnrollment(StudentEnrollment studentEnrollment) {
        this.studentEnrollment = studentEnrollment;
    }

    private Set<Document> submitedDocuments;

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

    private String taskID;

    public void setTaskID(String value) {
        this.taskID = value;
    }
    @Id
    public String getTaskID() {
        return this.taskID;
    }
}
