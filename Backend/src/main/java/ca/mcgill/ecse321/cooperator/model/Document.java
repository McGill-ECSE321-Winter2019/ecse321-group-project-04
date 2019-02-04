package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Document{
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
   
   @ManyToOne(optional=false)
   public Task getTask() {
      return this.task;
   }
   
   public void setTask(Task task) {
      this.task = task;
   }
   
   private CoopCourseOffering coopCourseOffering;
   
   @ManyToOne(optional=false)
   public CoopCourseOffering getCoopCourseOffering() {
      return this.coopCourseOffering;
   }
   
   public void setCoopCourseOffering(CoopCourseOffering coopCourseOffering) {
      this.coopCourseOffering = coopCourseOffering;
   }
   
   }
