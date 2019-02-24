package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
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
	
	/**
	 * Method to create a student
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @return
	 */
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

	
	/**
	 * Method to find a student by its McGill ID
	 * @param id
	 * @return
	 */
	@Transactional
	public Student getStudent(Integer id) {
		Student s = studentRepository.findByMcgillID(id);
		return s;
	}

	/**
	 * Method to get a list of all students
	 * @return
	 */
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	
	/**
	 * Method checks if Student attributes are in an invalid form
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @return
	 */
	private boolean incorrectStudentDetails(String firstName, String lastName, Integer id, String email) {
		if (lastName == null || lastName.trim().length() == 0 || email == null || email.trim().length() == 0
				|| id == null || firstName == null || firstName.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/*--- EMPLOYER METHODS ---*/
	
	/**
	 * Method to create an employer
	 * @param name
	 * @param email
	 * @return
	 */
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

	
	/**
	 * Method to find an employer by email
	 * @param email
	 * @return
	 */
	@Transactional
	public Employer getEmployer(String email) {
		Employer e = employerRepository.findByEmail(email);
		return e;
	}

	/**
	 * Method returns in a list all employers
	 * @return
	 */
	@Transactional
	public List<Employer> getAllEmployers() {
		return toList(employerRepository.findAll());
	}

	/**
	 * Method checks if Employer attributes are in an invalid form
	 * @param name
	 * @param email
	 * @return
	 */
	private boolean incorrectEmployerDetails(String name, String email) {
		if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/*--- COOP COURSE METHODS ---*/
	
	
	/**
	 * Method to create a coop course
	 * @param courseCode
	 * @param coopTerm
	 * @return
	 */
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

	/**
	 * Method to find a course by its ID
	 * @param coopCourseID
	 * @return
	 */
	@Transactional
	public CoopCourse getCoopCourse(String coopCourseID) {
		CoopCourse c = coopCourseRepository.findByCourseCode(coopCourseID);
		return c;
	}

	/**
	 * Method to list all coop courses
	 * @return
	 */
	@Transactional
	public List<CoopCourse> getAllCoopCourses() {
		return toList(coopCourseRepository.findAll());
	}

	/**
	 * Method checks if the Course attributes are in an invalid form
	 * @param courseCode
	 * @param coopTerm
	 * @return
	 */
	private boolean incorrectCourseDetails(String courseCode, Integer coopTerm) {
		if (courseCode == null || courseCode.trim().length() == 0 || coopTerm == null || !(coopTerm >= 1)) {
			return true;
		}
		return false;
	}

	/*--- COOP COURSE OFFERING METHODS ---*/
	
	/**
	 * Method creates a coop course offering
	 * @param year
	 * @param term
	 * @param active
	 * @param coopCourse
	 * @return
	 */
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
		
		coopCourse.addCourseOffering(cco);
		
		coopCourseOfferingRepository.save(cco);
		return cco;
	}

	/**
	 * Method to find a course offering by its ID
	 * @param offerID
	 * @return
	 */
	@Transactional
	public CoopCourseOffering getCoopCourseOffering(String offerID) {
		CoopCourseOffering cco = coopCourseOfferingRepository.findByOfferID(offerID);
		return cco;
	}

	/**
	 * Method returns a list of all coop course offering
	 * @return
	 */
	@Transactional
	public List<CoopCourseOffering> getAllCoopCourseOfferings() {
		return toList(coopCourseOfferingRepository.findAll());
	}
	
	/**
	 * Method checks if Course attributes are in an invalid form
	 * @param year
	 * @param term
	 * @param active
	 * @param coopCourse
	 * @return
	 */
	private boolean incorrectCourseOfferingDetails(Integer year, Term term, Boolean active, CoopCourse coopCourse) {
		if (year == null || term == null || active == null || coopCourse == null) {
			return true;
		}
		return false;
	}

	/*--- STUDENT ENROLLMENT METHODS ---*/
	
	/**
	 * Method created a student enrollment
	 * @param active
	 * @param status
	 * @param s
	 * @param e
	 * @param cco
	 * @return
	 */
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
		e.addStudentEnrollment(se);
		s.addCourseEnrollment(se);

		studentEnrollmentRepository.save(se);
		return se;
	}

	/**
	 * Method to find a student enrollment by its ID
	 * @param id
	 * @return
	 */
	@Transactional
	public StudentEnrollment getStudentEnrollment(String id) {
		StudentEnrollment se = studentEnrollmentRepository.findByEnrollmentID(id);
		return se;
	}

	/**
	 * Method to list all enrollments
	 * @return
	 */
	@Transactional
	public List<StudentEnrollment> getAllStudentEnrollments() {
		return toList(studentEnrollmentRepository.findAll());
	}
	
	/**
	 * Method to list all student enrollments
	 * @param emp
	 * @return
	 */
	@Transactional
	public List<StudentEnrollment> getEmployersStudentEnrollments(Employer emp) {
		return toList(studentEnrollmentRepository.findByStudentEmployer(emp));
	}

	/**
	 * Method checks if Student Enrollment attributes are in an invalid form
	 * @param active
	 * @param status
	 * @param s
	 * @param e
	 * @param cco
	 * @return
	 */
	private boolean incorrectStudentEnrollmentDetails(Boolean active, CourseStatus status, Student s, Employer e,
			CoopCourseOffering cco) {
		if (active == null || status == null || s == null || e == null) {
			return true;
		}
		return false;
	}

	/*--- TASK METHODS ---*/
	
	/**
	 * Method to create a task
	 * @param description
	 * @param dueDate
	 * @param status
	 * @return
	 */
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

	/**
	 * Method to find a task by its ID
	 * @param taskID
	 * @return
	 */
	@Transactional
	public Task getTask(long taskID) {
		Task t = taskRepository.findByTaskID(taskID);
		return t;
	}

	/**
	 * Method to list all tasks
	 * @return
	 */
	@Transactional
	public List<Task> getAllTasks() {
		return toList(taskRepository.findAll());
	}

	/**
	 * Method checks if Task attributes are in an invalid form
	 * @param description
	 * @param dueDate
	 * @param status
	 * @return
	 */
	private boolean incorrectTaskDetails(String description, Date dueDate, TaskStatus status) {
		if (description == null || description.trim().length() == 0 || dueDate == null || status == null) {
			return true;
		}
		return false;
	}

	/*--- DOCUMENT METHODS ---*/
	
	
	/**
	 * Method to create a document
	 * @param name
	 * @param url
	 * @return
	 */
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

	/**
	 *Method to find a document by its URL
	 * @param url
	 * @return
	 */
	@Transactional
	public Document getDocument(String url) {
		Document doc = documentRepository.findByUrl(url);
		return doc;
	}

	/**
	 * Method to list all documents
	 * @return
	 */
	@Transactional
	public List<Document> getAllDocuments() {
		return toList(documentRepository.findAll());
	}
	
	/**
	 * Method checks if Document attributes are in an invalid form
	 * @param name
	 * @param url
	 * @return
	 */
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
