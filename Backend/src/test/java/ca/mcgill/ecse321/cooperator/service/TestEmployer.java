package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEmployer {
	@Autowired
	private CooperatorService service;

	@Autowired
	private EmployerRepository employerRepository;

	@After
	public void cleanDataBase() {
		employerRepository.deleteAll();
	}

	@Test
	public void testCreateEmployer() {
		try {
			service.createEmployer("Google", "google@gmail.com");
		} catch (InvalidParameterException e) {
			fail();
		}

		Employer e = service.getEmployer("google@gmail.com");
		// Check attributes
		assertEquals("Google", e.getName());
		assertEquals("google@gmail.com", e.getEmail());

		assertEquals(1, service.getAllEmployers().size());
	}

	@Test
	public void testCreateEmployerWithObject() {
		Employer param = new Employer();
		param.setName("Google");
		param.setEmail("google@gmail.com");
		try {
			service.createEmployer(param);
		} catch (InvalidParameterException e) {
			fail();
		}

		Employer e = service.getEmployer("google@gmail.com");
		// Check attributes
		assertEquals("Google", e.getName());
		assertEquals("google@gmail.com", e.getEmail());

		assertEquals(1, service.getAllEmployers().size());
	}
	
	@Test
	public void testCreateNullNameEmployer() {
		String error = null;
		try {
			service.createEmployer(null, "google@gmail.com");
		} catch (InvalidParameterException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your employer details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllEmployers().size());
	}
	
	@Test
	public void testGetNonexistentEmployer() {
		String error = null;
		try {
			service.getEmployer("google@gmail.com");
		} catch (EntityNotFoundException e){
			error = e.getMessage();
		}
		
		assertEquals(error, "Could not find an Employer with email google@gmail.com");
	}
}