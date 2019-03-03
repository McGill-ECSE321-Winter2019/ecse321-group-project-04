package ca.mcgill.ecse321.cooperator.controller_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetEndPointRestIT {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployerRepository emoloyerRepository;

	@Autowired
	private CoopCourseRepository coopCourseRepository;

	@Autowired
	private CoopCourseOfferingRepository coopCourseOfferingRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private StudentEnrollmentRepository studentEnrollmentRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void getStudents() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students"), HttpMethod.GET, null,
				String.class);
		String result = response.getBody().toString();
		assertTrue(result.contains("/students/260893874"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getEmployers() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/employers"), HttpMethod.GET, null,
				String.class);
		String result = response.getBody().toString();

		assertTrue(result.contains("/employers/tom@email.com"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getCourses() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourses"), HttpMethod.GET, null,
				String.class);
		String result = response.getBody().toString();

		assertTrue(result.contains("\"coopTerm\" : 2"));

		assertTrue(result.contains("/coopCourses/EBUC1000"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getCourseOferings() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/coopCourseOfferings"),
				HttpMethod.GET, null, String.class);
		String result = response.getBody().toString();
		assertTrue(result.contains(" \"term\" : \"SUMMER\","));
		assertTrue(result.contains(" \"year\" : 2019,"));
		assertTrue(result.contains("\"active\" : true,"));
		assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getEnrollments() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/studentEnrollments"),
				HttpMethod.GET, null, String.class);
		String result = response.getBody().toString();

		assertTrue(result.contains(" \"status\" : \"ONGOING\""));
		assertTrue(result.contains("  \"active\" : true,"));
		assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getTasks() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/tasks"), HttpMethod.GET, null,
				String.class);
		String result = response.getBody().toString();

		assertTrue(result.contains("Submit the CO-OP position acceptance form"));
		assertTrue(result.contains("Submit the employer contract document"));
		assertTrue(result.contains("Submit an initial report of the tasks and workload of the internship"));
		assertTrue(result.contains("Submit the term technical report about the internship experience"));
		assertTrue(result.contains("Submit the final evaluation report for the internship experience"));
		assertTrue(result.contains("some description"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getDocuments() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/documents"), HttpMethod.GET, null,
				String.class);
		String result = response.getBody().toString();
		assertTrue(result.contains("CO-OP Position Acceptance Form"));
		assertTrue(result.contains("Employer Contract"));
		assertTrue(result.contains("DocName"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getStudentsCourseEnrollments() {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students/260893874/courseEnrollments"), HttpMethod.GET, null,
				String.class);
		String result = response.getBody().toString();
		assertTrue(result.contains(" \"status\" : \"ONGOING\""));
		assertTrue(result.contains("  \"active\" : true,"));
		assertTrue(result.contains("studentEnrollments/260893874-EBUC1000-S19"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getCourseOfferingsForCourse() {
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/coopCourses/EBUC1000/coopCourseOffering"), HttpMethod.GET, null, String.class);
		String result = response.getBody().toString();
		assertTrue(result.contains(" \"term\" : \"SUMMER\","));
		assertTrue(result.contains(" \"year\" : 2019,"));
		assertTrue(result.contains("\"active\" : true,"));
		
		assertTrue(result.contains("/coopCourseOfferings/EBUC1000-S19"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void getCourseEnrolledStudentForEmployer() {
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/employers/tom@email.com/studentEnrollments"), HttpMethod.GET, null, String.class);
		String result = response.getBody().toString();

		assertTrue(result.contains(" \"status\" : \"ONGOING\""));
		assertTrue(result.contains("  \"active\" : true,"));
		assertTrue(result.contains("/studentEnrollments/260893874-EBUC1000-S19"));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	

	@Before
	public void prepareTest() {

		CoopCourse course = new CoopCourse();

		course.setCourseCode("EBUC1000");
		course.setCoopTerm(2);

		HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);

		restTemplate.exchange(createURLWithPort("/coopCourse"), HttpMethod.POST, entity, String.class);

		Student student = new Student();

		student.setFirstName("uvw");
		student.setLastName("xyz");
		student.setMcgillID(260893874);
		student.setMcgillEmail("uvw.xyz@email.com");

		HttpEntity<Student> entity2 = new HttpEntity<Student>(student, headers);

		restTemplate.exchange(createURLWithPort("/student"), HttpMethod.POST, entity2, String.class);

		Employer employer = new Employer();

		employer.setName("Tom");
		employer.setEmail("tom@email.com");

		HttpEntity<Employer> entity3 = new HttpEntity<Employer>(employer, headers);

		restTemplate.exchange(createURLWithPort("/employer"), HttpMethod.POST, entity3, String.class);

		CoopCourseOffering courseOffering = new CoopCourseOffering();

		courseOffering.setYear(2019);
		courseOffering.setTerm(Term.SUMMER);
		courseOffering.setActive(true);

		HttpEntity<CoopCourseOffering> entity4 = new HttpEntity<CoopCourseOffering>(courseOffering, headers);

		restTemplate.exchange(createURLWithPort("coopCourseOffering?courseCode=EBUC1000"), HttpMethod.POST, entity4,
				String.class);

		StudentEnrollment sudentEnrollment = new StudentEnrollment();

		sudentEnrollment.setActive(true);
		sudentEnrollment.setStatus(CourseStatus.ONGOING);

		HttpEntity<StudentEnrollment> entity5 = new HttpEntity<StudentEnrollment>(sudentEnrollment, headers);

		restTemplate
				.exchange(
						createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
								+ "&studentID=260893874&employerEmail=tom@email.com"
								+ "&coopAcceptanceForm=url1&employerContract=url2"),
						HttpMethod.POST, entity5, String.class);

		Task task = new Task();

		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		task.setName("someTask");
		task.setDescription("some description");
		task.setDueDate(dueDate);
		task.setTaskStatus(TaskStatus.COMPLETED);

		HttpEntity<Task> entity6 = new HttpEntity<Task>(task, headers);

		restTemplate.exchange(createURLWithPort("/task?studentEnrollmentID=260893874-EBUC1000-S19"), HttpMethod.POST,
				entity6, String.class);

		Document document = new Document();

		document.setName("DocName");
		document.setUrl("http://test-url.this/is/just/for/testing");

		HttpEntity<Document> entity7 = new HttpEntity<Document>(document, headers);

		restTemplate.exchange(
				createURLWithPort("document?studentEnrollmentID=260893874-EBUC1000-S19" + "&taskName=someTask"),
				HttpMethod.POST, entity7, String.class);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Before
	@After
	public void cleanDataBase() {
		studentEnrollmentRepository.deleteAll();
		studentRepository.deleteAll();
		emoloyerRepository.deleteAll();
		coopCourseOfferingRepository.deleteAll();
		coopCourseRepository.deleteAll();
		documentRepository.deleteAll();
		taskRepository.deleteAll();
	}

}
