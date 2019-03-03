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
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestCourseOffering {

  private static final String COURSE_CODE = "ECSE301";
  private static final Integer COURSE_TERM = 1;

  private static final Integer YEAR = 2018;
  private static final Term OFFER_TERM = Term.WINTER;
  private static final Boolean ACTIVE = true;
  private static final String OFFER_ID = "ECSE301-W18";

  @InjectMocks
  private CooperatorService service;

  @Mock
  private CoopCourseRepository coopCourseRepository;

  @Mock
  private CoopCourseOfferingRepository coopCourseOfferingRepository;

  @Before
  public void mockSetUp() {
    when(coopCourseRepository.save(any(CoopCourse.class)))
        .thenAnswer((InvocationOnMock invocation) -> {
          return TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
        });

    when(coopCourseOfferingRepository.save(any(CoopCourseOffering.class)))
        .thenAnswer((InvocationOnMock invocation) -> {
          CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
          return TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
        });

    when(coopCourseOfferingRepository.findByOfferID(anyString()))
        .thenAnswer((InvocationOnMock invocation) -> {
          if (invocation.getArgument(0).equals(OFFER_ID)) {
            CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
            return TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
          } else {
            return null;
          }
        });

    when(coopCourseOfferingRepository.existsById(anyString())).thenReturn(false, true);
  }

  @Test
  public void testCreateCoopCourseOffering() {
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);

    try {
      service.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, c);
    } catch (InvalidParameterException e) {
      fail();
    }

    CoopCourseOffering cco = service.getCoopCourseOffering(OFFER_ID);
    // Check attributes
    assertEquals(YEAR, cco.getYear());
    assertEquals(OFFER_TERM, cco.getTerm());
    assertEquals(ACTIVE, cco.getActive());
    assertEquals(OFFER_ID, cco.getOfferID());
    // Check references
    assertEquals(COURSE_CODE, cco.getCoopCourse().getCourseCode());
  }

  @Test
  public void testCreateCoopCourseOfferingWithObject() {
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);

    CoopCourseOffering param = new CoopCourseOffering();
    param.setYear(YEAR);
    param.setTerm(OFFER_TERM);
    param.setActive(ACTIVE);

    try {
      service.createCoopCourseOffering(param, c);
    } catch (InvalidParameterException e) {
      fail();
    }

    CoopCourseOffering cco = service.getCoopCourseOffering(OFFER_ID);
    // Check attributes
    assertEquals(YEAR, cco.getYear());
    assertEquals(OFFER_TERM, cco.getTerm());
    assertEquals(ACTIVE, cco.getActive());
    assertEquals(OFFER_ID, cco.getOfferID());
    // Check references
    assertEquals(COURSE_CODE, cco.getCoopCourse().getCourseCode());
  }

  @Test
  public void testCreateNullYearCoopCourseOffering() {
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    String error = null;
    try {
      service.createCoopCourseOffering(null, OFFER_TERM, ACTIVE, c);
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
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    String error = null;
    try {
      service.createCoopCourseOffering(YEAR, null, ACTIVE, c);
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
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    String error = null;
    try {
      service.createCoopCourseOffering(YEAR, OFFER_TERM, null, c);
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

  @Test
  public void testContainsCourseOffering() {
    String error = null;
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);

    CoopCourseOffering param1 = new CoopCourseOffering();
    param1.setYear(YEAR);
    param1.setTerm(OFFER_TERM);
    param1.setActive(ACTIVE);

    CoopCourseOffering param2 = new CoopCourseOffering();
    param2.setYear(YEAR);
    param2.setTerm(OFFER_TERM);
    param2.setActive(ACTIVE);

    try {
      service.createCoopCourseOffering(param1, c);
    } catch (InvalidParameterException e) {
      fail();
    }
    try {
      service.createCoopCourseOffering(param2, c);
    } catch (EntityExistsException e) {
      error = e.getMessage();
    }

    assertEquals("Offering Already Exists", error);
  }
}
