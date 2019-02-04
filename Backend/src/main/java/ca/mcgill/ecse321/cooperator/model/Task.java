package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.sql.Date;
//import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Task {
	private String description;

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	private Date dueDate;

	//@OneToOne(optional = false)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	private TaskStatus status;

	public void setStatus(TaskStatus value) {
		this.status = value;
	}

	public TaskStatus getStatus() {
		return this.status;
	}

	private StudentEnrollment studentEnrollment;

	@ManyToOne(optional = false)
	public StudentEnrollment getStudentEnrollment() {
		return this.studentEnrollment;
	}

	public void setStudentEnrollment(StudentEnrollment studentEnrollment) {
		this.studentEnrollment = studentEnrollment;
	}

	private Set<Document> documents;

	@OneToMany(mappedBy = "task", cascade = { CascadeType.ALL })
	public Set<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(Set<Document> documentss) {
		this.documents = documentss;
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
