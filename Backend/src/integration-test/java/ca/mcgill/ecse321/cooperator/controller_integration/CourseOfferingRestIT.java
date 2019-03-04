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
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Term;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseOfferingRestIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private HttpHeaders headers = new HttpHeaders();

  @Autowired
  private CoopCourseRepository coopCourseRepository;

  @Autowired
  private CoopCourseOfferingRepository coopCourseOfferingRepository;

  @Before
  @After
  public void cleanDataBase() {
    coopCourseOfferingRepository.deleteAll();
    coopCourseRepository.deleteAll();
  }

  @Before
  public void prepareTest() {
    // Create chain of objects required to create a course offering
    createCourse();
  }

  @Test
  public void createCourseOffering() {
    CoopCourseOffering courseOffering = new CoopCourseOffering();
    courseOffering.setYear(2019);
    courseOffering.setTerm(Term.SUMMER);
    courseOffering.setActive(true);

    HttpEntity<CoopCourseOffering> entity =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
            HttpMethod.POST, entity, String.class);
    // Check Status
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // Check URI in Body
    String result = response.getBody().toString();
    assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
  }

  @Test
  public void createNullYearCourseOffering() {
    CoopCourseOffering courseOffering = new CoopCourseOffering();
    courseOffering.setYear(null);
    courseOffering.setTerm(Term.SUMMER);
    courseOffering.setActive(true);

    HttpEntity<CoopCourseOffering> entity =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
            HttpMethod.POST, entity, String.class);
    
    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your course offering details are incomplete!"));
  }

  @Test
  public void createNullTermCourseOffering() {
    CoopCourseOffering courseOffering = new CoopCourseOffering();
    courseOffering.setYear(2019);
    courseOffering.setTerm(null);
    courseOffering.setActive(true);

    HttpEntity<CoopCourseOffering> entity =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
            HttpMethod.POST, entity, String.class);
    
    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your course offering details are incomplete!"));
  }

  @Test
  public void createNullActiveCourseOffering() {
    CoopCourseOffering courseOffering = new CoopCourseOffering();
    courseOffering.setYear(2019);
    courseOffering.setTerm(Term.SUMMER);
    courseOffering.setActive(null);

    HttpEntity<CoopCourseOffering> entity =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
            HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your course offering details are incomplete!"));
  }

  @Test
  public void createCourseOfferingTwice() {
    CoopCourseOffering courseOffering = new CoopCourseOffering();
    courseOffering.setYear(2019);
    courseOffering.setTerm(Term.SUMMER);
    courseOffering.setActive(true);

    HttpEntity<CoopCourseOffering> entity =
        new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
            HttpMethod.POST, entity, String.class);

    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    String result = response.getBody().toString();
    assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
    
    // Create Duplicate course
    entity = new HttpEntity<CoopCourseOffering>(courseOffering, headers);

    response = restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
        HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
    // Check error message
    result = response.getBody().toString();
    assertTrue(result.contains("Offering Already Exists"));
  }

  private void createCourse() {
    CoopCourse course = new CoopCourse();
    course.setCourseCode("EBUC1000");
    course.setCoopTerm(2);

    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

    restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST, entity, String.class);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
