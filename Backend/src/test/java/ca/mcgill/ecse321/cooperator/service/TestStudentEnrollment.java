package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Term;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudentEnrollment {
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

	@After
	public void cleanDataBase() {
		studentEnrollmentRepository.deleteAll();
		studentRepository.deleteAll();
		employerRepository.deleteAll();
		coopCourseOfferingRepository.deleteAll();
		coopCourseRepository.deleteAll();
	}
	
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
		/*This should probably be moved to a different test*/
		assertEquals(se.getEnrollmentID(), service.getEmployersStudentEnrollments(emp).get(0).getEnrollmentID());

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
}
