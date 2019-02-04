package ca.mcgill.ecse321.cooperator;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

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
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Ecse321GroupProject04ApplicationTests {
	
	
	private static final String EMPLOYER_NAME = "Google";
	private static final String EMPLOYER_EMAIL = "google@gmail.com";
	
	private static final String COURSE_CODE = "ECSE3XX";
	private static final Integer COURSE_TERM = 2;
	private static final String COURSE_ID = "ECSE3XX-W19";
	
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
	
	/*The rest of the read/write tests need to be changed --classes with composition relations leave a null row in the table */
	
//	@Test
//	public void testCreateCoopCourseOffering() {
//		// create Co-op course offering
//		dao.createCoopCourseOffering(1999, Term.FALL, true , "123");
//		// search for co-op course offering by ID
//		CoopCourseOffering cco = dao.getCoopCourseOffering("123");
//		System.out.println("cco" + cco.getYear() +"\n");
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
//	@Test
//	public void testCreateDocument() {
//		// create a document
//		CoopCourseOffering cco1 = new CoopCourseOffering();
//		cco1.setOfferID("2");
//		dao.createDocument("Some report", "www.onedrive.something",cco1, t1);
//		// search for doc
//		Document doc = dao.getDocument("www.onedrive.something");
//		System.out.println("Document name: " + doc.getName() +"\n");
//	}
//	
	//for some reason, dao.getStudent() returns null
//	@Test
//	public void testCreateStudent() {
//		// create a student
//		Integer id=12345;
//		dao.createStudent("Remi", "Carriere", id, "remi.carriere@mail.mcgill.ca");
//		// search for student by id 
//		Student s = dao.getStudent(id);
//		System.out.println("Student Name: " + s.getFirstName() + " " + s.getLastName());
//		System.out.println("Student Email: " + s.getMcgillEmail() + "ID: " + s.getMcgillID()  +"\n");
//	}
	
}
