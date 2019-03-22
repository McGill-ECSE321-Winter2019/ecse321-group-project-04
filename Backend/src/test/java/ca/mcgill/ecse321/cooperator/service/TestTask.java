package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.sql.Date;
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
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
// @RunWith(SpringRunner.class)
@SpringBootTest

public class TestTask {

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
      return TestUtil.createEmployer(NAME, EMAIL, "");
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
          Employer e = TestUtil.createEmployer(NAME, EMAIL, "");
          CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
          CoopCourseOffering cco = TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
          StudentEnrollment se = TestUtil.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, e,
              cco, D1_URL, D2_URL);
          Task t = TestUtil.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS);
          se.addCourseTasks(t);
          return se;
        });

    when(studentEnrollmentRepository.findByEnrollmentID(anyString()))
        .thenAnswer((InvocationOnMock invocation) -> {
          if (invocation.getArgument(0).equals(ENROLLMENT_ID)) {
            Student s = TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
            Employer e = TestUtil.createEmployer(NAME, EMAIL, "");
            CoopCourse cc = TestUtil.createCoopCourse(COURSE_CODE, COURSE_TERM);
            CoopCourseOffering cco =
                TestUtil.createCoopCourseOffering(YEAR, OFFER_TERM, ACTIVE, cc);
            StudentEnrollment se = TestUtil.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, e,
                cco, D1_URL, D2_URL);
            Task t = TestUtil.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS);
            se.addCourseTasks(t);
            return se;
          } else {
            return null;
          }
        });
  }

  @Test
  public void testCreateTask() {
    long taskID = -1;

    // Create chain of objects required to create a task
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    CoopCourseOffering cco = service.createCoopCourseOffering(YEAR, Term.FALL, ACTIVE, c);
    Student s = service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    Employer emp = service.createEmployer(NAME, EMAIL, ADDRESS);
    StudentEnrollment se = service.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, emp, cco,
        D1_URL, D2_URL, START_DATE, END_DATE, WORK_PERMIT, JOB_ID);

    try {
      Task t =
          service.createTask(TASK_NAME, TASK_DESC, TASK_DATE, TASK_STATUS, se.getEnrollmentID());
      taskID = t.getTaskID();
    } catch (InvalidParameterException e) {
      fail();
    }
    Task t = service.getStudentEnrollment(se.getEnrollmentID()).getTask(TASK_NAME);

    // check attributes
    assertEquals(TASK_NAME, t.getName());
    assertEquals(TASK_DESC, t.getDescription());
    assertEquals(TASK_DATE, t.getDueDate());
    assertEquals(TASK_STATUS, t.getTaskStatus());
    assertEquals(taskID, t.getTaskID());
  }

  @Test
  public void testCreateTaskWithObject() {
    // Create chain of objects required to create a task
    CoopCourse c = service.createCoopCourse(COURSE_CODE, COURSE_TERM);
    CoopCourseOffering cco = service.createCoopCourseOffering(YEAR, Term.FALL, ACTIVE, c);
    Student s = service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    Employer emp = service.createEmployer(NAME, EMAIL, ADDRESS);
    StudentEnrollment se = service.createStudentEnrollment(ACTIVE, ENROLLMENT_STATUS, s, emp, cco,
        D1_URL, D2_URL, START_DATE, END_DATE, WORK_PERMIT, JOB_ID);

    Task param = new Task();
    param.setName(TASK_NAME);
    param.setDescription(TASK_DESC);
    param.setDueDate(TASK_DATE);
    param.setTaskStatus(TASK_STATUS);

    try {
      service.createTask(param, se.getEnrollmentID());
    } catch (InvalidParameterException e) {
      fail();
    }
    Task t = service.getStudentEnrollment(se.getEnrollmentID()).getTask(TASK_NAME);
    // Task t = service.getTask(taskID);
    // check attributes
    assertEquals(TASK_NAME, t.getName());
    assertEquals(TASK_DESC, t.getDescription());
    assertEquals(TASK_DATE, t.getDueDate());
    assertEquals(TASK_STATUS, t.getTaskStatus());
  }

  @Test
  public void testCreateNullNameTask() {
    String error = null;

    try {
      service.createTask(null, TASK_DESC, TASK_DATE, TASK_STATUS, null);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your task details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllTasks().size());

  }

  @Test
  public void testCreateNullDescriptionTask() {
    String error = null;

    try {
      service.createTask(TASK_NAME, null, TASK_DATE, TASK_STATUS, null);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your task details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllTasks().size());

  }

  @Test
  public void testGetNonexistentTask() {
    String error = null;
    try {
      service.getTask(1234567);
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals(error, "Could not find a Task with ID 1234567");
  }

}
