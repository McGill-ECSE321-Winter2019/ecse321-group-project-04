package ca.mcgill.ecse321.cooperator.controller_Integration;


import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
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
    
    @Before
    @After
    public void cleanDataBase() {
    studentRepository.deleteAll();
    emoloyerRepository.deleteAll();
    coopCourseOfferingRepository.deleteAll();
    coopCourseRepository.deleteAll();
    documentRepository.deleteAll();
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
	    
	    assertThat(response.getBody().equals("/student"));
	    
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
	    
	    assertThat(response.getBody().equals("/employer"));
    }
    
    
    @Test
    public void createCourse() throws Exception {
    
	    CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC 1000");
	    course.setCoopTerm(2);
	    
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class);
	    
    	assertThat(response.getBody().equals("/coopCourse"));
    }
    
    @Test
    public void createCourseOffering() throws Exception {
    
	    CoopCourseOffering courseOffering = new CoopCourseOffering();
	    
        CoopCourse course = new CoopCourse();
	    
	    course.setCourseCode("EBUC 1000");
	    course.setCoopTerm(2);
	    
	    
	    HttpEntity<CoopCourse> entity = new HttpEntity<CoopCourse>(course, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(
	    				createURLWithPort("/coopCourse"),
	    				HttpMethod.POST, entity, String.class); 
	    
	    courseOffering.setYear(2019);
	    courseOffering.setTerm(Term.SUMMER);
	    courseOffering.setActive(true);
	    courseOffering.setCoopCourse(course);
	    
	    
	    HttpEntity<CoopCourseOffering> entity2 = new HttpEntity<CoopCourseOffering>(courseOffering, headers);
	    
	    ResponseEntity<String> response2 = restTemplate.exchange(
	    				createURLWithPort("coopCourseOffering?courseCode=EBUC 1000"),
	    				HttpMethod.POST, entity2, String.class);
	    
	    assertThat(response2.getBody().equals("/coopCourseOfferings/EBUC 1000-S19"));
	    
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

