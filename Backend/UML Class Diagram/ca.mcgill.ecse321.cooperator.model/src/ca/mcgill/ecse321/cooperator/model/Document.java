package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Document{
private long documentID;

private void setDocumentID(long value) {
this.documentID = value;
}
@Id
private long getDocumentID() {
return this.documentID;
}
   private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private String url;

private void setUrl(String value) {
    this.url = value;
}
private String getUrl() {
    return this.url;
}
   
   }
