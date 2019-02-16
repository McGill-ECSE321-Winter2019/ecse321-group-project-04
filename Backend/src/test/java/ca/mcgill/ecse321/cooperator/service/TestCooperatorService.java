package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCooperatorService {

	@Autowired
	private CooperatorService service;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private CoopCourseRepository coopCourseRepository;
	@Autowired
	private CoopCourseOfferingRepository coopCourseOfferingRepository;
	@Autowired
	private StudentEnrollmentRepository studentEnrollmentRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private DocumentRepository documentRepository;

	@Before
	public void cleanDataBase() {
		documentRepository.deleteAll();
		taskRepository.deleteAll();
		studentEnrollmentRepository.deleteAll();
		studentRepository.deleteAll();
		employerRepository.deleteAll();
		coopCourseOfferingRepository.deleteAll();
		coopCourseRepository.deleteAll();
	}

	/*--- STUDENT TESTS ---*/
	@Test
	public void testCreateStudent() {
		// Create and persist a student
		try {
			service.createStudent("first_name", "last_name", 260112233, "student@email.com");
		} catch (IllegalArgumentException e) {
			fail();
		}
		// find the student by id
		Student s = service.getStudent(260112233);
		// Check attributes
		assertEquals("first_name", s.getFirstName());
		assertEquals("last_name", s.getLastName());
		assertEquals((Integer) 260112233, s.getMcgillID());
		assertEquals("student@email.com", s.getMcgillEmail());

		assertEquals(1, service.getAllStudents().size());
	}

	@Test
	public void testCreateNullNameStudent() {
		String error = null;
		try {
			service.createStudent(null, "last_name", 260112233, "student@email.com");
		} catch (IllegalArgumentException e) {
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
			service.createStudent(" ", "last_name", 260112233, "student@email.com");
		} catch (IllegalArgumentException e) {
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
			service.createStudent("first_name", "last_name", null, "student@email.com");
		} catch (IllegalArgumentException e) {
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
			service.createStudent("first_name", "last_name", 260112233, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your student details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllStudents().size());
	}

	/*--- EMPLOYER TESTS ---*/
	@Test
	public void testCreateEmployer() {
		try {
			service.createEmployer("Google", "google@gmail.com");
		} catch (IllegalArgumentException e) {
			fail();
		}

		Employer e = service.getEmployer("google@gmail.com");
		// Check attributes
		assertEquals("Google", e.getName());
		assertEquals("google@gmail.com", e.getEmail());

		assertEquals(1, service.getAllEmployers().size());
	}

	@Test
	public void testCreateNullNameEmployer() {
		String error = null;
		try {
			service.createEmployer(null, "google@gmail.com");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your employer details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllEmployers().size());
	}

	/*--- CO-OP COURSE TESTS ---*/
	@Test
	public void testCreateCoopCourse() {
		try {
			service.createCoopCourse("ECSE300", 1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		CoopCourse c = service.getCoopCourse("ECSE300");
		// Check attributes
		assertEquals("ECSE300", c.getCourseCode());
		assertEquals((Integer) 1, c.getCoopTerm());

		assertEquals(1, service.getAllCoopCourses().size());
	}

	@Test
	public void testCreateNullCodeCoopCourse() {
		String error = null;
		try {
			service.createCoopCourse(null, 1);
		} catch (IllegalArgumentException e) {
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
			service.createCoopCourse("ECSE300", null);
		} catch (IllegalArgumentException e) {
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
			service.createCoopCourse("ECSE300", -5);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourses().size());
	}

	/*--- CO-OP COURSE OFEERING TESTS ---*/
	@Test
	public void testCreateCoopCourseOffering() {
		CoopCourse c = service.createCoopCourse("ECSE301", 1);

		try {
			service.createCoopCourseOffering(2018, Term.WINTER, true, c);
		} catch (IllegalArgumentException e) {
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

		assertEquals(1, service.getAllCoopCourseOfferings().size());
	}

	@Test
	public void testCreateNullYearCoopCourseOffering() {
		CoopCourse c = service.createCoopCourse("ECSE301", 1);
		String error = null;
		try {
			service.createCoopCourseOffering(null, Term.WINTER, true, c);
		} catch (IllegalArgumentException e) {
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
		} catch (IllegalArgumentException e) {
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
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course offering details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourseOfferings().size());
	}

	/*--- STUDENT ENROLLMENT TESTS ---*/
	@Test
	public void testCreateStudentEnrollment() {
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");

		try {
			service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);
		} catch (IllegalArgumentException e) {
			fail();
		}
		StudentEnrollment se = service.getStudentEnrollment("260654321-ECSE302-F19");
		// Check attributes
		assertEquals(true, se.getActive());
		assertEquals("260654321-ECSE302-F19", se.getEnrollmentID());
		assertEquals(CourseStatus.PASSED, se.getStatus());
		// check references
		assertEquals("ECSE302-F19", se.getCoopCourseOffering().getOfferID());
		assertEquals("test@mail.com", se.getEnrolledStudent().getMcgillEmail());
		assertEquals("fb@email.com", se.getStudentEmployer().getEmail());

		assertEquals(1, service.getAllStudentEnrollments().size());

	}

	@Test
	public void testCreateNullStatusStudentEnrollment() {
		String error = null;
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");

		try {
			service.createStudentEnrollment(null, CourseStatus.PASSED, s, emp, cco);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your student enrollment details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllStudentEnrollments().size());

	}

	/*--- TASK TESTS ---*/
	@Test
	public void testCreateTask() {
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		try {
			service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1234");
		} catch (IllegalArgumentException e) {
			fail();
		}
		Task t = service.getTask("1234");
		// check attributes
		assertEquals("Some description", t.getDescription());
		assertEquals(dueDate, t.getDueDate());
		assertEquals(TaskStatus.COMPLETED, t.getTaskStatus());
		assertEquals("1234", t.getTaskID());

		assertEquals(1, service.getAllTasks().size());
	}

	@Test
	public void testCreateNullDescriptionTask() {
		String error = null;
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		try {
			service.createTask(null, dueDate, TaskStatus.COMPLETED, "1234");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your task details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllTasks().size());

	}

	/*--- DOCUMENT TESTS ---*/
	@Test
	public void testCreateDocument() {
		try {
			service.createDocument("doc name", "http://test-url.this/is/just/for/testing");
		} catch (IllegalArgumentException e) {
			fail();
		}
		Document d = service.getDocument("http://test-url.this/is/just/for/testing");
		
		assertEquals("doc name", d.getName());
		assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());

		assertEquals(1, service.getAllDocuments().size());
	}

	@Test
	public void testCreateNullNameDocument() {
		String error = null;

		try {
			service.createDocument(null, "http://test-url.this/is/just/for/testing");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your document details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllDocuments().size());
	}

	/* Really messy, but I think it works? */
//	@Test
//	public void testReplaceDocument() {
//		@SuppressWarnings("deprecation")
//		Date dueDate = new Date(2019, 1, 1);
//		CoopCourse c = service.createCoopCourse("ECSE303", 1);
//		CoopCourseOffering cco = service.createCoopCourseOffering(2017, Term.FALL, true, c);
//		Student s = service.createStudent("f_name", "l_name", 260654322, "test@mail.com");
//		Employer emp = service.createEmployer("Facebook", "fb@email.ca");
//		StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);
//		Task t = service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1235", se);
//		try {
//			service.createDocument("doc name", "http://test-url.this/is/just/for/testing", t);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//		Document oldDoc = service.getDocument("http://test-url.this/is/just/for/testing");
//		// check that old doc was persisted
//		assertEquals("doc name", oldDoc.getName());
//		assertEquals("http://test-url.this/is/just/for/testing", oldDoc.getUrl());
//		assertEquals(t.getTaskID(), oldDoc.getTask().getTaskID());
//
//		// create a new doc
//		Document newDoc = service.createDocument("doc name", "http://replacement/doc", t);
//
//		// delete the old doc and persist the new doc
//		service.replaceTaskDocument(t.getTaskID(), newDoc, oldDoc.getUrl());
//
//		// find the new doc assuming it should be associated with the same task ID
//		t = service.getTask("1235");
//		newDoc = service.getDocument("http://replacement/doc");
//
//		assertEquals(t.getTaskID(), newDoc.getTask().getTaskID()); // old task id same as task id associated with new
//																	// document
//		assertEquals(1, service.getAllDocuments().size()); // there should only be one doc since the other is deleted in
//															// the transaction
//	}

}
