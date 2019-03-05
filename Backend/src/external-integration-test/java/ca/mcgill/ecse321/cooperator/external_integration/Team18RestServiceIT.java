package ca.mcgill.ecse321.cooperator.external_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Team18RestServiceIT {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;
  // TEAM 18: Academic Manager
  private static final String URL = "https://cooperatorapp-backend-18.herokuapp.com/";

  @Before
  public void prepareTest() {
    // Create Cooperator
    restTemplate.exchange(URL + "cooperators/create/?id=1", HttpMethod.POST, null, String.class);
    // Create Coop Term
    restTemplate.exchange(URL
        + "terms/create/?id=2112&name=Winter2019&studentdeadline=2019-3-22&coopdeadline=2019-4-4",
        HttpMethod.POST, null, String.class);
    // Create Student
    restTemplate.exchange(
        URL + "students/create/?id=226433222&firstname=Yen-Vi&lastname=Huynh&cooperatorid=1",
        HttpMethod.POST, null, String.class);
    // Create Registration
    restTemplate.exchange(URL
        + "coopTermRegistrations/create/?registrationid=1&jobid=142412&studentid=226433222&termid=2112&termstat=0&gradeid=5",
        HttpMethod.POST, null, String.class);
  }

  @Test
  public void testGetGradesFrom18() {
    String expected = "[\"NotGraded\"]";
    ResponseEntity<String> response = restTemplate.exchange(URL + "coopTermRegistrations/grades",
        HttpMethod.GET, null, String.class);
    // Check status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // Check Body
    assertTrue(response.getBody().contains(expected));
  }

  @Test
  public void testGetRegisterationTermsFrom18() {

    ResponseEntity<String> response = restTemplate.exchange(URL + "coopTermRegistrations/list",
        HttpMethod.GET, null, String.class);
    // Check status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // Check Body
    assertTrue(response.getBody().contains("\"registrationID\":\"1\""));
    assertTrue(response.getBody().contains("\"jobID\":\"142412\""));
    assertTrue(response.getBody().contains("\"grade\":\"NotGraded\""));
    assertTrue(response.getBody().contains("\"studentID\":\"226433222\""));
    assertTrue(response.getBody().contains("\"termStatus\":\"ONGOING\""));
  }

  @Test
  public void testGetProblematicFrom18() {
    ResponseEntity<String> response =
        restTemplate.exchange(URL + "students/problematic", HttpMethod.GET, null, String.class);
    // Check status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // Empty Body
  }
}
