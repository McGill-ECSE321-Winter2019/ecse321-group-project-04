package ca.mcgill.ecse321.cooperator.external_integration;

import static org.junit.Assert.assertEquals;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Team07RestServiceIT {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;
  // TEAM 07: Employer
  private static final String URL = "https://employer-backend-8888.herokuapp.com/";

  /* ASK FOR PERMISSION FROM TEAM 07 BEFORE RUNNING */

  @Before
  public void prepareTest() {
    // create app
    restTemplate.exchange(URL + "mainapp/1", HttpMethod.POST, null, String.class);
    // create student
    restTemplate.exchange(URL + "students/alexis/26080/alexis@hotmail.com/BEng/1234567890/1",
        HttpMethod.POST, null, String.class);
    // create employer
    restTemplate.exchange(URL + "employer/billGates/1234/microsoft/1", HttpMethod.POST, null,
        String.class);
    // create coop position
    restTemplate.exchange(
        URL + "coopposition/2/developer/26080/billGates/1/2019-02-26/2019-06-26/winter/started",
        HttpMethod.POST, null, String.class);
  }

  @Test
  public void testGetCooppositionFrom07() {
    String expected =
        "{\"starting\":\"2019-02-26\",\"finishing\":\"2019-06-26\",\"coopID\":2,\"name\":\"developer\",\"status\":\"Started\",\"semester\":\"Winter\"}";

    ResponseEntity<String> response =
        restTemplate.exchange(URL + "coopposition/2", HttpMethod.GET, null, String.class);
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // Check Body
    try {
      JSONAssert.assertEquals(expected, response.getBody(), false);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetEmployerFrom07() throws JSONException {
    String expected =
        "{\"username\":\"billGates\",\"password\":\"1234\",\"company\":\"microsoft\",\"notification\":[],\"file\":[],\"coopPosition\":[{\"starting\":\"2019-02-26\",\"finishing\":\"2019-06-26\",\"coopID\":2,\"name\":\"developer\",\"status\":\"Started\",\"semester\":\"Winter\"}]}";
    ResponseEntity<String> response =
        restTemplate.exchange(URL + "employer/billGates", HttpMethod.GET, null, String.class);
    // Check Status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // Check Body
    try {
      JSONAssert.assertEquals(expected, response.getBody(), false);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
