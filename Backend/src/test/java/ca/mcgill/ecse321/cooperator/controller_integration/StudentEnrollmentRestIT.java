package ca.mcgill.ecse321.cooperator.controller_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Term;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentEnrollmentRestIT {

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
  @After
  public void prepareTest() {
    CoopCourse course = new CoopCourse();

    course.setCourseCode("EBUC1000");
    course.setCoopTerm(2);

    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

    restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST, entity, String.class);

    Student student = new Student();

    student.setFirstName("uvw");
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity2 = new HttpEntity<Student>(student, headers);

    restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity2, String.class);

    Employer employer = new Employer();

    employer.setName("Tom");
    employer.setEmail("tom@email.com");

    HttpEntity<Employer> entity3 = new HttpEntity<Employer>(employer, headers);

    restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity3, String.class);

    CoopCourseOffering courseOffering = new CoopCourseOffering();

    courseOffering.setYear(2019);
    courseOffering.setTerm(Term.SUMMER);
    courseOffering.setActive(true);
    courseOffering.setCoopCourse(course);

    HttpEntity<CoopCourseOffering> entity4 =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
        HttpMethod.POST, entity4, String.class);

  }

  @Test
  public void createStudentEnrollment() {

    StudentEnrollment studentEnrollment = new StudentEnrollment();

    studentEnrollment.setActive(true);
    studentEnrollment.setStatus(CourseStatus.ONGOING);

    HttpEntity<StudentEnrollment> entity =
        new HttpEntity<StudentEnrollment>(studentEnrollment, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
            + "&studentID=260893874&employerEmail=tom@email.com"
            + "&coopAcceptanceForm=url1&employerContract=url2"),

        HttpMethod.POST, entity, String.class);

    String result = response.getBody().toString();

    assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
  }

  @Test
  public void createNullStatusStudentEnrollment() {

    StudentEnrollment studentEnrollment = new StudentEnrollment();

    studentEnrollment.setActive(true);
    studentEnrollment.setStatus(null);

    HttpEntity<StudentEnrollment> entity =
        new HttpEntity<StudentEnrollment>(studentEnrollment, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
            + "&studentID=260893874&employerEmail=tom@email.com"
            + "&coopAcceptanceForm=url1&employerContract=url2"),

        HttpMethod.POST, entity, String.class);

    String result = response.getBody().toString();

    assertTrue(result.contains("Your student enrollment details are incomplete!"));
    assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Test
  public void createStudentEnrollmentTwice() {

    StudentEnrollment studentEnrollment = new StudentEnrollment();

    studentEnrollment.setActive(true);
    studentEnrollment.setStatus(CourseStatus.ONGOING);

    HttpEntity<StudentEnrollment> entity =
        new HttpEntity<StudentEnrollment>(studentEnrollment, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
            + "&studentID=260893874&employerEmail=tom@email.com"
            + "&coopAcceptanceForm=url1&employerContract=url2"),

        HttpMethod.POST, entity, String.class);

    String result = response.getBody().toString();

    assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    response = restTemplate.exchange(
        createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
            + "&studentID=260893874&employerEmail=tom@email.com"
            + "&coopAcceptanceForm=url1&employerContract=url2"),

        HttpMethod.POST, entity, String.class);

    result = response.getBody().toString();

    assertTrue(result.contains("Enrollment Already Exists"));
    assertEquals(response.getStatusCode(), HttpStatus.I_AM_A_TEAPOT);

  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
