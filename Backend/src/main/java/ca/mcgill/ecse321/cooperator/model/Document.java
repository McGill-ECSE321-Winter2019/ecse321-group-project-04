package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.ManyToMany;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Document {

    private String name;
    private String url;
    private Task task;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    @Id
    public String getUrl() {
        return this.url;
    }

    @ManyToOne(optional = false)
    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
