package ca.mcgill.ecse321.cooperator.external_integration;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class Team13RestServiceIT {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;
  // TEAM 13: Coop Administrator
  private static final String URL = "http://cooperator-backend-123.herokuapp.com/";

//  @Test
//  public void testGetStudentStatusFrom13() {
//    // The server responds with HTTP Status 502 or 503 for any request, waiting for confirmation from team
//    // 13
//    ResponseEntity<String> response =
//        restTemplate.exchange(URL + "student/{name}", HttpMethod.GET, null, String.class);
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//  }
  
  @Test
  public void placeHolderWhileServiceIsDown() {
    // Remove this test when the server will be working
    @SuppressWarnings("unused")
    ResponseEntity<String> response =
        restTemplate.exchange(URL + "allStudents", HttpMethod.GET, null, String.class);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE);
    //assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
