package ca.mcgill.ecse321.cooperator;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.dao.Ecse321GroupProject04ApplicationRepsitory;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Ecse321GroupProject04ApplicationTests {
	
	// Employer
	private static final String EMPLOYER_NAME = "Google";
	private static final String EMPLOYER_EMAIL = "google@gmail.com";
	// Course
	private static final String COURSE_CODE = "ECSE3XX";
	private static final Integer COURSE_TERM = 2;
	private static final String COURSE_ID = "ECSE3XX-W19";
	// Student
	private static final String STUDENT_FIRST_NAME = "first name";
	private static final String STUDENT_LAST_NAME = "last name";
	private static final Integer STUDENT_ID = 12345678;
	private static final String STUDENT_EMAIL = "name@mail.mcgill.ca";
	//Co-op course Offering
	private static final Integer YEAR = 2018;
	private static final Term TERM = Term.FALL;
	private static final Boolean ACTIVE = true;
	private static final String OFFER_ID = "1";
	private static final StudentEnrollment SE = new StudentEnrollment();
	//Document
	private static final String DOCUMENT_NAME = "some name";
	private static final String DOCUMENT_URL = "www.onedrive.com/...";
	private static final Task TASK = new Task();
	//Task
	private static final Date DATE = new Date(2019,1,1);
	private static final TaskStatus STATUS = TaskStatus.COMPLETED; 
	
	@Autowired
	Ecse321GroupProject04ApplicationRepsitory dao;

	@Test
	public void testCreateEmployer() {
		// create an employer
		dao.createEmployer(EMPLOYER_NAME, EMPLOYER_EMAIL);
		// search for employer by email
		Employer e = dao.getEmployer(EMPLOYER_EMAIL);
		
		String name =e.getName();
		assertEquals(EMPLOYER_NAME, name);
		
		String email = e.getEmail();
		assertEquals(EMPLOYER_EMAIL, email);
	}
	
	@Test
	public void testCreateCoopCourse() {
		// create a Co-op Course
		dao.createCoopCourse(COURSE_CODE, COURSE_TERM, COURSE_ID);
		//search for co-op course by ID
		CoopCourse c = dao.getCoopCourse(COURSE_ID);
		
		String code = c.getCourseCode();
		assertEquals(COURSE_CODE, code);
		
		Integer term =c.getCoopTerm();
		assertEquals(COURSE_TERM, term);
		
		String id = c.getCoopCourseID();
		assertEquals(COURSE_ID, id);
	}
	
	@Test
	public void testCreateStudent() {
		// create a student
		dao.createStudent(STUDENT_FIRST_NAME, STUDENT_LAST_NAME, STUDENT_ID, STUDENT_EMAIL);
		// search for student by id 
		Student s = dao.getStudent(STUDENT_ID);
		
		String firstName= s.getFirstName();
		assertEquals(STUDENT_FIRST_NAME, firstName);
		
		String lastName = s.getLastName();
		assertEquals(STUDENT_LAST_NAME, lastName);
		
		Integer studentID = s.getMcgillID();
		assertEquals(STUDENT_ID, studentID);
		
		String studentEmail = s.getMcgillEmail();
		assertEquals(STUDENT_EMAIL, studentEmail);
	}
	
	/*The rest of the read/write tests need to be changed --classes with composition relations leave a null row in the table */
	
//	@Test
//	public void testCreateCoopCourseOffering() {
//		// create Co-op course offering
//		dao.createCoopCourseOffering(YEAR, TERM, ACTIVE, OFFER_ID);
//		// search for co-op course offering by ID
//		CoopCourseOffering cco = dao.getCoopCourseOffering(OFFER_ID);
//	}
//	
//	@Test
//	public void testCreateTask() {
//		// create a task
//		Date dueDate =new Date(2019, 1, 1); 
//		dao.createTask("Some description", dueDate , TaskStatus.COMPLETED, "6498");
//		// search for Task by task ID
//		Task t = dao.getTask("6498");
//		System.out.println("Task desription: " + t.getDescription() + " Task Status: " + t.getStatus() + "  Task ID; " + t.getTaskID());
//		System.out.println("Task Date: " + t.getDueDate().getDate() + " , " + t.getDueDate().getMonth() + " , " + t.getDueDate().getYear() +"\n");
//	}
//	
	@Test
	public void testCreateDocument() {
		// create a document
		TASK.setTaskID("1");
		TASK.setDueDate(DATE);
		TASK.setDescription("we");
		TASK.setTaskStatus(STATUS);
		dao.createDocument(DOCUMENT_NAME, DOCUMENT_URL, TASK);
		// search for doc
		Document doc = dao.getDocument(DOCUMENT_URL);
		
		String name = doc.getName();
		assertEquals(DOCUMENT_NAME, name);
		String url = doc.getUrl();
		assertEquals(DOCUMENT_URL, url);
	}
	

	
}
