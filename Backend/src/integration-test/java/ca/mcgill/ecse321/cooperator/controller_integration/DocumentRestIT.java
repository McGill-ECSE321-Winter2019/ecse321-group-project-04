package ca.mcgill.ecse321.cooperator.controller_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.CooperatorApplication;
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

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentRestIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private HttpHeaders headers = new HttpHeaders();

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private EmployerRepository emoloyerRepository;

  @Autowired
  private CoopCourseRepository coopCourseRepository;

  @Autowired
  private CoopCourseOfferingRepository coopCourseOfferingRepository;

  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private StudentEnrollmentRepository studentEnrollmentRepository;

  @Autowired
  private TaskRepository taskRepository;

  @Before
  @After
  public void cleanDataBase() {
    studentEnrollmentRepository.deleteAll();
    studentRepository.deleteAll();
    emoloyerRepository.deleteAll();
    coopCourseOfferingRepository.deleteAll();
    coopCourseRepository.deleteAll();
    documentRepository.deleteAll();
    taskRepository.deleteAll();
  }

  @Before
  public void prepareTest() {
    // Create chain of objects required to create a document
    createStudent();
    createEmployer();
    createCourse();
    createCourseOffering();
    createStudentEnrollment();
    createTask();
  }

  @Test
  public void createDocument() {
    Document document = new Document();
    document.setName("DocName");
    document.setUrl("http://test-url.this/is/just/for/testing");

    HttpEntity<Document> entity = new HttpEntity<Document>(document, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(
            "document?studentEnrollmentID=260893874-EBUC1000-S19" + "&taskName=someTask"),
        HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // CHeck URI in body
    String result = response.getBody().toString();
    assertTrue(result.contains("/documents/"));
  }

  @Test
  public void createNullNameDocument() {
    Document document = new Document();
    document.setName(null);
    document.setUrl("http://test-url.this/is/just/for/testing");

    HttpEntity<Document> entity = new HttpEntity<Document>(document, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(
            "document?studentEnrollmentID=260893874-EBUC1000-S19" + "&taskName=someTask"),
        HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // CHeck error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your document details are incomplete!"));
  }

  @Test
  public void createNullUrlDocument() {
    Document document = new Document();
    document.setName("name");
    document.setUrl(null);

    HttpEntity<Document> entity = new HttpEntity<Document>(document, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(
            "document?studentEnrollmentID=260893874-EBUC1000-S19" + "&taskName=someTask"),
        HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // CHeck error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your document details are incomplete!"));
  }

  private void createCourse() {
    CoopCourse course = new CoopCourse();
    course.setCourseCode("EBUC1000");
    course.setCoopTerm(2);

    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
    restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST, entity, String.class);
  }

  private void createEmployer() {
    Employer employer = new Employer();
    employer.setName("Tom");
    employer.setEmail("tom@email.com");

    HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);
    restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity, String.class);
  }

  private void createStudent() {
    Student student = new Student();
    student.setFirstName("uvw");
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
    restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);
  }

  private void createCourseOffering() {
    CoopCourseOffering courseOffering = new CoopCourseOffering();
    courseOffering.setYear(2019);
    courseOffering.setTerm(Term.SUMMER);
    courseOffering.setActive(true);

    HttpEntity<CoopCourseOffering> entity =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
        HttpMethod.POST, entity, String.class);
  }

  private void createStudentEnrollment() {
    StudentEnrollment studentEnrollment = new StudentEnrollment();
    studentEnrollment.setActive(true);
    studentEnrollment.setStatus(CourseStatus.ONGOING);

    HttpEntity<StudentEnrollment> entity =
        new HttpEntity<StudentEnrollment>(studentEnrollment, headers);

    restTemplate.exchange(
        createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
            + "&studentID=260893874&employerEmail=tom@email.com"
            + "&coopAcceptanceForm=url1&employerContract=url2"),
        HttpMethod.POST, entity, String.class);
  }

  private void createTask() {
    Task task = new Task();
    @SuppressWarnings("deprecation")
    Date dueDate = new Date(2019, 1, 1);
    task.setName("someTask");
    task.setDescription("some description");
    task.setDueDate(dueDate);
    task.setTaskStatus(TaskStatus.COMPLETED);

    HttpEntity<Task> entity = new HttpEntity<Task>(task, headers);

    restTemplate.exchange(createURLWithPort("/task?studentEnrollmentID=260893874-EBUC1000-S19"),
        HttpMethod.POST, entity, String.class);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
