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
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployerRestIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private HttpHeaders headers = new HttpHeaders();

  @Autowired
  private EmployerRepository emoloyerRepository;

  @Before
  @After
  public void cleanDataBase() {
    emoloyerRepository.deleteAll();
  }

  @Test
  public void createEmployer() {
    Employer employer = new Employer();
    employer.setName("Tom");
    employer.setEmail("tom@email.com");

    HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);

    ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/employer"),
        HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // Check URI in body
    String result = response.getBody().toString();
    assertTrue(result.contains("/employers/tom@email.com"));
  }

  @Test
  public void createNullNameEmployer() {
    Employer employer = new Employer();
    employer.setName(null);
    employer.setEmail("tom@email.com");

    HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);
    ResponseEntity<String> response = null;

    response = restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity,
        String.class);

    // Check Status
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    // Check error message
    String result = response.getBody().toString();
    assertTrue(result.contains("Your employer details are incomplete!"));
  }

  @Test
  public void createEmployerTwice() {
    Employer employer = new Employer();
    employer.setName("Tom");
    employer.setEmail("tom@email.com");

    HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);

    ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/employer"),
        HttpMethod.POST, entity, String.class);

    // Check Status
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    String result = response.getBody().toString();
    assertTrue(result.contains("/employers/tom@email.com"));

    // create duplicate
    response = restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity,
        String.class);

    // Check Status
    assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
    // Check error message
    result = response.getBody().toString();
    assertTrue(result.contains("Employer Already Exists"));
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
