package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.invocation.InvocationOnMock;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestEmployer {
  @InjectMocks
  private CooperatorService service;

  @Mock
  private EmployerRepository employerRepository;

  @Before
  public void mockSetUp() {
    when(employerRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      return invocation.getArgument(0);
    });

    when(employerRepository.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals("google@gmail.com")) {
        return service.createEmployer("Google", "google@gmail.com");
      } else {
        return null;
      }
    });
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
      service.getEmployer("fb@gmail.com");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find an Employer with email fb@gmail.com");
  }
}
