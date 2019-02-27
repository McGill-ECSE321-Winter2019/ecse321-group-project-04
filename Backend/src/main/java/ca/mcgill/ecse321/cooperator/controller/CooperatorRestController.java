package ca.mcgill.ecse321.cooperator.controller;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorRestController {

	@Autowired
	private CooperatorService service;


	/*------- Student Controller -------*/
	
	@PostMapping("/student")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
		
		Student savedStudent = service.createStudent(student);
		// create URI of where the enitity can be found
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedStudent.getMcgillID()).toUri();
		//return 201 Status with location in header and body
		return ResponseEntity.created(location).body(location);
	}

	/*------- Employer Controller -------*/
	@PostMapping("/employer")
	public ResponseEntity<Object> createEmlpyer(@Valid @RequestBody Employer employer) {
		Employer savedEmployer = service.createEmployer(employer);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedEmployer.getEmail()).toUri();

		return ResponseEntity.created(location).body(location);
	}

	/*------- Coop Course Controller -------*/

	@PostMapping("/coopCourse")
	public ResponseEntity<Object> createCoopCourse(@Valid @RequestBody CoopCourse coopCourse) {
		CoopCourse savedCoopCourse = service.createCoopCourse(coopCourse);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedCoopCourse.getCourseCode()).toUri();

		return ResponseEntity.created(location).body(location);
	}

	/*------- Coop Course Offering Controller -------*/

	@PostMapping("/coopCourseOffering")
	public ResponseEntity<Object> createCourseOffering(@Valid @RequestBody CoopCourseOffering cco,
			@RequestParam(name = "courseCode") String courseCode) {

		CoopCourse c = service.getCoopCourse(courseCode);

		CoopCourseOffering savedcco = service.createCoopCourseOffering(cco, c);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedcco.getOfferID()).toUri();

		return ResponseEntity.created(location).body(location);
	}

	/*------- StudentEnrollment Controller -------*/
	@PostMapping("/studentEnrollment")
	public ResponseEntity<Object> createStudentEnrollment(@Valid @RequestBody StudentEnrollment se,
			@RequestParam(name = "courseOfferingID") String offerID, @RequestParam(name = "studentID") Integer id,
			@RequestParam(name = "employerEmail") String email,
			@RequestParam(name = "coopAcceptanceForm") String coopAcceptanceForm,
			@RequestParam(name = "employerContract") String employerContract) {

		CoopCourseOffering cco = service.getCoopCourseOffering(offerID);
		Student s = service.getStudent(id);
		Employer e = service.getEmployer(email);

		StudentEnrollment savedse = service.createStudentEnrollment(se, s,e,cco, coopAcceptanceForm, employerContract);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedse.getEnrollmentID()).toUri();

		return ResponseEntity.created(location).body(location);
	}

	/*------- Task Controller -------*/

	@PostMapping("/task")
	public ResponseEntity<Object> createTask(@Valid @RequestBody Task task,
			@RequestParam(name = "studentEnrollmentID") String id) {
		StudentEnrollment se = service.getStudentEnrollment(id);
		Task savedTask = service.createTask(task, se);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedTask.getTaskID()).toUri();

		return ResponseEntity.created(location).body(location);
	}

	/*------- Document Controller -------*/

	@PostMapping("/document")
	public ResponseEntity<Object> createDocument(@Valid @RequestBody Document document,
			@RequestParam(name = "taskID") long id) {
		Task t = service.getTask(id);
		Document savedDocument = service.createDocument(document, t);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
				.buildAndExpand(savedDocument.getDocumentID()).toUri();

		return ResponseEntity.created(location).body(location);
	}
}