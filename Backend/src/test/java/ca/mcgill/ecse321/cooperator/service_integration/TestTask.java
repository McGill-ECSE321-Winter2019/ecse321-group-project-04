package ca.mcgill.ecse321.cooperator.service_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.Before;
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
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTask {
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
	public void testCreateTask() {
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);
                long taskID = -1;

                // Create chain of objects required to create a task
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");
                StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco, "test-url-1", "test-url-2");

		try {
			Task t = service.createTask("Task name", "Some description", dueDate, TaskStatus.COMPLETED, se);
                        taskID = t.getTaskID();
		} catch (InvalidParameterException e) {
			fail();
		}
		Task t = service.getTask(taskID);
		// check attributes
		assertEquals("Task name", t.getName());
		assertEquals("Some description", t.getDescription());
		assertEquals(dueDate, t.getDueDate());
		assertEquals(TaskStatus.COMPLETED, t.getTaskStatus());
		assertEquals(taskID, t.getTaskID());

		assertEquals(6, service.getAllTasks().size());
	}
	
	@Test
	public void testCreateTaskWithObject() {
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);
        String taskName = null;

        // Create chain of objects required to create a task
		CoopCourse c = service.createCoopCourse("ECSE302", 1);
		CoopCourseOffering cco = service.createCoopCourseOffering(2019, Term.FALL, true, c);
		Student s = service.createStudent("f_name", "l_name", 260654321, "test@mail.com");
		Employer emp = service.createEmployer("Facebook", "fb@email.com");
        StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco, "test-url-1", "test-url-2");

		Task param = new Task();
		param.setName("Task name");
		param.setDescription("Some description");
		param.setDueDate(dueDate);
		param.setTaskStatus(TaskStatus.COMPLETED);
        
		try {
			Task t = service.createTask(param, se);
            taskName = t.getName();
		} catch (InvalidParameterException e) {
			fail();
		}
		Task t = service.getStudentEnrollment(se.getEnrollmentID()).getTask(taskName);
		//Task t = service.getTask(taskID);
		// check attributes
		assertEquals("Task name", t.getName());
		assertEquals("Some description", t.getDescription());
		assertEquals(dueDate, t.getDueDate());
		assertEquals(TaskStatus.COMPLETED, t.getTaskStatus());
		//assertEquals(taskID, t.getTaskID());

		assertEquals(6, service.getAllTasks().size());
	}
	
	@Test
	public void testCreateNullNameTask() {
		String error = null;
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		try {
			service.createTask(null, "Some description", dueDate, TaskStatus.COMPLETED, null);
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
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		try {
			service.createTask("Task name", null, dueDate, TaskStatus.COMPLETED, null);
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
		} catch (EntityNotFoundException e){
			error = e.getMessage();
		}
		
		assertEquals(error, "Could not find a Task with ID 1234567");
	}
}
