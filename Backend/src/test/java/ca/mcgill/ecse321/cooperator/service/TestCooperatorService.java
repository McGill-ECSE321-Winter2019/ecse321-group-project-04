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
		try {
			service.createStudent("first_name", "last_name", 260112233, "student@email.com");
		} catch (IllegalArgumentException e) {
			fail();
		}
		Student s = service.getStudent(260112233);

		assertEquals("first_name", s.getFirstName());
		assertEquals("last_name", s.getLastName());
		assertEquals((Integer) 260112233, s.getMcgillID());
		assertEquals("student@email.com", s.getMcgillEmail());

		assertEquals(1, service.getAllStudents().size());
		assertEquals((Integer) 260112233, service.getAllStudents().get(0).getMcgillID()); // probably not necessary
	}

	@Test
	public void testCreateNullNameStudent() {
		String error = null;
		try {
			service.createStudent(null, "last_name", 260112233, "student@email.com");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Your student details are incomplete!", error);

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

		assertEquals("Your student details are incomplete!", error);

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

		assertEquals("Your student details are incomplete!", error);

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

		assertEquals("Your employer details are incomplete!", error);

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
		CoopCourse c = coopCourseRepository.findCoopCourseByCourseCode("ECSE300");

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
		assertEquals("Your course details are incomplete!", error);

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

		CoopCourseOffering cco = coopCourseOfferingRepository.findCoopCourseOfferingByOfferID("ECSE301-W18");

		assertEquals((Integer) 2018, cco.getYear());
		assertEquals(Term.WINTER, cco.getTerm());
		assertEquals(true, cco.getActive());
		assertEquals("ECSE301-W18", cco.getOfferID());

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
		assertEquals("Your course offering details are incomplete!", error);

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
		StudentEnrollment se = studentEnrollmentRepository.findStudentEnrollmentByEnrollmentID("260654321-ECSE302-F19");

		assertEquals(true, se.getActive());
		assertEquals("260654321-ECSE302-F19", se.getEnrollmentID());
		assertEquals(CourseStatus.PASSED, se.getStatus());

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

		assertEquals("Your student enrollment details are incomplete!", error);

		assertEquals(0, service.getAllStudentEnrollments().size());

	}

	/*--- TASK TESTS ---*/
	@Test
	public void testCreateTask() {
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");
		StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);

		try {
			service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1234", se);
		} catch (IllegalArgumentException e) {
			fail();
		}
		Task t = taskRepository.findTaskByTaskID("1234");

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
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");
		StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);

		try {
			service.createTask(null, dueDate, TaskStatus.COMPLETED, "1234", se);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Your task details are incomplete!", error);

		assertEquals(0, service.getAllTasks().size());

	}

	/*--- DOCUMENT TESTS ---*/
	@Test
	public void testCreateDocument() {
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);
		CoopCourse c = service.createCoopCourse("ECSE303", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2017, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654322, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.ca");
		StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);
		Task t = service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1235", se);
		try {
			service.createDocument("doc name", "http://test-url.this/is/just/for/testing", t);
		} catch (IllegalArgumentException e) {
			fail();
		}
		Document d = documentRepository.findDocumentByUrl("http://test-url.this/is/just/for/testing");

		assertEquals("doc name", d.getName());
		assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());

		assertEquals(1, service.getAllDocuments().size());
	}

	@Test
	public void testCreateNullNameDocument() {
		String error = null;
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);
		CoopCourse c = service.createCoopCourse("ECSE303", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2017, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654322, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.ca");
		StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);
		Task t = service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1235", se);
		try {
			service.createDocument(null, "http://test-url.this/is/just/for/testing", t);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Your document details are incomplete!", error);

		assertEquals(0, service.getAllDocuments().size());

	}

}
