package ca.mcgill.ecse321.cooperator.controller_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.CooperatorApplication;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoopCourseRestIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Autowired
	private CoopCourseRepository coopCourseRepository;

	@Before
	@After
	public void cleanDataBase() {

		coopCourseRepository.deleteAll();

	}

	@Test
	public void createCourse() {

		CoopCourse course = new CoopCourse();

		course.setCourseCode("EBUC1000");
		course.setCoopTerm(2);

		HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST,
				entity, String.class);

		String result = response.getBody().toString();

		assertTrue(result.contains("/coopCourses/EBUC1000"));
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void createNullCodeCourse(){

		CoopCourse course = new CoopCourse();

		course.setCourseCode(null);
		course.setCoopTerm(2);

		HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST,
				entity, String.class);

		String result = response.getBody().toString();
		assertTrue(result.contains("Your course details are incomplete!"));
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
	}

	@Test
	public void createNullTermCourse()  {

		CoopCourse course = new CoopCourse();

		course.setCourseCode("EBUC1000");
		course.setCoopTerm(null);

		HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST,
				entity, String.class);

		String result = response.getBody().toString();
		assertTrue(result.contains("Your course details are incomplete!"));
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
	}

	@Test
	public void createNegativeTermCourse()  {

		CoopCourse course = new CoopCourse();

		course.setCourseCode("EBUC1000");
		course.setCoopTerm(-1);

		HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST,
				entity, String.class);

		String result = response.getBody().toString();

		assertTrue(result.contains("Your course details are incomplete!"));
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
	}
	
	@Test
	public void createCourseTwice()  {

		CoopCourse course = new CoopCourse();

		course.setCourseCode("EBUC1000");
		course.setCoopTerm(2);

		HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST,
				entity, String.class);

		String result = response.getBody().toString();

		assertTrue(result.contains("/coopCourses/EBUC1000"));
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		response= restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST,
				entity, String.class);
		result = response.getBody().toString();
		
		
		assertTrue(result.contains("Course Already Exists"));
		assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
