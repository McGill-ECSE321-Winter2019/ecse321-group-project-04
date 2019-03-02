package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Term;
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
public class TestCourseOffering {
  @InjectMocks
  private CooperatorService service;
  
  @Mock
  private CoopCourseRepository coopCourseRepository;
  
  @Mock
  private CoopCourseOfferingRepository coopCourseOfferingRepository;

  @Before
  public void mockSetUp() {
    when(coopCourseRepository.save(notNull())).thenAnswer( (InvocationOnMock invocation) ->
    {
      return invocation.getArgument(0);
    });
    
    when(coopCourseOfferingRepository.save(notNull())).thenAnswer( (InvocationOnMock invocation) ->
    {
      return invocation.getArgument(0);
    });
    
    when(coopCourseOfferingRepository.findByOfferID(anyString())).thenAnswer( (InvocationOnMock invocation) ->
    {
      if (invocation.getArgument(0).equals("ECSE301-W18")) {
        CoopCourse c = service.createCoopCourse("ECSE301", 1);
        return service.createCoopCourseOffering(2018, Term.WINTER, true, c);
      } else {
        return null;
      }
    });
  }

  @Test
  public void testCreateCoopCourseOffering() {
    CoopCourse c = service.createCoopCourse("ECSE301", 1);

    try {
      service.createCoopCourseOffering(2018, Term.WINTER, true, c);
    } catch (InvalidParameterException e) {
      fail();
    }

    CoopCourseOffering cco = service.getCoopCourseOffering("ECSE301-W18");
    // Check attributes
    assertEquals((Integer) 2018, cco.getYear());
    assertEquals(Term.WINTER, cco.getTerm());
    assertEquals(true, cco.getActive());
    assertEquals("ECSE301-W18", cco.getOfferID());
    // Check references
    assertEquals("ECSE301", cco.getCoopCourse().getCourseCode());
  }

  @Test
  public void testCreateCoopCourseOfferingWithObject() {
    CoopCourse c = service.createCoopCourse("ECSE301", 1);

    CoopCourseOffering param = new CoopCourseOffering();
    param.setYear(2018);
    param.setTerm(Term.WINTER);
    param.setActive(true);

    try {
      service.createCoopCourseOffering(param, c);
    } catch (InvalidParameterException e) {
      fail();
    }

    CoopCourseOffering cco = service.getCoopCourseOffering("ECSE301-W18");
    // Check attributes
    assertEquals((Integer) 2018, cco.getYear());
    assertEquals(Term.WINTER, cco.getTerm());
    assertEquals(true, cco.getActive());
    assertEquals("ECSE301-W18", cco.getOfferID());
    // Check references
    assertEquals("ECSE301", cco.getCoopCourse().getCourseCode());
  }

  @Test
  public void testCreateNullYearCoopCourseOffering() {
    CoopCourse c = service.createCoopCourse("ECSE301", 1);
    String error = null;
    try {
      service.createCoopCourseOffering(null, Term.WINTER, true, c);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your course offering details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllCoopCourseOfferings().size());
  }

  @Test
  public void testCreateNullTermCoopCourseOffering() {
    CoopCourse c = service.createCoopCourse("ECSE301", 1);
    String error = null;
    try {
      service.createCoopCourseOffering(2018, null, true, c);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your course offering details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllCoopCourseOfferings().size());
  }

  @Test
  public void testCreateNullActiveCoopCourseOffering() {
    CoopCourse c = service.createCoopCourse("ECSE301", 1);
    String error = null;
    try {
      service.createCoopCourseOffering(2018, Term.WINTER, null, c);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your course offering details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllCoopCourseOfferings().size());
  }

  @Test
  public void testGetNonexistentCoopCourseOffering() {
    String error = null;
    try {
      service.getCoopCourseOffering("ECSE301-F19");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find a CO-OP Course Offering with ID ECSE301-F19");
  }
}
