package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestStudent {

  private static final String FIRST_NAME = "first_name";
  private static final String LAST_NAME = "last_name";
  private static final Integer MCGILL_ID = 260112233;
  private static final String MCGILL_EMAIL = "student@email.com";

  @InjectMocks
  private CooperatorService service;

  @Mock
  private StudentRepository studentRepository;

  @Before
  public void mockSetUp() {
    when(studentRepository.save(any(Student.class))).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    });

    when(studentRepository.findByMcgillID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
      if ((int) invocation.getArgument(0) == MCGILL_ID) {
        return TestUtil.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
      } else {
        return null;
      }
    });

    when(studentRepository.existsById(anyInt())).thenReturn(false, true);
  }

  @Test
  public void testCreateStudent() {
    // Create and persist a student
    try {
      service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    } catch (InvalidParameterException e) {
      fail();
    }
    // find the student by id
    Student s = service.getStudent(MCGILL_ID);
    // Check attributes
    assertEquals(FIRST_NAME, s.getFirstName());
    assertEquals(LAST_NAME, s.getLastName());
    assertEquals(MCGILL_ID, s.getMcgillID());
    assertEquals(MCGILL_EMAIL, s.getMcgillEmail());
  }

  @Test
  public void testCreateStudentWithObject() {
    // Create and persist a student
    Student param = new Student();
    param.setFirstName(FIRST_NAME);
    param.setLastName(LAST_NAME);
    param.setMcgillID(MCGILL_ID);
    param.setMcgillEmail(MCGILL_EMAIL);
    try {
      service.createStudent(param);
    } catch (InvalidParameterException e) {
      fail();
    }
    // find the student by id
    Student s = service.getStudent(MCGILL_ID);
    // Check attributes
    assertEquals(FIRST_NAME, s.getFirstName());
    assertEquals(LAST_NAME, s.getLastName());
    assertEquals(MCGILL_ID, s.getMcgillID());
    assertEquals(MCGILL_EMAIL, s.getMcgillEmail());
  }

  @Test
  public void testCreateNullNameStudent() {
    String error = null;
    try {
      service.createStudent(null, LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your student details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllStudents().size());
  }

  @Test
  public void testCreateEmptyNameStudent() {
    String error = null;
    try {
      service.createStudent(" ", LAST_NAME, MCGILL_ID, MCGILL_EMAIL);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your student details are incomplete!", error);

    assertEquals(0, service.getAllStudents().size());
  }

  @Test
  public void testCreateNullIDStudent() {
    String error = null;
    try {
      service.createStudent(FIRST_NAME, LAST_NAME, null, MCGILL_EMAIL);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your student details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllStudents().size());
  }

  @Test
  public void testCreateNullEmailStudent() {
    String error = null;
    try {
      service.createStudent(FIRST_NAME, LAST_NAME, MCGILL_ID, null);
    } catch (InvalidParameterException e) {
      error = e.getMessage();
    }
    // check error message
    assertEquals("Your student details are incomplete!", error);
    // check nothing was added
    assertEquals(0, service.getAllStudents().size());
  }

  @Test
  public void testGetNonexistentStudent() {
    String error = null;
    try {
      service.getStudent(260112234);
    } catch (EntityNotFoundException e) {
      error = e.getMessage();
    }

    assertEquals("Could not find a Student with ID 260112234", error);
  }

  @Test
  public void testContainsStudent() {
    Student param = new Student();
    param.setFirstName(FIRST_NAME);
    param.setLastName(LAST_NAME);
    param.setMcgillID(MCGILL_ID);
    param.setMcgillEmail(MCGILL_EMAIL);

    Student param2 = new Student();
    param2.setFirstName(FIRST_NAME);
    param2.setLastName(LAST_NAME);
    param2.setMcgillID(MCGILL_ID);
    param2.setMcgillEmail(MCGILL_EMAIL);

    String error = null;

    try {
      service.createStudent(param);
    } catch (InvalidParameterException e) {
      fail();
    }

    try {
      service.createStudent(param2);
    } catch (EntityExistsException e) {
      error = e.getMessage();
    }

    assertEquals("Student Already Exists", error);
  }

}

