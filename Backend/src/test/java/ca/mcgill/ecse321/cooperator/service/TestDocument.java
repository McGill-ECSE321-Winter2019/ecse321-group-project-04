package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.Calendar;
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
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestDocument {

  private static final String FIRST_NAME = "f_name";
  private static final String LAST_NAME = "l_name";
  private static final Integer MCGILL_ID = 260654321;
  private static final String MCGILL_EMAIL = "test@mail.com";

  private static final String NAME = "Facebook";
  private static final String EMAIL = "fb@email.com";
  private static final String ADDRESS = "845 rue Sherbrookes";

  private static final String COURSE_CODE = "ECSE302";
  private static final Integer COURSE_TERM = 1;

  private static final Integer YEAR = 2019;
  private static final Term OFFER_TERM = Term.FALL;
  private static final Boolean ACTIVE = true;

  private static final String D1_URL = "test-url-1";
  private static final String D2_URL = "test-url-2";
  private static final String ENROLLMENT_ID = "260654321-ECSE302-F19";
  private static final CourseStatus ENROLLMENT_STATUS = CourseStatus.PASSED;
  @SuppressWarnings("deprecation")
  private static final Date START_DATE = new Date(2019, 05, 15);
  @SuppressWarnings("deprecation")
  private static final Date END_DATE = new Date(2019, 11, 15);
  private static final Boolean WORK_PERMIT = true;
  private static final String JOB_ID = "ABC123456";

  private static final String TASK_NAME = "Task name";
  private static final String TASK_DESC = "Some description";
  @SuppressWarnings("deprecation")
  private static final Date TASK_DATE = new Date(2019, 1, 1);
  private static final TaskStatus TASK_STATUS = TaskStatus.COMPLETED;

  private static final String DOC_NAME = "test-url-1";
  private static final String DOC_URL = "http://test-url.this/is/just/for/testing";

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
    when(studentRepository.save(any(Student.class))).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    });

    when(employerRepository.save(any(Employer.class))).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createEmployer(NAME, EMAIL,"");
    });

    when(coopCourseRepository.save(any(CoopCourse.class)))
        .thenAnswer((InvocationOnMock invocation) -> {
          return TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
        });

    when(coopCourseOfferingRepository.save(any(CoopCourseOffering.class)))
        .thenAnswer((InvocationOnMock invocation) -> {
          CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
          return TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
        });

    when(studentEnrollmentRepository.save(any(StudentEnrollment.class)))
        .thenAnswer((InvocationOnMock invocation) -> {
          Student s = TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
          Employer e = TestUtil.createEmployer(NAME, EMAIL,"");
          CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
          CoopCourseOffering cco = TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
          StudentEnrollment se = TestUtil.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, e,
              cco, D1_URL, D2_URL, START_DATE, END_DATE, WORK_PERMIT, JOB_ID);
          Task t = TestUtil.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS);
          se.addCourseTasks(t);
          return se;
        });

    when(studentEnrollmentRepository.findByEnrollmentID(anyString()))
        .thenAnswer((InvocationOnMock invocation) -> {
          if (invocation.getArgument(0).equals(ENROLLMENT_ID)) {
            Student s = TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
            Employer e = TestUtil.createEmployer(NAME, EMAIL,"");
            CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
            CoopCourseOffering cco =
                TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
            StudentEnrollment se = TestUtil.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, e,
                cco, D1_URL, D2_URL, START_DATE, END_DATE, WORK_PERMIT, JOB_ID);
            Task t = TestUtil.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS);
            se.addCourseTasks(t);
            return se;
          } else {
            return null;
          }
        });

    when(taskRepository.save(any(Task.class))).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS);
    });

    when(documentRepository.findByUrl(anyString())).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(DOC_URL)) {
        return TestUtil.createDocument(DOC_NAME, DOC_URL);
      } else {
        return null;
      }
    });
  }

  @Test
  public void testCreateDocument() {
    // Create chain of objects required to create a task
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    CoopCourseOffering cco = service.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, c);
    Student s = service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    Employer emp = service.createEmployer(NAME, EMAIL, ADDRESS);
    StudentEnrollment se =
        service.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, emp, cco, D1_URL, D2_URL, START_DATE, END_DATE, WORK_PERMIT, JOB_ID);
    Task t = service.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS, se.getEnrollmentID());

    Document param = new Document();
    param.setName(DOC_NAME);
    param.setUrl(DOC_URL);

    try {
      service.createDocument(param, se.getEnrollmentID(), t.getName());
    } catch (InvalidParameterException e) {
      fail();
    }
    Document d = service.getDocument(DOC_URL);

    assertEquals(DOC_NAME, d.getName());
    
    Calendar currentCal = Calendar.getInstance();
    Date currentDate = new Date(currentCal.getTimeInMillis());
    assertEquals(currentDate.toString(), d.getSubmissionDate().toString());
  }

  @Test
  public void testCreateNullNameDocument() {
    String error = null;

    try {
      service.createDocument(null, DOC_URL, null, null);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your document details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllDocuments().size());
  }

  @Test
  public void testGetNonexistentDocument() {
    String error = null;
    try {
      service.getDocument("test-url-1");
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find a Document with URL test-url-1");
  }

}
