package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;

@Service
public class CooperatorService {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EmployerRepository employerRepository;
	@Autowired
	CoopCourseRepository coopCourseRepository;
	@Autowired
	CoopCourseOfferingRepository coopCourseOfferingRepository;
	@Autowired
	StudentEnrollmentRepository studentEnrollmentRepository;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	DocumentRepository documentRepository;

	/*--- STUDENT METHODS ---*/
	@Transactional
	public Student createStudent(String firstName, String lastName, Integer id, String email) {
		if (incorrectStudentDetails(firstName, lastName, id, email)) {
			throw new IllegalArgumentException("Your student details are incomplete!");
		}
		Student s = new Student();
		s.setFirstName(firstName);
		s.setLastName(lastName);
		s.setMcgillID(id);
		s.setMcgillEmail(email);
		studentRepository.save(s);
		return s;
	}

	@Transactional
	public Student getStudent(Integer id) {
		Student s = studentRepository.findByMcgillID(id);
		return s;
	}

	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	// Method that checks if Student attributes are in a invalid form
	private boolean incorrectStudentDetails(String firstName, String lastName, Integer id, String email) {
		if (lastName == null || lastName.trim().length() == 0 || email == null || email.trim().length() == 0
				|| id == null || firstName == null || firstName.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/*--- EMPLOYER METHODS ---*/
	@Transactional
	public Employer createEmployer(String name, String email) {
		if (incorrectEmployerDetails(name, email)) {
			throw new IllegalArgumentException("Your employer details are incomplete!");
		}
		Employer e = new Employer();
		e.setName(name);
		e.setEmail(email);
		employerRepository.save(e);
		return e;
	}

	@Transactional
	public Employer getEmployer(String email) {
		Employer e = employerRepository.findByEmail(email);
		return e;
	}

	@Transactional
	public List<Employer> getAllEmployers() {
		return toList(employerRepository.findAll());
	}

	// Method that checks if Employer attributes are in a invalid form
	private boolean incorrectEmployerDetails(String name, String email) {
		if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/*--- COOP COURSE METHODS ---*/
	@Transactional
	public CoopCourse createCoopCourse(String courseCode, Integer coopTerm) {
		if (incorrectCourseDetails(courseCode, coopTerm)) {
			throw new IllegalArgumentException("Your course details are incomplete!");
		}
		CoopCourse c = new CoopCourse();
		c.setCourseCode(courseCode);
		c.setCoopTerm(coopTerm);
		coopCourseRepository.save(c);
		return c;
	}

	@Transactional
	public CoopCourse getCoopCourse(String coopCourseID) {
		CoopCourse c = coopCourseRepository.findByCourseCode(coopCourseID);
		return c;
	}

	@Transactional
	public List<CoopCourse> getAllCoopCourses() {
		return toList(coopCourseRepository.findAll());
	}

	// Method that checks if Course attributes are in a invalid form
	private boolean incorrectCourseDetails(String courseCode, Integer coopTerm) {
		if (courseCode == null || courseCode.trim().length() == 0 || coopTerm == null || !(coopTerm >= 1)) {
			return true;
		}
		return false;
	}

	/*--- COOP COURSE OFFERING METHODS ---*/
	@Transactional
	public CoopCourseOffering createCoopCourseOffering(Integer year, Term term, Boolean active, CoopCourse coopCourse) {
		if (incorrectCourseOfferingDetails(year, term, active, coopCourse)) {
			throw new IllegalArgumentException("Your course offering details are incomplete!");
		}
		CoopCourseOffering cco = new CoopCourseOffering();
		cco.setYear(year);
		cco.setTerm(term);
		cco.setActive(active);
		cco.setCoopCourse(coopCourse);
		String offerID = coopCourse.getCourseCode();
		switch (term) {
		case FALL:
			offerID += "-F";
			break;
		case WINTER:
			offerID += "-W";
			break;
		case SUMMER:
			offerID += "-S";
			break;
		}
		offerID += year % 2000;
		cco.setOfferID(offerID);
		coopCourseOfferingRepository.save(cco);
		return cco;
	}

	@Transactional
	public CoopCourseOffering getCoopCourseOffering(String offerID) {
		CoopCourseOffering cco = coopCourseOfferingRepository.findByOfferID(offerID);
		return cco;
	}

	@Transactional
	public List<CoopCourseOffering> getAllCoopCourseOfferings() {
		return toList(coopCourseOfferingRepository.findAll());
	}
	
	// Method that checks if Course attributes are in a invalid form
	private boolean incorrectCourseOfferingDetails(Integer year, Term term, Boolean active, CoopCourse coopCourse) {
		if (year == null || term == null || active == null || coopCourse == null) {
			return true;
		}
		return false;
	}

	/*--- STUDENT ENROLLMENT METHODS ---*/
	@Transactional
	public StudentEnrollment createStudentEnrollment(Boolean active, CourseStatus status, Student s, Employer e,
			CoopCourseOffering cco) {
		if (incorrectStudentEnrollmentDetails(active, status, s, e, cco)) {
			throw new IllegalArgumentException("Your student enrollment details are incomplete!");
		}
		StudentEnrollment se = new StudentEnrollment();
		se.setEnrolledStudent(s);
		se.setStudentEmployer(e);
		se.setActive(active);
		se.setStatus(status);
		se.setEnrollmentID(s.getMcgillID() + "-" + cco.getOfferID());
		se.setCoopCourseOffering(cco);

		cco.addStudentEnrollment(se);

		studentEnrollmentRepository.save(se);
		return se;
	}

	@Transactional
	public StudentEnrollment getStudentEnrollment(String id) {
		StudentEnrollment se = studentEnrollmentRepository.findByEnrollmentID(id);
		return se;
	}

	@Transactional
	public List<StudentEnrollment> getAllStudentEnrollments() {
		return toList(studentEnrollmentRepository.findAll());
	}
	
	@Transactional
	public List<StudentEnrollment> getEmployersStudentEnrollments(Employer emp) {
		return toList(studentEnrollmentRepository.findByStudentEmployer(emp));
	}

	// Method that checks if Student Enrollment attributes are in a invalid form
	private boolean incorrectStudentEnrollmentDetails(Boolean active, CourseStatus status, Student s, Employer e,
			CoopCourseOffering cco) {
		if (active == null || status == null || s == null || e == null) {
			return true;
		}
		return false;
	}

	/*--- TASK METHODS ---*/
	@Transactional
	public Task createTask(String description, Date dueDate, TaskStatus status) {
		if (incorrectTaskDetails(description, dueDate, status)) {
			throw new IllegalArgumentException("Your task details are incomplete!");
		}
		Task t = new Task();
		t.setDescription(description);
		t.setDueDate(dueDate);
		t.setTaskStatus(status);

		taskRepository.save(t);
		return t;
	}

	@Transactional
	public Task getTask(long taskID) {
		Task t = taskRepository.findByTaskID(taskID);
		return t;
	}

	@Transactional
	public List<Task> getAllTasks() {
		return toList(taskRepository.findAll());
	}

	// Method that checks if Task attributes are in a invalid form
	private boolean incorrectTaskDetails(String description, Date dueDate, TaskStatus status) {
		if (description == null || description.trim().length() == 0 || dueDate == null || status == null) {
			return true;
		}
		return false;
	}

	/*--- DOCUMENT METHODS ---*/
	@Transactional
	public Document createDocument(String name, String url) {
		if (incorrectDocumentDetails(name, url)) {
			throw new IllegalArgumentException("Your document details are incomplete!");
		}
		Document d = new Document();
		d.setName(name);
		d.setUrl(url);
		documentRepository.save(d);
		return d;
	}

	@Transactional
	public Document getDocument(String url) {
		Document doc = documentRepository.findByUrl(url);
		return doc;
	}

	@Transactional
	public List<Document> getAllDocuments() {
		return toList(documentRepository.findAll());
	}
	
	// Method that checks if Document attributes are in a invalid form
	private boolean incorrectDocumentDetails(String name, String url) {
		if (name == null || name.trim().length() == 0 || url == null || url.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/*--- UTILITY METHODS ---*/
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
