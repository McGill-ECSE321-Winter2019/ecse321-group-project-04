package ca.mcgill.ecse321.cooperator.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Document {

  private String name;
  private Date submissionDate;
  private String url;
  private long documentID;

  public void setName(String value) {
    this.name = value;
  }

  public String getName() {
    return this.name;
  }

  public void setUrl(String value) {
    this.url = value;
  }

  public String getUrl() {
    return this.url;
  }

  @Id
  @GeneratedValue
  public long getDocumentID() {
    return documentID;
  }

  public void setDocumentID(long documentID) {
    this.documentID = documentID;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Date submissionDate) {
    this.submissionDate = submissionDate;
  }

}
