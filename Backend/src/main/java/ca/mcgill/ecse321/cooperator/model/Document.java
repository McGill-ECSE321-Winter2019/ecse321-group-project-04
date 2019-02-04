package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.ManyToMany;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Document {
	private Set<Task> tasks;

	@ManyToMany
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> taskss) {
		this.tasks = taskss;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private String url;

	public void setUrl(String value) {
		this.url = value;
	}

	@Id
	public String getUrl() {
		return this.url;
	}

	private Task task;

	@ManyToOne(optional = false)
	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
