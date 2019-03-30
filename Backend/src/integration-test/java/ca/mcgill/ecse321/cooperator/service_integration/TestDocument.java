package ca.mcgill.ecse321.cooperator.service_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestDocument {
  @Autowired
  private CooperatorService service;
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private EmployerRepository employerRepository;
  @Autowired
  private CoopCourseRepository coopCourseRepository;
  @Autowired
  private CoopCourseOfferingRepository coopCourseOfferingRepository;
  @Autowired
  private StudentEnrollmentRepository studentEnrollmentRepository;
  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private DocumentRepository documentRepository;
  
  @SuppressWarnings("deprecation")
  private static final Date START_DATE = new Date(2019, 05, 15);
  @SuppressWarnings("deprecation")
  private static final Date END_DATE = new Date(2019, 11, 15);
  private static final Boolean WORK_PERMIT = true;
  private static final String JOB_ID = "ABC123456";

  @After
  @Before
  public void cleanDataBase() {
    studentEnrollmentRepository.deleteAll();
    studentRepository.deleteAll();
    employerRepository.deleteAll();
    coopCourseOfferingRepository.deleteAll();
    coopCourseRepository.deleteAll();
    taskRepository.deleteAll();
    documentRepository.deleteAll();
  }

  @Test
  public void testCreateDocument() {
    // Create chain of objects required to create a task
    @SuppressWarnings("deprecation")
    Date dueDate = new Date(2019, 1, 1);
    CoopCourse c = service.createCoopCourse("ECSE302", 1);
    CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
    Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
    Employer emp = service.createEmployer("Facebook", "fb@email.com", "123 Sherbrooke");
    StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco,
        "test-url-1", "test-url-2", START_DATE, END_DATE, WORK_PERMIT, JOB_ID);
    Task t = service.createTask("Task name", "Some description", dueDate, TaskStatus.COMPLETED,
        se.getEnrollmentID());

    Document param = new Document();
    param.setName("doc name");
    param.setUrl("http://test-url.this/is/just/for/testing");

    try {
      service.createDocument(param, se.getEnrollmentID(), t.getName());
    } catch (InvalidParameterException e) {
      fail();
    }
    Document d = service.getDocument("http://test-url.this/is/just/for/testing");

    assertEquals("doc name", d.getName());
    assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());
    Calendar currentCal = Calendar.getInstance();
    Date currentDate = new Date(currentCal.getTimeInMillis());
    assertEquals(currentDate.toString(), d.getSubmissionDate().toString());

    assertEquals(5, service.getAllDocuments().size());
  }
  
  @Test
  public void testcheckSubmissionStatus() {
    // Create chain of objects required to create a task
    Calendar currentCal = Calendar.getInstance();
    Date dueDate = new Date(currentCal.getTimeInMillis());
    CoopCourse c = service.createCoopCourse("ECSE302", 1);
    CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
    Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
    Employer emp = service.createEmployer("Facebook", "fb@email.com", "123 Sherbrooke");
    StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco,
        "test-url-1", "test-url-2", START_DATE, END_DATE, WORK_PERMIT, JOB_ID);
    Task t = service.createTask("Task name", "Some description", dueDate, TaskStatus.INCOMPLETE,
        se.getEnrollmentID());
    System.out.println("hey"+t.getTaskStatus());

    Document param = new Document();
    param.setName("doc name");
    param.setUrl("http://test-url.this/is/just/for/testing");

    try {
      service.createDocument(param, se.getEnrollmentID(), t.getName());
    } catch (InvalidParameterException e) {
      fail();
    }
    Document d = service.getDocument("http://test-url.this/is/just/for/testing");

    assertEquals("doc name", d.getName());
    assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());
    Calendar currentCal1 = Calendar.getInstance();
    Date currentDate = new Date(currentCal1.getTimeInMillis());
    assertEquals(currentDate.toString(), d.getSubmissionDate().toString());

    assertEquals(5, service.getAllDocuments().size());
    Task t1 = service.getTask(t.getTaskID());
    System.out.println(t1.getTaskStatus());
  }

  @Test
  public void testOverwriteDocument() {
    // Create chain of objects required to create a task
    @SuppressWarnings("deprecation")
    Date dueDate = new Date(2019, 1, 1);
    CoopCourse c = service.createCoopCourse("ECSE302", 1);
    CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
    Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
    Employer emp = service.createEmployer("Facebook", "fb@email.com", "123 Sherbrooke");
    StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco,
        "test-url-1", "test-url-2", START_DATE, END_DATE, WORK_PERMIT, JOB_ID);
    Task t = service.createTask("Task name", "Some description", dueDate, TaskStatus.COMPLETED,
        se.getEnrollmentID());

    Document param = new Document();
    param.setName("doc name");
    param.setUrl("http://test-url.this/is/just/for/testing");

    Document param_overwrite = new Document();
    param_overwrite.setName("doc name");
    param_overwrite.setUrl("http://test-url.this/is/also/just/for/testing");

    try {
      service.createDocument(param, se.getEnrollmentID(), t.getName());
      service.createDocument(param_overwrite, se.getEnrollmentID(), t.getName());
    } catch (InvalidParameterException e) {
      fail();
    }

    String error = null;
    try {
      service.getDocument("http://test-url.this/is/just/for/testing");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    Document d = service.getDocument("http://test-url.this/is/also/just/for/testing");

    assertEquals("Could not find a Document with URL http://test-url.this/is/just/for/testing",
        error);

    assertEquals("doc name", d.getName());
    assertEquals("http://test-url.this/is/also/just/for/testing", d.getUrl());
    assertEquals(1, service.getStudentEnrollment(se.getEnrollmentID()).getTask(t.getName())
        .getDocuments().size());

    assertEquals(5, service.getAllDocuments().size());
  }

  @Test
  public void testCreateNullNameDocument() {
    String error = null;

    try {
      service.createDocument(null, "http://test-url.this/is/just/for/testing", null, null);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your document details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllDocuments().size());
  }

  @Test
  public void testGetNonexistentDocument() {
    String error = null;
    try {
      service.getDocument("test-url-1");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }
    assertEquals("Could not find a Document with URL test-url-1", error);
  }
}
