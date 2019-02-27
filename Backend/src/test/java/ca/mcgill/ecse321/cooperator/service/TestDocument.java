package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDocument {
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

	@After
	public void cleanDataBase() {
		studentEnrollmentRepository.deleteAll();
		studentRepository.deleteAll();
		employerRepository.deleteAll();
		coopCourseOfferingRepository.deleteAll();
		coopCourseRepository.deleteAll();
                taskRepository.deleteAll();
		documentRepository.deleteAll();
	}

	@Test
	public void testCreateDocument() {
                // Create chain of objects required to create a task
		Date dueDate = new Date(2019, 1, 1);
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");
                StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco, "test-url-1", "test-url-2");
                Task t = service.createTask("Some description", dueDate, TaskStatus.COMPLETED, se);

		try {
			service.createDocument("doc name", "http://test-url.this/is/just/for/testing", t);
		} catch (IllegalArgumentException e) {
			fail();
		}
		Document d = service.getDocument("http://test-url.this/is/just/for/testing");

		assertEquals("doc name", d.getName());
		assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());

		assertEquals(3, service.getAllDocuments().size());
	}

	@Test
	public void testCreateNullNameDocument() {
		String error = null;

		try {
			service.createDocument(null, "http://test-url.this/is/just/for/testing", null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your document details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllDocuments().size());
	}

}
