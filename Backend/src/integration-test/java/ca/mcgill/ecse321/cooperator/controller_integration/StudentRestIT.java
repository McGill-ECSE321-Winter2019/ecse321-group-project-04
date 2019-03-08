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
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.Student;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentRestIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private HttpHeaders headers = new HttpHeaders();

  @Autowired
  private StudentRepository studentRepository;

  @Before
  @After
  public void cleanDataBase() {
    studentRepository.deleteAll();
  }

  @Test
  public void createStudent() {
    Student student = new Student();
    student.setFirstName("uvw");
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // Check URI in body
    String result = response.getBody().toString();
    assertTrue(result.contains("/students/260893874"));
  }

  @Test
  public void createEmptyNameStudent() {
    Student student = new Student();
    student.setFirstName("");
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your student details are incomplete!"));
  }

  @Test
  public void createNullNameStudent() {
    Student student = new Student();
    student.setFirstName(null);
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your student details are incomplete!"));
  }

  @Test
  public void createNullIDStudent() {
    Student student = new Student();
    student.setFirstName("uvw");
    student.setLastName("xyz");
    student.setMcgillID(null);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your student details are incomplete!"));
  }

  @Test
  public void createNullEmailStudent() {
    Student student = new Student();
    student.setFirstName("uvw");
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail(null);

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your student details are incomplete!"));
  }

  @Test
  public void createStudentTwice() {
    Student student = new Student();
    student.setFirstName("uvw");
    student.setLastName("xyz");
    student.setMcgillID(260893874);
    student.setMcgillEmail("uvw.xyz@email.com");

    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    String result = response.getBody().toString();
    assertTrue(result.contains("/students/260893874"));
    // Create duplicate
    response =
        restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
    // Check error message
    result = response.getBody().toString();
    assertTrue(result.contains("Student Already Exists"));
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
