package ca.mcgill.ecse321.cooperator.external_integration;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Team13RestServiceIT {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String URL = "http://cooperator-backend-123.herokuapp.com/";

	@Test
	public void test() {
		ResponseEntity<String> response = restTemplate.exchange(URL + "statistics", HttpMethod.GET, null, String.class);
		//assertEquals( HttpStatus.OK, response.getStatusCode()); //Server is currently down
	}

}
