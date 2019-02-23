package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Document {

    private String name;
    private String url;

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

}
