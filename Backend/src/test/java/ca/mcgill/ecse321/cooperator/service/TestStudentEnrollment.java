package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestStudentEnrollment {
  
  private static final String FIRST_NAME = "f_name";
  private static final String LAST_NAME = "l_name";
  private static final Integer MCGILL_ID = 260654321;
  private static final String MCGILL_EMAIL = "test@mail.com";
  
  private static final String NAME = "Facebook";
  private static final String EMAIL = "fb@email.com";
  
  private static final String COURSE_CODE = "ECSE302";
  private static final Integer COURSE_TERM = 1;
  
  private static final Integer YEAR = 2019;
  private static final Term OFFER_TERM = Term.FALL;
  private static final Boolean ACTIVE = true;
  
  private static final String D1_URL = "test-url-1";
  private static final String D2_URL = "test-url-2";
  private static final String ENROLLMENT_ID = "260654321-ECSE302-F19";
  private static final CourseStatus ENROLLMENT_STATUS = CourseStatus.PASSED;
  
  @InjectMocks
  private CooperatorService service;
  @Mock
  private StudentRepository studentRepository;
  @Mock
  private EmployerRepository employerRepository;
  @Mock
  private CoopCourseRepository coopCourseRepository;
  @Mock
  private CoopCourseOfferingRepository coopCourseOfferingRepository;
  @Mock
  private StudentEnrollmentRepository studentEnrollmentRepository;
  @Mock
  private TaskRepository taskRepository;
  @Mock
  private DocumentRepository documentRepository;

  @Before
  public void mockSetUp() {
    when(studentRepository.save(notNull())).thenAnswer( (InvocationOnMock invocation) ->
    {
      return TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    });
    
    when(employerRepository.save(notNull())).thenAnswer( (InvocationOnMock invocation) ->
    {
      return TestUtil.createEmployer(NAME, EMAIL);
    });
    
    when(coopCourseRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
    });

    when(coopCourseOfferingRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
      return TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
    });

    when(studentEnrollmentRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      Student s = TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
      Employer e = TestUtil.createEmployer(NAME, EMAIL);
      CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
      CoopCourseOffering cco = TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
      return TestUtil.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, e, cco, D1_URL, D2_URL);
    });

    when(studentEnrollmentRepository.findByEnrollmentID(anyString()))
        .thenAnswer((InvocationOnMock invocation) -> {
          if (invocation.getArgument(0).equals(ENROLLMENT_ID)) {
            Student s = TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
            Employer e = TestUtil.createEmployer(NAME, EMAIL);
            CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
            CoopCourseOffering cco = TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
            return TestUtil.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, e, cco, D1_URL, D2_URL);
          } else {
            return null;
          }
        });
  }
  
  @Test
  public void testCreateStudentEnrollment() {
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    CoopCourseOffering cco = service.createCoopCourseOffering(YEAR, Term.FALL, true, c);
    Student s = service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    Employer emp = service.createEmployer(NAME, EMAIL);

    try {
      service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco, D1_URL,
          D2_URL);
    } catch (InvalidParameterException e) {
      fail();
    }

    StudentEnrollment se = service.getStudentEnrollment(ENROLLMENT_ID);

    // Check attributes
    assertEquals(true, se.getActive());
    assertEquals(ENROLLMENT_ID, se.getEnrollmentID());
    assertEquals(CourseStatus.PASSED, se.getStatus());

    // Check references
    assertEquals("ECSE302-F19", se.getCoopCourseOffering().getOfferID());
    assertEquals(MCGILL_EMAIL, se.getEnrolledStudent().getMcgillEmail());
    assertEquals(EMAIL, se.getStudentEmployer().getEmail());

    // Get the 5 default tasks from the student enrollment
    Task t1 = se.getTask("Report CO-OP Position Acceptance");
    Task t2 = se.getTask("Upload Employer Contract");
    Task t3 = se.getTask("Initial Workload Report");
    Task t4 = se.getTask("Technical Experience Report");
    Task t5 = se.getTask("Internship Evaluation Report");

    // Check that the tasks are attached to the enrollment
    assertNotNull(t1);
    assertNotNull(t2);
    assertNotNull(t3);
    assertNotNull(t4);
    assertNotNull(t5);

    // Check that tasks 1 and 2 have the corresponding documents attached
    assertNotNull(t1.getDocument("CO-OP Position Acceptance Form"));
    assertNotNull(t2.getDocument("Employer Contract"));

    // Check that the due dates of the tasks are correct
    Calendar currentCal = Calendar.getInstance();
    Date currentDate = new Date(currentCal.getTimeInMillis());
    Calendar dateInTwoWeeks = Calendar.getInstance();
    dateInTwoWeeks.add(Calendar.DAY_OF_MONTH, +14);
    Calendar dateInFourMonths = Calendar.getInstance();
    dateInFourMonths.add(Calendar.MONTH, +4);

    assertEquals(currentDate.toString(), t1.getDueDate().toString());
    assertEquals(currentDate.toString(), t2.getDueDate().toString());
    assertEquals(new Date(dateInTwoWeeks.getTimeInMillis()).toString(), t3.getDueDate().toString());
    assertEquals(new Date(dateInFourMonths.getTimeInMillis()).toString(),
        t4.getDueDate().toString());
    assertEquals(new Date(dateInFourMonths.getTimeInMillis()).toString(),
        t5.getDueDate().toString());
  }

  @Test
  public void testCreateStudentEnrollmentWithObject() {
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    CoopCourseOffering cco = service.createCoopCourseOffering(YEAR, Term.FALL, true, c);
    Student s = service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    Employer emp = service.createEmployer(NAME, EMAIL);

    StudentEnrollment param = new StudentEnrollment();
    param.setActive(true);
    param.setStatus(CourseStatus.PASSED);

    try {
      service.createStudentEnrollment(param, s, emp, cco, D1_URL, D2_URL);
    } catch (InvalidParameterException e) {
      fail();
    }

    StudentEnrollment se = service.getStudentEnrollment(ENROLLMENT_ID);

    // Check attributes
    assertEquals(true, se.getActive());
    assertEquals(ENROLLMENT_ID, se.getEnrollmentID());
    assertEquals(CourseStatus.PASSED, se.getStatus());

    // Check references
    assertEquals("ECSE302-F19", se.getCoopCourseOffering().getOfferID());
    assertEquals(MCGILL_EMAIL, se.getEnrolledStudent().getMcgillEmail());
    assertEquals(EMAIL, se.getStudentEmployer().getEmail());

    // Get the 5 default tasks from the student enrollment
    Task t1 = se.getTask("Report CO-OP Position Acceptance");
    Task t2 = se.getTask("Upload Employer Contract");
    Task t3 = se.getTask("Initial Workload Report");
    Task t4 = se.getTask("Technical Experience Report");
    Task t5 = se.getTask("Internship Evaluation Report");

    // Check that the tasks are attached to the enrollment
    assertNotNull(t1);
    assertNotNull(t2);
    assertNotNull(t3);
    assertNotNull(t4);
    assertNotNull(t5);

    // Check that tasks 1 and 2 have the corresponding documents attached
    assertNotNull(t1.getDocument("CO-OP Position Acceptance Form"));
    assertNotNull(t2.getDocument("Employer Contract"));

    // Check that the due dates of the tasks are correct
    Calendar currentCal = Calendar.getInstance();
    Date currentDate = new Date(currentCal.getTimeInMillis());
    Calendar dateInTwoWeeks = Calendar.getInstance();
    dateInTwoWeeks.add(Calendar.DAY_OF_MONTH, +14);
    Calendar dateInFourMonths = Calendar.getInstance();
    dateInFourMonths.add(Calendar.MONTH, +4);

    assertEquals(currentDate.toString(), t1.getDueDate().toString());
    assertEquals(currentDate.toString(), t2.getDueDate().toString());
    assertEquals(new Date(dateInTwoWeeks.getTimeInMillis()).toString(), t3.getDueDate().toString());
    assertEquals(new Date(dateInFourMonths.getTimeInMillis()).toString(),
        t4.getDueDate().toString());
    assertEquals(new Date(dateInFourMonths.getTimeInMillis()).toString(),
        t5.getDueDate().toString());
  }

  @Test
  public void testCreateNullStatusStudentEnrollment() {
    String error = null;
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    CoopCourseOffering cco = service.createCoopCourseOffering(YEAR, Term.FALL, true, c);
    Student s = service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    Employer emp = service.createEmployer(NAME, EMAIL);

    try {
      service.createStudentEnrollment(null, CourseStatus.PASSED, s, emp, cco, D1_URL,
          D2_URL);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your student enrollment details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllStudentEnrollments().size());

  }

  @Test
  public void testGetNonexistentStudentEnrollment() {
    String error = null;
    try {
      service.getStudentEnrollment("260112233-ECSE300");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find a Student Enrollment with ID 260112233-ECSE300");
  }
}
