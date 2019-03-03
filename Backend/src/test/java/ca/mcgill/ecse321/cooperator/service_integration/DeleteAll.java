//package ca.mcgill.ecse321.cooperator.service_integration;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
//import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
//import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
//import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
//import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
//import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
//import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DeleteAll {
//
//	@Autowired
//	private StudentRepository studentRepository;
//	@Autowired
//	private EmployerRepository employerRepository;
//	@Autowired
//	private CoopCourseRepository coopCourseRepository;
//	@Autowired
//	private CoopCourseOfferingRepository coopCourseOfferingRepository;
//	@Autowired
//	private StudentEnrollmentRepository studentEnrollmentRepository;
//	@Autowired
//	private TaskRepository taskRepository;
//	@Autowired
//	private DocumentRepository documentRepository;
//
//
//	@Test
//	public void testCreateTask() {
//		studentEnrollmentRepository.deleteAll();
//		studentRepository.deleteAll();
//		employerRepository.deleteAll();
//		coopCourseOfferingRepository.deleteAll();
//		coopCourseRepository.deleteAll();
//	    taskRepository.deleteAll();
//	    documentRepository.deleteAll();
//	}
//}
