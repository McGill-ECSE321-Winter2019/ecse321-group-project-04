package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.invocation.InvocationOnMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestCourse {
  
  private static final String COURSE_CODE = "ECSE300";
  private static final Integer COURSE_TERM = 1;
  
  @InjectMocks
  private CooperatorService service;

  @Mock
  private CoopCourseRepository coopCourseRepository;

  @Before
  public void mockSetUp() {
    when(coopCourseRepository.save(any(CoopCourse.class))).thenAnswer( (InvocationOnMock invocation) ->
    {
      return TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
    });
    
    when(coopCourseRepository.findByCourseCode(anyString())).thenAnswer( (InvocationOnMock invocation) ->
    {
      if (invocation.getArgument(0).equals(COURSE_CODE)) {
        return TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
      } else {
        return null;
      }
    });
    
    when(coopCourseRepository.existsById(anyString())).thenReturn(false, true);
  }

  @Test
  public void testCreateCoopCourse() {
    CoopCourse param = new CoopCourse();
    param.setCourseCode(COURSE_CODE);
    param.setCoopTerm(COURSE_TERM);
    try {
      service.createCoopCourse(param);
    } catch (InvalidParameterException e) {
      fail();
    }
    CoopCourse c = service.getCoopCourse(COURSE_CODE);
    // Check attributes
    assertEquals(COURSE_CODE, c.getCourseCode());
    assertEquals(COURSE_TERM, c.getCoopTerm());
  }

  @Test
  public void testCreateCoopCourseWithObject() {
    try {
      service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    } catch (InvalidParameterException e) {
      fail();
    }
    CoopCourse c = service.getCoopCourse(COURSE_CODE);
    // Check attributes
    assertEquals(COURSE_CODE, c.getCourseCode());
    assertEquals(COURSE_TERM, c.getCoopTerm());
  }

  @Test
  public void testCreateNullCodeCoopCourse() {
    String error = null;
    try {
      service.createCoopCourse(null, COURSE_TERM);
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
      service.createCoopCourse(COURSE_CODE, null);
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
      service.createCoopCourse(COURSE_CODE, -5);
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
  
  @Test
  public void testContainsCourse() {
      String error = null;
      CoopCourse param1 = new CoopCourse();
      param1.setCourseCode(COURSE_CODE);
      param1.setCoopTerm(COURSE_TERM);
      
      CoopCourse param2 = new CoopCourse();
      param2.setCourseCode(COURSE_CODE);
      param2.setCoopTerm(COURSE_TERM);
      
      try {
          service.createCoopCourse(param1);
      } catch (InvalidParameterException e) {
          fail();
      }
      try {
          service.createCoopCourse(param2);
      } catch (EntityExistsException e) {
          error = e.getMessage();
      }
      assertEquals("Course Already Exists", error);
  }
}
