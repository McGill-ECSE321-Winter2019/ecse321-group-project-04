package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import java.sql.Date;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;

	private long taskID;
	@NotBlank(message = "description cannot be blank")
	private String description;
	private Date dueDate;
	private String name;
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

	public void setTaskID(long value) {
		this.taskID = value;
	}

	@Id
	@GeneratedValue
	public long getTaskID() {
		return this.taskID;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	public Set<Document> getDocuments() {
		return this.documents;
	}

	public void addDocument(Document doc) {
		if (documents == null)
			documents = new HashSet<Document>();
		documents.add(doc);
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Document getDocument(String name) {
		if (documents != null) {
			for (Document d : documents) {
				if (d.getName().equals(name))
					return d;
			}
		}
		return null;
	}
}
