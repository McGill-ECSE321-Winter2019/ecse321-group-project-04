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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllGetEndPointsRestIT {
  private String taskId;
  private String docId;
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
    // Create objects required to perform all GET end point tests
    createStudent();
    createEmployer();
    createCourse();
    createCourseOffering();
    createStudentEnrollment();
    createTask();
    createDocument();
  }

  /*------- STUDENT TEST -------*/
  @Test
  public void getStudents() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/students"), HttpMethod.GET, null, String.class);

    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // Check URI
    String result = response.getBody().toString();
    assertTrue(result.contains("/students"));
  }

  @Test
  public void getStudentByID() {
    ResponseEntity<String> response = restTemplate
        .exchange(createURLWithPort("/students/260893874"), HttpMethod.GET, null, String.class);

    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/students/260893874"));
    // Check attributes
    assertTrue(result.contains("\"firstName\" : \"uvw\""));
    assertTrue(result.contains("\"lastName\" : \"xyz\""));
    assertTrue(result.contains("\"mcgillEmail\" : \"uvw.xyz@email.com\""));
  }

  @Test
  public void getStudentsCourseEnrollments() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/students/260893874/courseEnrollments"),
            HttpMethod.GET, null, String.class);

    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("studentEnrollments/260893874-EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"status\" : \"ONGOING\""));
    assertTrue(result.contains("  \"active\" : true,"));
  }

  /*------- EMPLOYER TEST -------*/
  @Test
  public void getEmployers() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/employers"), HttpMethod.GET, null, String.class);

    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/employers"));
  }

  @Test
  public void getEmployerByID() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/employers/tom@email.com"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/employers/tom@email.com"));
    // Check attributes
    assertTrue(result.contains("\"name\" : \"Tom\""));
  }

  @Test
  public void getCourseEnrolledStudentForEmployer() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/employers/tom@email.com/studentEnrollments"),
            HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"status\" : \"ONGOING\""));
    assertTrue(result.contains("  \"active\" : true,"));
  }

  /*------- COOP COURSE TEST -------*/
  @Test
  public void getCourses() {
    ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourses"),
        HttpMethod.GET, null, String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/coopCourses"));
  }

  @Test
  public void getCoursesByID() {
    ResponseEntity<String> response = restTemplate
        .exchange(createURLWithPort("/coopCourses/EBUC1000"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/coopCourses/EBUC1000"));
    // Check attributes
    assertTrue(result.contains("\"coopTerm\" : 2"));
  }

  @Test
  public void getCourseOfferingsForCourse() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/coopCourses/EBUC1000/coopCourseOffering"),
            HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"term\" : \"SUMMER\","));
    assertTrue(result.contains(" \"year\" : 2019,"));
    assertTrue(result.contains("\"active\" : true,"));

  }

  /*------- COURSE OFFERINGS TEST -------*/
  @Test
  public void getCourseOferings() {
    ResponseEntity<String> response = restTemplate
        .exchange(createURLWithPort("/coopCourseOfferings"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/coopCourseOfferings"));
  }

  @Test
  public void getCourseOferingByID() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/coopCourseOfferings/EBUC1000-S19"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"term\" : \"SUMMER\","));
    assertTrue(result.contains(" \"year\" : 2019,"));
    assertTrue(result.contains("\"active\" : true,"));
  }

  @Test
  public void getEnrollmentsOfOffering() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/coopCourseOfferings/EBUC1000-S19/studentEnrollments"), HttpMethod.GET,
        null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"status\" : \"ONGOING\""));
    assertTrue(result.contains("  \"active\" : true,"));
  }

  /*------- STUDENT ENROLLMENT TEST -------*/
  @Test
  public void getEnrollments() {
    ResponseEntity<String> response = restTemplate
        .exchange(createURLWithPort("/studentEnrollments"), HttpMethod.GET, null, String.class);
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/studentEnrollments"));
  }

  @Test
  public void getEnrollmentsByID() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/studentEnrollments/260893874-EBUC1000-S19"),
            HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"status\" : \"ONGOING\""));
    assertTrue(result.contains("  \"active\" : true,"));
  }

  @Test
  public void getEnrolledStudent() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollments/260893874-EBUC1000-S19/enrolledStudent"),
        HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/students/260893874"));
    // Check attributes
    assertTrue(result.contains("\"firstName\" : \"uvw\""));
    assertTrue(result.contains("\"lastName\" : \"xyz\""));
    assertTrue(result.contains("\"mcgillEmail\" : \"uvw.xyz@email.com\""));
  }

  @Test
  public void getCourseOfferingOfEnrollment() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollments/260893874-EBUC1000-S19/coopCourseOffering"),
        HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
    // Check attributes
    assertTrue(result.contains(" \"term\" : \"SUMMER\","));
    assertTrue(result.contains(" \"year\" : 2019,"));
    assertTrue(result.contains("\"active\" : true,"));
  }

  @Test
  public void getEmployerOfEnrollment() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollments/260893874-EBUC1000-S19/studentEmployer"),
        HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/employers/tom@email.com"));
    // Check attributes
    assertTrue(result.contains("\"name\" : \"Tom\""));
  }

  @Test
  public void getTasksOfEnrollment() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollments/260893874-EBUC1000-S19/courseTasks"), HttpMethod.GET,
        null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/tasks/" + taskId));
    // Check attributes
    assertTrue(result.contains("Submit the CO-OP position acceptance form"));
    assertTrue(result.contains("Submit the employer contract document"));
    assertTrue(
        result.contains("Submit an initial report of the tasks and workload of the internship"));
    assertTrue(result.contains("Submit the term technical report about the internship experience"));
    assertTrue(result.contains("Submit the final evaluation report for the internship experience"));
    assertTrue(result.contains("some description"));
  }

  /*------- TASKS TEST -------*/
  @Test
  public void getTasks() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/tasks"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/tasks"));
  }

  @Test
  public void getTaskByID() {
    ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/tasks/" + taskId),
        HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/tasks/" + taskId));
    // Check attributes
    assertTrue(result.contains("some description"));
  }

  @Test
  public void getDocumentsOfTask() {
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/tasks/" + taskId + "/documents"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    assertTrue(result.contains("/documents/" + docId));
    // Check attributes
    assertTrue(result.contains("DocName"));
  }

  /*------- DOCUMENT TEST -------*/
  @Test
  public void getDocuments() {
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/documents/"), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/documents"));
  }

  @Test
  public void getDocumentByID() {
    ResponseEntity<String> response = restTemplate
        .exchange(createURLWithPort("/documents/" + docId), HttpMethod.GET, null, String.class);
    
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String result = response.getBody().toString();
    // Check URI
    assertTrue(result.contains("/documents/" + docId));
    // Check attributes
    assertTrue(result.contains("DocName"));
  }

  /*------- TEST PREPARATION METHODS -------*/

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

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/task?studentEnrollmentID=260893874-EBUC1000-S19"),
            HttpMethod.POST, entity, String.class);
    /*
     * Remember the Id when creating a task, in real scenario the id would be found by navigating
     * from URI in body of /studentEnrollments/id/tasks.
     */
    String result = response.getBody().split("tasks/")[1].replaceAll("^\"|\"$", "");
    this.taskId = result;
  }

  private void createDocument() {
    Document document = new Document();
    document.setName("DocName");
    document.setUrl("http://test-url.this/is/just/for/testing");

    HttpEntity<Document> entity = new HttpEntity<Document>(document, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(
            "document?studentEnrollmentID=260893874-EBUC1000-S19" + "&taskName=someTask"),
        HttpMethod.POST, entity, String.class);
    /*
     * Remember the Id when creating a document, in real scenario the id would be found by
     * navigating from URI in body of /studentEnrollments/id/tasks and then tasks/id/documents
     */
    String result = response.getBody().split("documents/")[1].replaceAll("^\"|\"$", "");
    this.docId = result;
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
