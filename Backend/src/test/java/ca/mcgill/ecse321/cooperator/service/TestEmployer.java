package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestEmployer {

  private static final String NAME = "Google";
  private static final String ADDRESS = "845 rue Sherbrookes";
  private static final String EMAIL = "google@gmail.com";

  @InjectMocks
  private CooperatorService service;

  @Mock
  private EmployerRepository employerRepository;

  @Before
  public void mockSetUp() {
    when(employerRepository.save(any(Employer.class))).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createEmployer(NAME, EMAIL, ADDRESS);
    });

    when(employerRepository.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EMAIL)) {
        return TestUtil.createEmployer(NAME, EMAIL, ADDRESS);
      } else {
        return null;
      }
    });

    when(employerRepository.existsById(anyString())).thenReturn(false, true);
  }

  @Test
  public void testCreateEmployer() {
    try {
      service.createEmployer(NAME, EMAIL, ADDRESS);
    } catch (InvalidParameterException e) {
      fail();
    }
   
    Employer e = service.getEmployer(EMAIL);
    // Check attributes
    assertEquals(NAME, e.getName());
    assertEquals(EMAIL, e.getEmail());
    assertEquals(ADDRESS, e.getAddress());
  }

  @Test
  public void testCreateEmployerWithObject() {
    Employer param = new Employer();
    param.setName(NAME);
    param.setEmail(EMAIL);
    param.setAddress(ADDRESS);
    try {
      service.createEmployer(param);
    } catch (InvalidParameterException e) {
      fail();
    }

    Employer e = service.getEmployer(EMAIL);
    System.out.print(e.getAddress());
    // Check attributes
    assertEquals(NAME, e.getName());
    assertEquals(EMAIL, e.getEmail());
    assertEquals(ADDRESS, e.getAddress());
  }

  @Test
  public void testCreateNullNameEmployer() {
    String error = null;
    try {
      service.createEmployer(null, EMAIL, ADDRESS);
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

  @Test
  public void testContainsEmployer() {
    String error = null;
    Employer param1 = new Employer();
    param1.setName(NAME);
    param1.setEmail(EMAIL);
    param1.setAddress(ADDRESS);

    Employer param2 = new Employer();
    param2.setName(NAME);
    param2.setEmail(EMAIL);
    param2.setAddress(ADDRESS);

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
