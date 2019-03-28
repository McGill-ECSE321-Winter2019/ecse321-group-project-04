package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;

public class EnrollmentWrapper {
  private StudentEnrollment se;
  private String acceptanceFormURL;
  private String employerContractURL;
  public String getAcceptanceFormURL() {
    return acceptanceFormURL;
  }
  
  public void setAcceptanceFormURL(String acceptanceFormURL) {
    this.acceptanceFormURL = acceptanceFormURL;
  }
  public StudentEnrollment getSe() {
    return se;
  }
  public void setSe(StudentEnrollment se) {
    this.se = se;
  }
  public String getEmployerContractURL() {
    return employerContractURL;
  }
  public void setEmployerContractURL(String employerContractURL) {
    this.employerContractURL = employerContractURL;
  }
  
}
