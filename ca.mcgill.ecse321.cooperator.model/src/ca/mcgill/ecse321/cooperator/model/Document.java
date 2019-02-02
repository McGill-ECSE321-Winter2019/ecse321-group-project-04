package ca.mcgill.ecse321.cooperator.model;


public class Document {
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
   
   public String getUrl() {
      return this.url;
   }
   
   /**
    * <pre>
    *           1..*     1..1
    * Document ------------------------- Task
    *           documents        &lt;       task
    * </pre>
    */
   private Task task;
   
   public void setTask(Task value) {
      this.task = value;
   }
   
   public Task getTask() {
      return this.task;
   }
   
   /**
    * <pre>
    *           1..*     1..1
    * Document ------------------------- CoopCourseOffering
    *           documents        &gt;       coopCourseOffering
    * </pre>
    */
   private CoopCourseOffering coopCourseOffering;
   
   public void setCoopCourseOffering(CoopCourseOffering value) {
      this.coopCourseOffering = value;
   }
   
   public CoopCourseOffering getCoopCourseOffering() {
      return this.coopCourseOffering;
   }
   
   }
