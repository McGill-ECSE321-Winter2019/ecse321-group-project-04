package ca.mcgill.ecse321.cooperator.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;
import ca.mcgill.ecse321.cooperator.dto.EmployerDto;
import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.dto.StudentEnrolmentDto;
import ca.mcgill.ecse321.cooperator.dto.TaskDto;
import ca.mcgill.ecse321.cooperator.dto.CoopCourseDto;
import ca.mcgill.ecse321.cooperator.dto.CoopCourseOfferingDto;
import ca.mcgill.ecse321.cooperator.dto.DocumentDto;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorRestController {

	@Autowired
	private CooperatorService service;

	/******** Student Controller ********/

	@PostMapping(value = { "/students/{id}/{firstName}/{lastName}/{email}" })
	public StudentDto createStudent(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("id") Integer id,
			@PathVariable("email") String email) {
		Student s = service.createStudent(firstName, lastName, id, email);
		return convertToDto(s);
	}

	private StudentDto convertToDto(Student s) {
		StudentDto sDto = new StudentDto(s.getFirstName(), s.getLastName(), s.getMcgillID(), s.getMcgillEmail());
		sDto.setCourseEnrollments(s.getCourseEnrollments());
		return sDto;
	}

	/******** Coop Course Controller ********/

	@PostMapping(value = { "/coopCourses/{courseCode}/{coopTerm}" })
	public CoopCourseDto createCoopCourse(@PathVariable("courseCode") String courseCode,
			@PathVariable("coopTerm") Integer coopTerm) {
		CoopCourse c = service.createCoopCourse(courseCode, coopTerm);
		return convertToDto(c);
	}

	private CoopCourseDto convertToDto(CoopCourse c) {
		CoopCourseDto cDto = new CoopCourseDto(c.getCourseCode(), c.getCoopTerm());
		cDto.setCoopCourseOffering(c.getCoopCourseOffering());
		return cDto;
	}

	/******** Employer Controller ********/

	@PostMapping(value = { "/employers/{email}/{name}" })
	public EmployerDto createEmployer(@PathVariable("name") String name, @PathVariable("email") String email) {
		Employer e = service.createEmployer(name, email);
		return convertToDto(e);
	}

	private EmployerDto convertToDto(Employer e) {
		EmployerDto eDto = new EmployerDto(e.getName(), e.getEmail());
		eDto.setStudentEnrollments(e.getStudentEnrollments());
		return eDto;
	}

	/******** Coop Course Offering Controller ********/

	@PostMapping(value = { "/coopCourseOfferings/{year}/{term}/{active}" })
	public CoopCourseOfferingDto createCoopCourseOffering(@PathVariable("year") Integer year,
			@PathVariable("term") Term term, @PathVariable("active") Boolean active,
			@RequestParam(name = "coopCourse") CoopCourseDto cDto) {
		CoopCourse c = service.getCoopCourse(cDto.getCourseCode());
		CoopCourseOffering cco = service.createCoopCourseOffering(year, term, active, c);
		return convertToDto(cco);
	}

	private CoopCourseOfferingDto convertToDto(CoopCourseOffering cco) {
		CoopCourseOfferingDto ccoDto = new CoopCourseOfferingDto(cco.getYear(), cco.getTerm(), cco.getActive(),
				cco.getCoopCourse());
		ccoDto.setOfferID(cco.getOfferID());
		ccoDto.setStudentEnrollments(cco.getStudentEnrollments());
		return ccoDto;
	}
	
	/******** StudentEnrollment Controller ********/
	
	@PostMapping(value = {"/tasks/{status}/{active}" })
	public StudentEnrolmentDto createStudentEnrollment(@PathVariable("status") CourseStatus status, 
			@PathVariable("active") Boolean active, 
			@RequestParam(name = "studentEmployer") Employer employerDto, 
			@RequestParam(name = "enrolledStudent") Student studentDto, 
			@RequestParam(name = "CoopCourseOffering") CoopCourseOffering ccoDto) {
		Employer e = service.getEmployer(employerDto.getEmail()); 
		Student s = service.getStudent(studentDto.getMcgillID()); 
		CoopCourseOffering cco = service.getCoopCourseOffering(ccoDto.getOfferID()); 
		StudentEnrollment se = service.createStudentEnrollment(active, status, s, e, cco); 
		return convertToDto(se); 
	}
	
	private StudentEnrolmentDto convertToDto(StudentEnrollment se) { 
		StudentEnrolmentDto seDto = new StudentEnrolmentDto(se.getStatus(), se.getActive(), se.getStudentEmployer(), 
				se.getEnrolledStudent(), se.getCoopCourseOffering()); 
		seDto.setEnrollmentID(se.getEnrollmentID());
		seDto.setCourseTasks(se.getCourseTasks());
		return seDto; 
	}
			
			
	/******** Task Controller ********/
	
	@PostMapping(value = {"/tasks/{description}/{dueDate}/{taskStatus}" })
	public TaskDto createTask(@PathVariable("description") String description, 
			@PathVariable("dueDate") Date dueDate, 
			@PathVariable("taskStatus") TaskStatus taskStatus) {
		Task task = service.createTask(description, dueDate, taskStatus); 
		return convertToDto(task); 
	}
	
	private TaskDto convertToDto(Task task) { 
		TaskDto tDto = new TaskDto(task.getDescription(), task.getDueDate(), task.getTaskStatus()); 
		tDto.setDocument(task.getDocument());
		return tDto; 
	}
	
	/******** Document Controller ********/
	
	@PostMapping(value = { "/Document/{name}/{url}" })
	public DocumentDto createDocument(@PathVariable("name") String name, @PathVariable("url") String url) {
		Document doc = service.createDocument(name, url);
		return convertToDto(doc);		
	}
	
	private DocumentDto convertToDto(Document doc) {
		DocumentDto dDto = new DocumentDto(doc.getName(), doc.getUrl());
		return dDto; 
	}
	
	
}
