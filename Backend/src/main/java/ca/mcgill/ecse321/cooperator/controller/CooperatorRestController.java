package ca.mcgill.ecse321.cooperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;
import ca.mcgill.ecse321.cooperator.dto.EmployerDto;
import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.dto.CoopCourseDto;
import ca.mcgill.ecse321.cooperator.dto.CoopCourseOfferingDto;

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

}
