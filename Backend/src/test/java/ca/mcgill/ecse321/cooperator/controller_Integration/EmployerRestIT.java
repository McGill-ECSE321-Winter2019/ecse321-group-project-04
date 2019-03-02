package ca.mcgill.ecse321.cooperator.controller_Integration;


import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import ca.mcgill.ecse321.cooperator.CooperatorApplication;

import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;

import ca.mcgill.ecse321.cooperator.model.Employer;



@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CooperatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    
public class EmployerRestIT {
	
	 @LocalServerPort
	    private int port;
	    
	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    private HttpHeaders headers = new HttpHeaders();
	   
	    
	    @Autowired
	    private EmployerRepository emoloyerRepository;
	    
	    
	    @Before
	    @After
	    public void cleanDataBase() {
		    emoloyerRepository.deleteAll();
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
		  
		    assertTrue(result.contains("/employers/tom@email.com"));
	    }
	    
	    private String createURLWithPort(String uri) {
	    	return "http://localhost:" + port + uri;
	    }

}
