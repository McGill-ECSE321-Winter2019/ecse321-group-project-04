package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
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
public class TestCourse {
  @InjectMocks
  private CooperatorService service;

  @Mock
  private CoopCourseRepository coopCourseRepository;

  @Before
  public void mockSetUp() {
    when(coopCourseRepository.save(notNull())).thenAnswer( (InvocationOnMock invocation) ->
    {
      return invocation.getArgument(0);
    });
    
    when(coopCourseRepository.findByCourseCode(anyString())).thenAnswer( (InvocationOnMock invocation) ->
    {
      if (invocation.getArgument(0).equals("ECSE300")) {
        return service.createCoopCourse("ECSE300", 1);
      } else {
        return null;
      }
    });
  }

  @Test
  public void testCreateCoopCourse() {
    CoopCourse param = new CoopCourse();
    param.setCourseCode("ECSE300");
    param.setCoopTerm(1);
    try {
      service.createCoopCourse(param);
    } catch (InvalidParameterException e) {
      fail();
    }
    CoopCourse c = service.getCoopCourse("ECSE300");
    // Check attributes
    assertEquals("ECSE300", c.getCourseCode());
    assertEquals((Integer) 1, c.getCoopTerm());
  }

  @Test
  public void testCreateCoopCourseWithObject() {
    try {
      service.createCoopCourse("ECSE300", 1);
    } catch (InvalidParameterException e) {
      fail();
    }
    CoopCourse c = service.getCoopCourse("ECSE300");
    // Check attributes
    assertEquals("ECSE300", c.getCourseCode());
    assertEquals((Integer) 1, c.getCoopTerm());
  }

  @Test
  public void testCreateNullCodeCoopCourse() {
    String error = null;
    try {
      service.createCoopCourse(null, 1);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your course details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllCoopCourses().size());
  }

  public void testCreateNullTermCoopCourse() {
    String error = null;
    try {
      service.createCoopCourse("ECSE300", null);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your course details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllCoopCourses().size());
  }

  @Test
  public void testCreateNegativeTermCoopCourse() {
    String error = null;
    try {
      service.createCoopCourse("ECSE300", -5);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your course details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllCoopCourses().size());
  }

  @Test
  public void testGetNonexistentCoopCourse() {
    String error = null;
    try {
      service.getCoopCourse("ECSE301");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find a CO-OP Course with ID ECSE301");
  }
}
