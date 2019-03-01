package ca.mcgill.ecse321.cooperator.controller_Integration;


import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.mcgill.ecse321.cooperator.CooperatorApplication;

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
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CooperatorRestControllerIT {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate; //= new TestRestTemplate();
    
    private HttpHeaders headers = new HttpHeaders();
    
    @Autowired
    private CooperatorService service;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private EmployerRepository emoloyerRepository;
    
    @Autowired
    private CoopCourseRepository coopCourseRepository;
    
    @Autowired
    private CoopCourseOfferingRepository  coopCourseOfferingRepository;
    
    @Autowired
    private DocumentRepository  documentRepository;
    
    @Autowired
	private StudentEnrollmentRepository studentEnrollmentRepository;
    
    @Autowired
   	private TaskRepository taskRepository;
    
    
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
    
    //@SuppressWarnings("unlikely-arg-type")
    @Test
    public void createStudent() throws Exception {
    
	    Student student = new Student();
	    
	    student.setFirstName("uvw");
	    student.setLastName("xyz");
	    student.setMcgillID(260893874);
	    student.setMcgillEmail("uvw.xyz@email.com");
	    
	    //String inputInJason = this.mapToJson(student);
	    
	    
	    HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/student"),
	    				HttpMethod.POST, entity, String.class);
	    String result = response.getBody().toString(); 
	    assertThat(result.subSequence(23, result.length()-1)).isEqualTo("/students/260893874");
	    
	    //assertThat(""); 
	    
	    //System.out.println(result.subSequence(23, result.length()-1)); 
	    
	    //assertThat(response.equals("http://localhost:8080/student/id"));
	    
	    /*String responseInJason = response.getBody();
	     
	     assertThat(responseInJason).isEqualTo(inputInJason);*/
	    
	    /*String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
	     assertTrue(actual.contains("/student/"));*/
    }
    
    
    @Test
    public void createEmployer() throws Exception {
    
	    Employer employer = new Employer();
	    
	    employer.setName("Tom");
	    employer.setEmail("tom@email.com");
	    
	    HttpEntity<Employer> entity = new HttpEntity<Employer>(employer, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/employer"),
	    				HttpMethod.POST, entity, String.class);
	    
	    String result = response.getBody().toString(); 
	   // System.out.println(result.subSequence(23, result.length()-1));
	    assertThat(result.subSequence(23, result.length()-1)).isEqualTo("/employers/tom@email.com");
	    
	    //assertThat(response.getBody().equals("/employers"));
    }
    
    
    @Test
    public void createCourse() throws Exception {
    
	    CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC1000");
	    course.setCoopTerm(2);
	    
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class);
	    
	    String result = response.getBody().toString();
    	assertThat(result.subSequence(23, result.length()-1)).isEqualTo("/coopCourses/EBUC1000");
    }
    
    @Test
    public void createCourseOffering() throws Exception {
    
	    CoopCourseOffering courseOffering = new CoopCourseOffering();
	    
        CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC1000");
	    course.setCoopTerm(2);
	    
	    courseOffering.setYear(2019);
	    courseOffering.setTerm(Term.SUMMER);
	    courseOffering.setActive(true);
	    courseOffering.setCoopCourse(course);
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class); 
	    
	    HttpEntity<CoopCourseOffering> entity2 = new HttpEntity<CoopCourseOffering>(courseOffering, headers);
	    
	    ResponseEntity<String> response2 = restTemplate.exchange(
	    				createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
	    				HttpMethod.POST, entity2, String.class);
	    
	    String result2 = response2.getBody().toString();
	    assertThat(result2.subSequence(23, result2.length()-1)).isEqualTo("/coopCourseOfferings/EBUC1000-S19");
    }
    
    @Test
    public void createStudentEnrollment() throws Exception {
    
    	StudentEnrollment sudentEnrollment = new StudentEnrollment();
    	
    	CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC1000");
	    course.setCoopTerm(2);
	    
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class);
    	
	    Student student = new Student();
	    
	    student.setFirstName("uvw");
	    student.setLastName("xyz");
	    student.setMcgillID(260893874);
	    student.setMcgillEmail("uvw.xyz@email.com");
	    
	    
	    HttpEntity<Student> entity2 = new HttpEntity<Student>(student, headers);
	    
	    ResponseEntity<String> response2 = restTemplate.exchange(
	    				createURLWithPort("/student"),
	    				HttpMethod.POST, entity2, String.class);
	    
	    Employer employer = new Employer();
	    
	    employer.setName("Tom");
	    employer.setEmail("tom@email.com");
	    
	    HttpEntity<Employer> entity3 = new HttpEntity<Employer>(employer, headers);
	    
	    ResponseEntity<String> response3 = restTemplate.exchange(
	    				createURLWithPort("/employer"),
	    				HttpMethod.POST, entity3, String.class);
	    
	    CoopCourseOffering courseOffering = new CoopCourseOffering();
	    
	    
	    courseOffering.setYear(2019);
	    courseOffering.setTerm(Term.SUMMER);
	    courseOffering.setActive(true);
	    courseOffering.setCoopCourse(course);
	    //courseOffering.setOfferID("ECSE-321-W19");
	    
	    
	    HttpEntity<CoopCourseOffering> entity4 = new HttpEntity<CoopCourseOffering>(courseOffering, headers);
	    
	    ResponseEntity<String> response4 = restTemplate.exchange(
	    				createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
	    				HttpMethod.POST, entity4, String.class);
		
	    
	    sudentEnrollment.setActive(true);
	    sudentEnrollment.setStatus(CourseStatus.ONGOING);
	    
	    
	    HttpEntity<StudentEnrollment> entity5 = new HttpEntity<StudentEnrollment>(sudentEnrollment, headers);
	    
	    ResponseEntity<String> response5 = restTemplate.exchange(
	    				createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
	    						+ "&studentID=260893874&employerEmail=tom@email.com"
	    						+ "&coopAcceptanceForm=url1&employerContract=url2"),
	    				
	    				HttpMethod.POST, entity5, String.class);
	    
	    String result = response5.getBody().toString(); 
	   // System.out.println(result.subSequence(23, result.length()-1));
	    assertThat(response5.getBody().equals("/studentEnrollments/260893874-EEBUC1000-S19"));
	    
    }
    
    
    @Test
    public void createTasK() throws Exception {
    
    	Task task = new Task(); 
    	
    	CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC1000");
	    course.setCoopTerm(2);
	    
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class);
    	
	    Student student = new Student();
	    
	    student.setFirstName("uvw");
	    student.setLastName("xyz");
	    student.setMcgillID(260893874);
	    student.setMcgillEmail("uvw.xyz@email.com");
	    
	    
	    HttpEntity<Student> entity2 = new HttpEntity<Student>(student, headers);
	    
	    ResponseEntity<String> response2 = restTemplate.exchange(
	    				createURLWithPort("/student"),
	    				HttpMethod.POST, entity2, String.class);
	    
	    Employer employer = new Employer();
	    
	    employer.setName("Tom");
	    employer.setEmail("tom@email.com");
	    
	    HttpEntity<Employer> entity3 = new HttpEntity<Employer>(employer, headers);
	    
	    ResponseEntity<String> response3 = restTemplate.exchange(
	    				createURLWithPort("/employer"),
	    				HttpMethod.POST, entity3, String.class);
	    
	    CoopCourseOffering courseOffering = new CoopCourseOffering();
	    
	    
	    courseOffering.setYear(2019);
	    courseOffering.setTerm(Term.SUMMER);
	    courseOffering.setActive(true);
	    courseOffering.setCoopCourse(course);
	    //courseOffering.setOfferID("ECSE-321-W19");
	    
	    
	    HttpEntity<CoopCourseOffering> entity4 = new HttpEntity<CoopCourseOffering>(courseOffering, headers);
	    
	    ResponseEntity<String> response4 = restTemplate.exchange(
	    				createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
	    				HttpMethod.POST, entity4, String.class);
		
	    
	    StudentEnrollment sudentEnrollment = new StudentEnrollment();
	    
	    sudentEnrollment.setActive(true);
	    sudentEnrollment.setStatus(CourseStatus.ONGOING);
	    
	    
	    HttpEntity<StudentEnrollment> entity5 = new HttpEntity<StudentEnrollment>(sudentEnrollment, headers);
	    
	    ResponseEntity<String> response5 = restTemplate.exchange(
	    				createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
	    						+ "&studentID=260893874&employerEmail=tom@email.com"
	    						+ "&coopAcceptanceForm=url1&employerContract=url2"),
	    				HttpMethod.POST, entity5, String.class);
	    
	    Date dueDate = new Date(2019, 1, 1);
	   
	    task.setName("someTask");
	    task.setDescription("some description");
	    task.setDueDate(dueDate);
	    task.setTaskStatus(TaskStatus.COMPLETED);
	    
	    HttpEntity<Task> entity6 = new HttpEntity<Task>(task, headers);
	    
	    ResponseEntity<String> response6 = restTemplate.exchange(
	    				createURLWithPort("/task?studentEnrollmentID=260893874-EBUC1000-S19"),
	    				HttpMethod.POST, entity6, String.class);
	    
	    String result = response6.getBody().toString(); 
	    System.out.println(result.subSequence(23, result.length()-1));
	  //forget assertThat for now
	    
    }
    
    @Test
    public void createDocument() throws Exception {
    
    	Document document = new Document(); 
    	
    	CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC1000");
	    course.setCoopTerm(2);
	    
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class);
    	
	    Student student = new Student();
	    
	    student.setFirstName("uvw");
	    student.setLastName("xyz");
	    student.setMcgillID(260893874);
	    student.setMcgillEmail("uvw.xyz@email.com");
	    
	    
	    HttpEntity<Student> entity2 = new HttpEntity<Student>(student, headers);
	    
	    ResponseEntity<String> response2 = restTemplate.exchange(
	    				createURLWithPort("/student"),
	    				HttpMethod.POST, entity2, String.class);
	    
	    Employer employer = new Employer();
	    
	    employer.setName("Tom");
	    employer.setEmail("tom@email.com");
	    
	    HttpEntity<Employer> entity3 = new HttpEntity<Employer>(employer, headers);
	    
	    ResponseEntity<String> response3 = restTemplate.exchange(
	    				createURLWithPort("/employer"),
	    				HttpMethod.POST, entity3, String.class);
	    
	    CoopCourseOffering courseOffering = new CoopCourseOffering();
	    
	    
	    courseOffering.setYear(2019);
	    courseOffering.setTerm(Term.SUMMER);
	    courseOffering.setActive(true);
	    courseOffering.setCoopCourse(course);
	    
	    
	    HttpEntity<CoopCourseOffering> entity4 = new HttpEntity<CoopCourseOffering>(courseOffering, headers);
	    
	    ResponseEntity<String> response4 = restTemplate.exchange(
	    				createURLWithPort("coopCourseOffering?courseCode=EBUC1000"),
	    				HttpMethod.POST, entity4, String.class);
		
	    
	    StudentEnrollment sudentEnrollment = new StudentEnrollment();
	    
	    sudentEnrollment.setActive(true);
	    sudentEnrollment.setStatus(CourseStatus.ONGOING);
	    
	    
	    HttpEntity<StudentEnrollment> entity5 = new HttpEntity<StudentEnrollment>(sudentEnrollment, headers);
	    
	    ResponseEntity<String> response5 = restTemplate.exchange(
	    				createURLWithPort("/studentEnrollment?courseOfferingID=EBUC1000-S19"
	    						+ "&studentID=260893874&employerEmail=tom@email.com"
	    						+ "&coopAcceptanceForm=url1&employerContract=url2"),
	    				HttpMethod.POST, entity5, String.class);
	    
	    Task task = new Task(); 
	    
	    Date dueDate = new Date(2019, 1, 1);
	   
	    task.setName("someTask");
	    task.setDescription("some description");
	    task.setDueDate(dueDate);
	    task.setTaskStatus(TaskStatus.COMPLETED);
	    
	    HttpEntity<Task> entity6 = new HttpEntity<Task>(task, headers);
	    
	    ResponseEntity<String> response6 = restTemplate.exchange(
	    				createURLWithPort("/task?studentEnrollmentID=260893874-EBUC1000-S19"),
	    				HttpMethod.POST, entity6, String.class);
	    
	    document.setName("DocName");
	    document.setUrl("http://test-url.this/is/just/for/testing");
	    
	    HttpEntity<Document> entity7 = new HttpEntity<Document>(document, headers);
	    
	    ResponseEntity<String> response7 = restTemplate.exchange(
	    				createURLWithPort("document?studentEnrollmentID=260893874-EBUC1000-S19"
	    						+ "&taskName=someTask"),
	    				HttpMethod.POST, entity7, String.class);
	    
	    String result = response7.getBody().toString(); 
	    System.out.println(result.subSequence(23, result.length()-1));
	    
    }
    
    
    
    /******** Utility Method ********/
    
    private String convertToJson(Object object) throws JsonProcessingException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.writeValueAsString(object);
    }
    
    private String createURLWithPort(String uri) {
    	return "http://localhost:" + port + uri;
    }
    
    
}

