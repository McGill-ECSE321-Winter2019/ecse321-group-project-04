package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.After;
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
	
	@After
	public void cleanDataBase() {
		documentRepository.deleteAll();
		taskRepository.deleteAll();
		studentEnrollmentRepository.deleteAll();
		studentRepository.deleteAll();
		employerRepository.deleteAll();
		coopCourseOfferingRepository.deleteAll();
		coopCourseRepository.deleteAll();
	}

	@Test
	public void testCreateStudent() {
		service.createStudent("first_name", "last_name", 260112233, "student@email.com");

		Student s = service.getStudent(260112233);

		assertEquals("first_name", s.getFirstName());
		assertEquals("last_name", s.getLastName());
		assertEquals((Integer) 260112233, s.getMcgillID());
		assertEquals("student@email.com", s.getMcgillEmail());
	}
	
    @Test
    public void testCreateEmployer() {
        service.createEmployer("Google", "google@gmail.com");

        Employer e = service.getEmployer("google@gmail.com");

        assertEquals("Google", e.getName());
        assertEquals("google@gmail.com", e.getEmail());
    }
    
    @Test
    public void testCreateCoopCourse() {
        service.createCoopCourse("ECSE300", 1);
        CoopCourse c = coopCourseRepository.findCoopCourseByCourseCode("ECSE300");

        assertEquals("ECSE300", c.getCourseCode());
        assertEquals((Integer)1, c.getCoopTerm());
    }
    
    @Test
    public void testCreateCoopCourseOffering() {
        CoopCourse c = service.createCoopCourse("ECSE301", 1);

        service.createCoopCourseOffering(2018, Term.WINTER, true, c);

        CoopCourseOffering cco = coopCourseOfferingRepository.findCoopCourseOfferingByOfferID("ECSE301-W18");

        assertEquals((Integer)2018, cco.getYear());
        assertEquals(Term.WINTER, cco.getTerm());
        assertEquals(true, cco.getActive());
        assertEquals("ECSE301-W18", cco.getOfferID());
    }
    @Test
    public void testCreateStudentEnrollment() {
        CoopCourse c = service.createCoopCourse("ECSE302", 1);
        CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
        Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
        Employer e = service.createEmployer("Facebook", "fb@email.com");

        service.createStudentEnrollment(true, CourseStatus.PASSED,s, e, cco);

        StudentEnrollment se = studentEnrollmentRepository.findStudentEnrollmentByEnrollmentID("260654321-ECSE302-F19");

        assertEquals(true, se.getActive());
        assertEquals("260654321-ECSE302-F19", se.getEnrollmentID());
        assertEquals(CourseStatus.PASSED, se.getStatus());

    }
    
    @Test
    public void testCreateTask() {
        @SuppressWarnings("deprecation")
		Date dueDate =new Date(2019, 1, 1); 
        CoopCourse c = service.createCoopCourse("ECSE302", 1);
        CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
        Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
        Employer e = service.createEmployer("Facebook", "fb@email.com");
        StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, e, cco);

        service.createTask("Some description", dueDate , TaskStatus.COMPLETED, "1234", se);

        Task t = taskRepository.findTaskByTaskID("1234");

        assertEquals("Some description", t.getDescription());
        assertEquals(dueDate, t.getDueDate());
        assertEquals(TaskStatus.COMPLETED, t.getTaskStatus());
        assertEquals("1234", t.getTaskID());
    }
    
    @Test
    public void testCreateDocument() {
        @SuppressWarnings("deprecation")
		Date dueDate =new Date(2019, 1, 1); 
        CoopCourse c = service.createCoopCourse("ECSE303", 1);
        CoopCourseOffering cco = service.createCoopCourseOffering(2017, Term.FALL, true, c);
        Student s = service.createStudent("f_name", "l_name", 260654322, "test@mail.com");
        Employer e = service.createEmployer("Facebook", "fb@email.ca");
        StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, e, cco);
        Task t = service.createTask("Some description", dueDate , TaskStatus.COMPLETED, "1235", se);

        service.createDocument("doc name", "http://test-url.this/is/just/for/testing", t);

        Document d = documentRepository.findDocumentByUrl("http://test-url.this/is/just/for/testing");

        assertEquals("doc name", d.getName());
        assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());
    }
	
}
