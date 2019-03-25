package ca.mcgill.ecse321.cooperator.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorRestController {

  @Autowired
  private CooperatorService service;

  /**
   * Method to create a student via RESTful service call
   * 
   * @param student
   * @return
   */

  @PostMapping("/student")
  public ResponseEntity<Object> createStudent(@RequestBody Student student) {

    Student savedStudent = service.createStudent(student);
    // create URI of where the enitity can be found
    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedStudent.getMcgillID()).toUri();
    // return 201 Status with location in header and body
    return ResponseEntity.created(location).body(location);
  }

  /**
   * Method to create an employer via RESTful service call
   * 
   * @param employer
   * @return ResponseEntity with URI of created entity in body and header
   */

  @PostMapping("/employer")
  public ResponseEntity<Object> createEmlpyer(@RequestBody Employer employer) {
    Employer savedEmployer = service.createEmployer(employer);

    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedEmployer.getEmail()).toUri();

    return ResponseEntity.created(location).body(location);
  }

  /**
   * Method to create a coop course via RESTful service call
   * 
   * @param coopCourse
   * @return ResponseEntity with URI of created entity in body and header
   */

  @PostMapping("/coopCourse")
  public ResponseEntity<Object> createCoopCourse(@RequestBody CoopCourse coopCourse) {
    CoopCourse savedCoopCourse = service.createCoopCourse(coopCourse);

    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedCoopCourse.getCourseCode()).toUri();

    return ResponseEntity.created(location).body(location);
  }

  /**
   * Method to create a coop course offering via RESTful service call
   * 
   * @param cco
   * @param courseCode
   * @return ResponseEntity with URI of created entity in body and header
   */

  @PostMapping("/coopCourseOffering")
  public ResponseEntity<Object> createCourseOffering(@RequestBody CoopCourseOffering cco,
      @RequestParam(name = "courseCode") String courseCode) {

    CoopCourse c = service.getCoopCourse(courseCode);

    CoopCourseOffering savedcco = service.createCoopCourseOffering(cco, c);

    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedcco.getOfferID()).toUri();

    return ResponseEntity.created(location).body(location);
  }

  /**
   * Method to create a student enrollment via RESTful service call
   * 
   * @param se
   * @param offerID
   * @param id
   * @param email
   * @param coopAcceptanceForm
   * @param employerContract
   * @return ResponseEntity with URI of created entity in body and header
   */

  @PostMapping("/studentEnrollment")
  public ResponseEntity<Object> createStudentEnrollment(@RequestBody StudentEnrollment se,
      @RequestParam(name = "courseOfferingID") String offerID,
      @RequestParam(name = "studentID") Integer id,
      @RequestParam(name = "employerEmail") String email,
      @RequestParam(name = "coopAcceptanceForm") String coopAcceptanceForm,
      @RequestParam(name = "employerContract") String employerContract) {

    CoopCourseOffering cco = service.getCoopCourseOffering(offerID);
    Student s = service.getStudent(id);
    Employer e = service.getEmployer(email);

    StudentEnrollment savedse =
        service.createStudentEnrollment(se, s, e, cco, coopAcceptanceForm, employerContract);

    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedse.getEnrollmentID()).toUri();

    return ResponseEntity.created(location).body(location);
  }

  /**
   * Method to update a student enrollment via RESTful service call
   * 
   * @param se
   * @param offerID
   * @param id
   * @param email
   * @param coopAcceptanceForm
   * @param employerContract
   * @return
   */
  @PutMapping("/studentEnrollment")
  public ResponseEntity<Object> updateStudentEnrollment(
      @RequestParam(name = "enrollmentID") String enrollmentID,
      @RequestParam(name = "active") Boolean active,
      @RequestParam(name = "status") CourseStatus status) {

    StudentEnrollment savedse =
        service.updateStudentEnrollment(enrollmentID, active, status);

    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedse.getEnrollmentID()).toUri();
    return  ResponseEntity.accepted().body(location);
  }

  /**
   * Method to create a task via RESTful service call
   * 
   * @param task
   * @param id
   * @return ResponseEntity with URI of created entity in body and header
   */

  @PostMapping("/task")
  public ResponseEntity<Object> createTask(@RequestBody Task task,
      @RequestParam(name = "studentEnrollmentID") String id) {
    Task savedTask = service.createTask(task, id);
    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedTask.getTaskID()).toUri();

    return ResponseEntity.created(location).body(location);
  }

  /**
   * Method to create a document via RESTful service call
   * 
   * @param document
   * @param id
   * @param name
   * @return ResponseEntity with URI of created entity in body and header
   */

  @PostMapping("/document")
  public ResponseEntity<Object> createDocument(@RequestBody Document document,
      @RequestParam(name = "studentEnrollmentID") String id,
      @RequestParam(name = "taskName") String name) {
    Document savedDocument = service.createDocument(document, id, name);
    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedDocument.getDocumentID()).toUri();

    return ResponseEntity.created(location).body(location);
  }
}
