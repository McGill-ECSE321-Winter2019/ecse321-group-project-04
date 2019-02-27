package ca.mcgill.ecse321.cooperator.controller;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
import ca.mcgill.ecse321.cooperator.dto.EnrollmentWrapper;
import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.dto.StudentEnrollmentDto;
import ca.mcgill.ecse321.cooperator.dto.TaskDto;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dto.CoopCourseDto;
import ca.mcgill.ecse321.cooperator.dto.CoopCourseOfferingDto;
import ca.mcgill.ecse321.cooperator.dto.DocumentDto;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorRestController {

	@Autowired
	private CooperatorService service;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployerRepository employerRepository;
	@Autowired
	private CoopCourseRepository coopCourseRepository;

	/*------- Student Controller -------*/
	@PostMapping("/student")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
		Student savedStudent =  studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getMcgillID()).toUri();

		return ResponseEntity.created(location).build();
	}

	/*------- Coop Course Controller -------*/

	@PostMapping("/coopCourse")
	public ResponseEntity<Object> createCoopCourse(@Valid @RequestBody CoopCourse coopCourse) {
		CoopCourse savedCoopCourse =  coopCourseRepository.save(coopCourse);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCoopCourse.getCourseCode()).toUri();

		return ResponseEntity.created(location).build();
	}

	/*------- Employer Controller -------*/
	@PostMapping("/employer")
	public ResponseEntity<Object> createEmlpyer(@Valid @RequestBody Employer employer) {
		Employer savedEmployer =  employerRepository.save(employer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployer.getEmail()).toUri();

		return ResponseEntity.created(location).build();
	}

	/*------- Coop Course Offering Controller -------*/

//	@PostMapping(value = { "/coopCourseOffering/{year}/{term}/{active}" })
//	public CoopCourseOfferingDto createCoopCourseOffering(@PathVariable("year") Integer year,
//			@PathVariable("term") Term term, @PathVariable("active") Boolean active, @RequestBody CoopCourseDto cDto) {
//		CoopCourse c = service.getCoopCourse(cDto.getCourseCode());
//		CoopCourseOffering cco = service.createCoopCourseOffering(year, term, active, c);
//		return convertToDto(cco);
//	}
//
//	@GetMapping(value = { "/coopCourseOffering" })
//	public CoopCourseOfferingDto getOfferingByID(@RequestParam(name = "id") String id) {
//		CoopCourseOffering cco = service.getCoopCourseOffering(id);
//		return convertToDto(cco);
//	}
//
//	@GetMapping(value = { "/coopCourseOffering/All" })
//	public List<CoopCourseOfferingDto> getAllCourseOfferings() {
//		List<CoopCourseOfferingDto> ccoDtos = new ArrayList<>();
//		for (CoopCourseOffering cco : service.getAllCoopCourseOfferings()) {
//			ccoDtos.add(convertToDto(cco));
//		}
//		return ccoDtos;
//	}
//
//	private CoopCourseOfferingDto convertToDto(CoopCourseOffering cco) {
//		CoopCourseDto cDto = convertToDto(cco.getCoopCourse());
//		CoopCourseOfferingDto ccoDto = new CoopCourseOfferingDto(cco.getYear(), cco.getTerm(), cco.getActive(), cDto);
//		ccoDto.setOfferID(cco.getOfferID());
//		return ccoDto;
//	}

	/*------- StudentEnrollment Controller -------*/
//	@PostMapping(value = { "/studentEnrollment/{status}/{active}" })
//	public StudentEnrollmentDto createStudentEnrollment(@PathVariable("status") CourseStatus status,
//			@PathVariable("active") Boolean active, @RequestBody EnrollmentWrapper ew) {
//
//		Employer e = service.getEmployer(ew.getEmployer().getEmail());
//		Student s = service.getStudent(ew.getStudent().getMcgillID());
//		CoopCourseOffering cco = service.getCoopCourseOffering(ew.getCco().getOfferID());
//
//		StudentEnrollment se = service.createStudentEnrollment(active, status, s, e, cco);
//		return convertToDto(se);
//	}
//
//	@GetMapping(value = { "/studentEnrollment" })
//	public StudentEnrollmentDto getEnrollmentByID(@RequestParam(name = "id") String id) {
//		StudentEnrollment se = service.getStudentEnrollment(id);
//		return convertToDto(se);
//	}
//
//	@GetMapping(value = { "/studentEnrollment/All" })
//	public List<StudentEnrollmentDto> getAllStudentEnrollments() {
//		List<StudentEnrollmentDto> seDtos = new ArrayList<>();
//		for (StudentEnrollment se : service.getAllStudentEnrollments()) {
//			seDtos.add(convertToDto(se));
//		}
//		return seDtos;
//	}
//
//	private StudentEnrollmentDto convertToDto(StudentEnrollment se) {
//
//		CoopCourseOfferingDto ccoDto = convertToDto(se.getCoopCourseOffering());
//
//		StudentEnrollmentDto seDto = new StudentEnrollmentDto(se.getStatus(), se.getActive(), eDto, sDto, ccoDto);
//
//		seDto.setEnrollmentID(se.getEnrollmentID());
//		return seDto;
//	}
//
//	/*------- Task Controller -------*/
//
//	@PostMapping(value = { "/task/{description}/{taskStatus}" })
//	public TaskDto createTask(@PathVariable("description") String description,
//			@PathVariable("taskStatus") TaskStatus taskStatus, @RequestParam(name = "dueDate") Date dueDate) {
//		Task task = service.createTask(description, dueDate, taskStatus);
//		return convertToDto(task);
//	}
//
//	@GetMapping(value = { "/task" })
//	public TaskDto gettasksByID(@RequestParam(name = "id") long id) {
//		Task t = service.getTask(id);
//		return convertToDto(t);
//	}
//
//	@GetMapping(value = { "/task/All" })
//	public List<TaskDto> getAllTasks() {
//		List<TaskDto> tDtos = new ArrayList<>();
//		for (Task t : service.getAllTasks()) {
//			tDtos.add(convertToDto(t));
//		}
//		return tDtos;
//	}
//
//	private TaskDto convertToDto(Task task) {
//		TaskDto tDto = new TaskDto(task.getDescription(), task.getDueDate(), task.getTaskStatus());
//		return tDto;
//	}
//
//	/*------- Document Controller -------*/
//	// Used @request param for Post because url in path causes issues, we could also
//	// change to requestbody
//	@PostMapping(value = { "/document/{name}" })
//	public DocumentDto createDocument(@PathVariable("name") String name, @RequestParam(name = "url") String url) {
//		Document doc = service.createDocument(name, url);
//		return convertToDto(doc);
//	}
//
//	@GetMapping(value = { "/document" })
//	public DocumentDto getDocumentByID(@RequestParam(name = "url") String url) {
//		Document d = service.getDocument(url);
//		return convertToDto(d);
//	}
//
//	@GetMapping(value = { "/document/All" })
//	public List<DocumentDto> getAllDocuments() {
//		List<DocumentDto> dDtos = new ArrayList<>();
//		for (Document d : service.getAllDocuments()) {
//			dDtos.add(convertToDto(d));
//		}
//		return dDtos;
//	}
//
//	private DocumentDto convertToDto(Document doc) {
//		DocumentDto dDto = new DocumentDto(doc.getName(), doc.getUrl());
//		return dDto;
//	}

}
