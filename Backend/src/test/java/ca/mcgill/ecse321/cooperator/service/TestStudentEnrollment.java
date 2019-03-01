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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestStudentEnrollment {
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
      return invocation.getArgument(0);
    });
    
    when(employerRepository.save(notNull())).thenAnswer( (InvocationOnMock invocation) ->
    {
      return invocation.getArgument(0);
    });
    
    when(coopCourseRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      return invocation.getArgument(0);
    });

    when(coopCourseOfferingRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      return invocation.getArgument(0);
    });

    when(studentEnrollmentRepository.save(notNull())).thenAnswer((InvocationOnMock invocation) -> {
      return invocation.getArgument(0);
    });

    when(studentEnrollmentRepository.findByEnrollmentID(anyString()))
        .thenAnswer((InvocationOnMock invocation) -> {
          if (invocation.getArgument(0).equals("260654321-ECSE302-F19")) {
            CoopCourse c = service.createCoopCourse("ECSE302", 1);
            CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
            Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
            Employer emp = service.createEmployer("Facebook", "fb@email.com");
            return service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco,
                "test-url-1", "test-url-2");
          } else {
            return null;
          }
        });
  }

  @Test
  public void testCreateStudentEnrollment() {
    CoopCourse c = service.createCoopCourse("ECSE302", 1);
    CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
    Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
    Employer emp = service.createEmployer("Facebook", "fb@email.com");

    try {
      service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco, "test-url-1",
          "test-url-2");
    } catch (InvalidParameterException e) {
      fail();
    }

    StudentEnrollment se = service.getStudentEnrollment("260654321-ECSE302-F19");

    // Check attributes
    assertEquals(true, se.getActive());
    assertEquals("260654321-ECSE302-F19", se.getEnrollmentID());
    assertEquals(CourseStatus.PASSED, se.getStatus());

    // Check references
    assertEquals("ECSE302-F19", se.getCoopCourseOffering().getOfferID());
    assertEquals("test@mail.com", se.getEnrolledStudent().getMcgillEmail());
    assertEquals("fb@email.com", se.getStudentEmployer().getEmail());
    /* This should probably be moved to a different test */
    assertEquals(se.getEnrollmentID(),
        service.getEmployersStudentEnrollments(emp).get(0).getEnrollmentID());

    // Check that only one student enrollment exists in the database
    assertEquals(1, service.getAllStudentEnrollments().size());

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

    // Check that the documents can be accessed in the database on their own
    assertEquals("test-url-1", service.getDocument("test-url-1").getUrl());
    assertEquals("test-url-2", service.getDocument("test-url-2").getUrl());
  }

  @Test
  public void testCreateStudentEnrollmentWithObject() {
    CoopCourse c = service.createCoopCourse("ECSE302", 1);
    CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
    Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
    Employer emp = service.createEmployer("Facebook", "fb@email.com");

    StudentEnrollment param = new StudentEnrollment();
    param.setActive(true);
    param.setStatus(CourseStatus.PASSED);

    try {
      service.createStudentEnrollment(param, s, emp, cco, "test-url-1", "test-url-2");
    } catch (InvalidParameterException e) {
      fail();
    }

    StudentEnrollment se = service.getStudentEnrollment("260654321-ECSE302-F19");

    // Check attributes
    assertEquals(true, se.getActive());
    assertEquals("260654321-ECSE302-F19", se.getEnrollmentID());
    assertEquals(CourseStatus.PASSED, se.getStatus());

    // Check references
    assertEquals("ECSE302-F19", se.getCoopCourseOffering().getOfferID());
    assertEquals("test@mail.com", se.getEnrolledStudent().getMcgillEmail());
    assertEquals("fb@email.com", se.getStudentEmployer().getEmail());
    /* This should probably be moved to a different test */
    assertEquals(se.getEnrollmentID(),
        service.getEmployersStudentEnrollments(emp).get(0).getEnrollmentID());

    // Check that only one student enrollment exists in the database

    assertEquals(1, service.getAllStudentEnrollments().size());

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

    // Check that the documents can be accessed in the database on their own
    assertEquals("test-url-1", service.getDocument("test-url-1").getUrl());
    assertEquals("test-url-2", service.getDocument("test-url-2").getUrl());

  }

  @Test
  public void testCreateNullStatusStudentEnrollment() {
    String error = null;
    CoopCourse c = service.createCoopCourse("ECSE302", 1);
    CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
    Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
    Employer emp = service.createEmployer("Facebook", "fb@email.com");

    try {
      service.createStudentEnrollment(null, CourseStatus.PASSED, s, emp, cco, "test-url-1",
          "test-url-2");
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
