package ca.mcgill.ecse321.cooperator.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository; 
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository; 
import ca.mcgill.ecse321.cooperator.dao.StudentRepository; 
import ca.mcgill.ecse321.cooperator.dao.TaskRepository; 

import ca.mcgill.ecse321.cooperator.service.CooperatorService;
import org.mockito.invocation.InvocationOnMock;
import ca.mcgill.ecse321.cooperator.controller.CooperatorRestController;

import ca.mcgill.ecse321.cooperator.model.Student;

import static org.junit.Assert.assertEquals;	
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorApplicatonTests {
	
	@Mock
	private CoopCourseOfferingRepository coopCourseOfferingDao;
	@Mock
	private CoopCourseRepository coopCourseDao;
	@Mock
	private DocumentRepository documentDao;
	@Mock
	private EmployerRepository employerDao;
	@Mock
	private StudentEnrollmentRepository  studentEnrollmentDao;
	@Mock
	private StudentRepository studentDao;
	@Mock
	private TaskRepository taskDao;
	
	@InjectMocks
	private CooperatorService service;

	@Mock
	private CooperatorService serviceMock;

	@InjectMocks
	private CooperatorRestController controller;
	
//    @Test
//    public void contextLoads() {
//    	
//    }


}
