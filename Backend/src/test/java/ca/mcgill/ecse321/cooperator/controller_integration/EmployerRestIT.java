package ca.mcgill.ecse321.cooperator.controller_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.persistence.EntityExistsException;

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
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

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

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity,
				String.class);

		String result = response.getBody().toString();

		assertTrue(result.contains("/employers/tom@email.com"));
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void createNullNameEmployer() {

		Employer employer = new Employer();

		employer.setName(null);
		employer.setEmail("tom@email.com");

		HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);
		ResponseEntity<String> response = null;
		try {
		response = restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity,
				String.class);
		}catch (InvalidParameterException e) {
			fail();
		}

		String result = response.getBody().toString();
		System.out.println(result);
		assertTrue(result.contains("Your employer details are incomplete!"));
		assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Test
	public void createEmployerTwice() {

		Employer employer = new Employer();

		employer.setName("Tom");
		employer.setEmail("tom@email.com");

		HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity,
				String.class);

		String result = response.getBody().toString();

		assertTrue(result.contains("/employers/tom@email.com"));
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
		response = restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity, String.class);

		result = response.getBody().toString();

		assertTrue(result.contains("Employer Already Exists"));
		assertEquals(response.getStatusCode(), HttpStatus.I_AM_A_TEAPOT);

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
