package ca.mcgill.ecse321.cooperator.service_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
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
      service.createEmployer("Google", "google@gmail.com", "123 Sherbrooke");
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
    param.setAddress("123 Sherbrooke");
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
      service.createEmployer(null, "google@gmail.com", "");
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
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find an Employer with email google@gmail.com");
  }

  @Test
  public void testContainsEmployer() {
    String error = null;
    Employer param1 = new Employer();
    param1.setName("Google");
    param1.setEmail("google@gmail.com");
    param1.setAddress("123 Sherbrooke");

    Employer param2 = new Employer();
    param2.setName("Google");
    param2.setEmail("google@gmail.com");
    param2.setAddress("123 Sherbrooke");

    try {
      service.createEmployer(param1);
    } catch (InvalidParameterException e) {
      fail();
    }
    try {
      service.createEmployer(param2);
    } catch (EntityExistsException e) {
      error = e.getMessage();
    }
    assertEquals("Employer Already Exists", error);

  }
}
