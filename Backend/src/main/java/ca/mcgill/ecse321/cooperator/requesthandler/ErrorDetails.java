/*A PORTION OF THIS CODE WAS SOURCED FROM A SPRING BOOT TUTORIAL:
 * http://www.springboottutorial.com/spring-boot-validation-for-rest-services*/
package ca.mcgill.ecse321.cooperator.requesthandler;

import java.util.Date;

public class ErrorDetails {
	  private Date timestamp;
	  private String message;
	  private String details;

	  public ErrorDetails(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }
	  
	  public Date getTimestamp() {
		    return timestamp;
		  }

		  public String getMessage() {
		    return message;
		  }

		  public String getDetails() {
		    return details;
		  }
}
