package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentEnrollmentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;

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

	/*------- STUDENT METHODS -------*/

	/**
	 * Method to create a student
	 * 
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @return
	 */
	@Transactional
	public Student createStudent(String firstName, String lastName, Integer id, String email) {
		if (incorrectStudentDetails(firstName, lastName, id, email)) {
			throw new InvalidParameterException("Your student details are incomplete!");
		}
		Student s = new Student();
		s.setFirstName(firstName);
		s.setLastName(lastName);
		s.setMcgillID(id);
		s.setMcgillEmail(email);
		containsStudent(s.getMcgillID());
		return studentRepository.save(s);
	}

	/**
	 * Method to create a student
	 * 
	 * @param s
	 * @return
	 */
	@Transactional
	public Student createStudent(Student s) {
		return createStudent(s.getFirstName(), s.getLastName(), s.getMcgillID(), s.getMcgillEmail());
	}

	/**
	 * Method to find a student by its McGill ID
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Student getStudent(Integer id) {
		Student s = studentRepository.findByMcgillID(id);
		if (s == null)
			throw new EntityNotFoundException("Could not find a Student with ID " + id);
		return s;
	}

	/**
	 * Method to get a list of all students
	 * 
	 * @return
	 */
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	/**
	 * Method checks if Student attributes are in an invalid form
	 * 
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @return
	 */
	private boolean incorrectStudentDetails(String firstName, String lastName, Integer id, String email) {
		if (lastName == null || lastName.trim().length() == 0 || email == null || email.trim().length() == 0
				|| id == null || String.valueOf((int) id).length() < 9 || firstName == null
				|| firstName.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method checks if the student already exists
	 * 
	 * @param id
	 */
	@Transactional
	public void containsStudent(Integer id) {
		if (studentRepository.existsById(id))
			throw new EntityExistsException("Student Already Exists");
	}

	/*------- EMPLOYER METHODS -------*/

	/**
	 * Method to create an employer
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	@Transactional
	public Employer createEmployer(String name, String email, String address) {
		if (incorrectEmployerDetails(name, email, address)) {
			throw new InvalidParameterException("Your employer details are incomplete!");
		}
		Employer e = new Employer();
		e.setName(name);
		e.setEmail(email);
		e.setAddress(address);
		containsEmployer(e.getEmail());
		return employerRepository.save(e);
	}

	/**
	 * Method to create an employer
	 *
	 * @param e
	 */
	@Transactional
	public Employer createEmployer(Employer e) {
		Employer savedEmployer = createEmployer(e.getName(), e.getEmail(), e.getAddress());
		return savedEmployer;
	}

	/**
	 * Method to find an employer by email
	 * 
	 * @param email
	 * @return
	 */
	@Transactional
	public Employer getEmployer(String email) {
		Employer e = employerRepository.findByEmail(email);
		if (e == null)
			throw new EntityNotFoundException("Could not find an Employer with email " + email);
		return e;
	}

	/**
	 * Method returns in a list all employers
	 * 
	 * @return
	 */
	@Transactional
	public List<Employer> getAllEmployers() {
		return toList(employerRepository.findAll());
	}

	/**
	 * Method checks if Employer attributes are in an invalid form
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	private boolean incorrectEmployerDetails(String name, String email, String address) {
		if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0) {
			return true;
		}
//		if (address == null || address.trim().length() == 0) {
//			return true;
//		}
		return false;
	}

	/**
	 * Method checks if employer already exists
	 * 
	 * @param id
	 */
	@Transactional
	public void containsEmployer(String id) {
		if (employerRepository.existsById(id))
			throw new EntityExistsException("Employer Already Exists");
	}

	/*------- COOP COURSE METHODS -------*/

	/**
	 * Method to create a coop course
	 * 
	 * @param courseCode
	 * @param coopTerm
	 * @return
	 */
	@Transactional
	public CoopCourse createCoopCourse(String courseCode, Integer coopTerm) {
		if (incorrectCourseDetails(courseCode, coopTerm)) {
			throw new InvalidParameterException("Your course details are incomplete!");
		}
		CoopCourse c = new CoopCourse();
		c.setCourseCode(courseCode);
		c.setCoopTerm(coopTerm);
		containsCourse(c.getCourseCode());
		return coopCourseRepository.save(c);
	}

	/**
	 * Method to create a Coop Course
	 *
	 * @param cc
	 */
	@Transactional
	public CoopCourse createCoopCourse(CoopCourse cc) {
		CoopCourse savedCourse = createCoopCourse(cc.getCourseCode(), cc.getCoopTerm());
		return savedCourse;
	}

	/**
	 * Method to find a course by its ID
	 * 
	 * @param coopCourseID
	 * @return
	 */
	@Transactional
	public CoopCourse getCoopCourse(String coopCourseID) {
		CoopCourse c = coopCourseRepository.findByCourseCode(coopCourseID);
		if (c == null)
			throw new EntityNotFoundException("Could not find a CO-OP Course with ID " + coopCourseID);
		return c;
	}

	/**
	 * Method to list all coop courses
	 * 
	 * @return
	 */
	@Transactional
	public List<CoopCourse> getAllCoopCourses() {
		return toList(coopCourseRepository.findAll());
	}

	/**
	 * Method checks if the Course attributes are in an invalid form
	 * 
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

	/**
	 * Method checks if the course already exists
	 * 
	 * @param id
	 */
	@Transactional
	public void containsCourse(String id) {
		if (coopCourseRepository.existsById(id))
			throw new EntityExistsException("Course Already Exists");
	}

	/*------- COOP COURSE OFFERING METHODS -------*/

	/**
	 * Method creates a coop course offering
	 * 
	 * @param year
	 * @param term
	 * @param active
	 * @param coopCourse
	 * @return
	 */
	@Transactional
	public CoopCourseOffering createCoopCourseOffering(Integer year, Term term, Boolean active, CoopCourse coopCourse) {
		if (incorrectCourseOfferingDetails(year, term, active, coopCourse)) {
			throw new InvalidParameterException("Your course offering details are incomplete!");
		}
		CoopCourseOffering cco = new CoopCourseOffering();
		cco.setYear(year);
		cco.setTerm(term);
		cco.setActive(active);
		cco.setCoopCourse(coopCourse);
		cco.setOfferID();
		containsCourseOffering(cco.getOfferID());
		return coopCourseOfferingRepository.save(cco);
	}

	/**
	 * Method creates a coop course offering
	 * 
	 * @param cco
	 * @return
	 */
	@Transactional
	public CoopCourseOffering createCoopCourseOffering(CoopCourseOffering cco, CoopCourse c) {
		CoopCourseOffering savedCco = createCoopCourseOffering(cco.getYear(), cco.getTerm(), cco.getActive(), c);
		return savedCco;
	}

	/**
	 * Method to find a course offering by its ID
	 * 
	 * @param offerID
	 * @return
	 */
	@Transactional
	public CoopCourseOffering getCoopCourseOffering(String offerID) {
		CoopCourseOffering cco = coopCourseOfferingRepository.findByOfferID(offerID);
		if (cco == null)
			throw new EntityNotFoundException("Could not find a CO-OP Course Offering with ID " + offerID);
		return cco;
	}

	/**
	 * Method returns a list of all coop course offering
	 * 
	 * @return
	 */
	@Transactional
	public List<CoopCourseOffering> getAllCoopCourseOfferings() {
		return toList(coopCourseOfferingRepository.findAll());
	}

	/**
	 * Method checks if Course attributes are in an invalid form
	 * 
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

	/**
	 * Method checks if the course offering already exists
	 * 
	 * @param id
	 */
	@Transactional
	public void containsCourseOffering(String id) {
		if (coopCourseOfferingRepository.existsById(id))
			throw new EntityExistsException("Offering Already Exists");
	}

	/*------- STUDENT ENROLLMENT METHODS -------*/

	/**
	 * Method created a student enrollment
	 * 
	 * @param active
	 * @param status
	 * @param s
	 * @param e
	 * @param cco
	 * @return
	 */
	@Transactional
	public StudentEnrollment createStudentEnrollment(Boolean active, CourseStatus status, Student s, Employer e,
			CoopCourseOffering cco, String coopAcceptanceForm, String employerContract, Date startDate, Date endDate,
			Boolean workPermit) {
		if (incorrectStudentEnrollmentDetails(active, status, s, e, cco, coopAcceptanceForm, employerContract,
				startDate, endDate, workPermit)) {
			throw new InvalidParameterException("Your student enrollment details are incomplete!");
		}
		StudentEnrollment se = new StudentEnrollment();
		se.setEnrolledStudent(s);
		se.setStudentEmployer(e);
		se.setActive(active);
		se.setStatus(status);
		se.setWorkPermit(workPermit);
		se.setStartDate(startDate);
		se.setEndDate(endDate);
		se.setEnrollmentID(s, cco);
		se.setCoopCourseOffering(cco);
		containsEnrollment(se.getEnrollmentID());

		studentEnrollmentRepository.save(se);

		// Create the required initial tasks and populate them.
		se = populateStudentEnrollment(se, coopAcceptanceForm, employerContract);
		return se;
	}

	/**
	 * Method to create a Student Enrollment
	 *
	 * @param se
	 * @param coopAcceptanceForm
	 * @param employerContract
	 */
	public StudentEnrollment createStudentEnrollment(StudentEnrollment se, Student s, Employer e,
			CoopCourseOffering cco, String coopAcceptanceForm, String employerContract) {

		StudentEnrollment savedSe = createStudentEnrollment(se.getActive(), se.getStatus(), s, e, cco,
				coopAcceptanceForm, employerContract, se.getStartDate(), se.getEndDate(), se.getWorkPermit());
		return savedSe;
	}

	/**
	 * Method to create the initial tasks of a Student Enrollment.
	 * <p>
	 * This method requires that the {@code StudentEnrollment} passed as argument
	 * has already been persisted to the database.
	 *
	 * @param se
	 * @param coopAcceptanceForm
	 * @param employerContract
	 */
	private StudentEnrollment populateStudentEnrollment(StudentEnrollment se, String coopAcceptanceForm,
			String employerContract) {
		// Create the first two tasks which are completed at the time of registration
		Calendar currentCal = Calendar.getInstance();
		Date currentDate = new Date(currentCal.getTimeInMillis());
		Task t1 = createTask("Report CO-OP Position Acceptance", "Submit the CO-OP position acceptance form.",
				currentDate, TaskStatus.COMPLETED, se.getEnrollmentID());
		createDocument("CO-OP Position Acceptance Form", coopAcceptanceForm, se.getEnrollmentID(), t1.getName());

		Task t2 = createTask("Upload Employer Contract", "Submit the employer contract document.", currentDate,
				TaskStatus.COMPLETED, se.getEnrollmentID());

		createDocument("Employer Contract", employerContract, se.getEnrollmentID(), t2.getName());

		// Create the rest of the tasks that need to be completed throughout the
		// course offering.
		Calendar dateInTwoWeeks = Calendar.getInstance();
		dateInTwoWeeks.add(Calendar.DAY_OF_MONTH, +14);
		Calendar dateInFourMonths = Calendar.getInstance();
		dateInFourMonths.add(Calendar.MONTH, +4);
		createTask("Initial Workload Report", "Submit an initial report of the tasks and workload of the internship.",
				new Date(dateInTwoWeeks.getTimeInMillis()), TaskStatus.INCOMPLETE, se.getEnrollmentID());
		createTask("Technical Experience Report", "Submit the term technical report about the internship experience.",
				new Date(dateInFourMonths.getTimeInMillis()), TaskStatus.INCOMPLETE, se.getEnrollmentID());
		createTask("Internship Evaluation Report", "Submit the final evaluation report for the internship experience.",
				new Date(dateInFourMonths.getTimeInMillis()), TaskStatus.INCOMPLETE, se.getEnrollmentID());
		return getStudentEnrollment(se.getEnrollmentID());
	}

	/**
	 * Method to find a student enrollment by its ID
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public StudentEnrollment getStudentEnrollment(String id) {
		StudentEnrollment se = studentEnrollmentRepository.findByEnrollmentID(id);
		if (se == null)
			throw new EntityNotFoundException("Could not find a Student Enrollment with ID " + id);
		return se;
	}

	/**
	 * Method to list all enrollments
	 * 
	 * @return
	 */
	@Transactional
	public List<StudentEnrollment> getAllStudentEnrollments() {
		return toList(studentEnrollmentRepository.findAll());
	}

	/**
	 * Method to list all student enrollments
	 * 
	 * @param emp
	 * @return
	 */
	@Transactional
	public List<StudentEnrollment> getEmployersStudentEnrollments(Employer emp) {
		return toList(studentEnrollmentRepository.findByStudentEmployer(emp));
	}

	/**
	 * Method checks if Student Enrollment attributes are in an invalid form
	 * 
	 * @param active
	 * @param status
	 * @param s
	 * @param e
	 * @param cco
	 * @return
	 */
	private boolean incorrectStudentEnrollmentDetails(Boolean active, CourseStatus status, Student s, Employer e,
			CoopCourseOffering cco, String coopAcceptanceForm, String employerContract, Date startDate, Date endDate,
			Boolean workPermit) {
		if (active == null || status == null || s == null || e == null || coopAcceptanceForm == null
				|| coopAcceptanceForm.equals("") || employerContract == null || employerContract.equals("")) {
			return true;
		}
//		if(startDate == null || endDate == null || workPermit == null) {
//			return true;
//		}
		return false;
	}

	/**
	 * Method checks if the enrollment already exists
	 * 
	 * @param id
	 */
	@Transactional
	public void containsEnrollment(String id) {
		if (studentEnrollmentRepository.existsById(id))
			throw new EntityExistsException("Enrollment Already Exists");
	}

	/*------- TASK METHODS -------*/

	/**
	 * Method to create a task
	 * 
	 * @param description
	 * @param dueDate
	 * @param status
	 * @return
	 */
	@Transactional
	public Task createTask(String name, String description, Date dueDate, TaskStatus status, String enrollmentID) {
		if (incorrectTaskDetails(name, description, dueDate, status, enrollmentID)) {
			throw new InvalidParameterException("Your task details are incomplete!");
		}
		Task t = new Task();
		t.setName(name);
		t.setDescription(description);
		t.setDueDate(dueDate);
		t.setTaskStatus(status);
		StudentEnrollment se = studentEnrollmentRepository.findByEnrollmentID(enrollmentID);
		se.addCourseTasks(t);
		StudentEnrollment saved = studentEnrollmentRepository.save(se);
		return saved.getTask(name);
		// taskRepository.save(t);
		// return t;
	}

	/**
	 * Method to create a task
	 * 
	 * @param t
	 * @param se
	 * @return
	 */
	@Transactional
	public Task createTask(Task t, String enrollmentID) {
		Task savedTask = createTask(t.getName(), t.getDescription(), t.getDueDate(), t.getTaskStatus(), enrollmentID);
		return savedTask;
	}

	/**
	 * Method to find a task by its ID
	 * 
	 * @param taskID
	 * @return
	 */
	@Transactional
	public Task getTask(long taskID) {
		Task t = taskRepository.findByTaskID(taskID);
		if (t == null)
			throw new EntityNotFoundException("Could not find a Task with ID " + taskID);
		return t;
	}

	/**
	 * Method to list all tasks
	 * 
	 * @return
	 */
	@Transactional
	public List<Task> getAllTasks() {
		return toList(taskRepository.findAll());
	}

	/**
	 * Method checks if Task attributes are in an invalid form
	 * 
	 * @param description
	 * @param dueDate
	 * @param status
	 * @return
	 */
	private boolean incorrectTaskDetails(String name, String description, Date dueDate, TaskStatus status,
			String enrollmentID) {
		if (name == null || description == null || description.trim().length() == 0 || dueDate == null || status == null
				|| enrollmentID == null) {
			return true;
		}
		return false;
	}

	/*------- DOCUMENT METHODS -------*/

	/**
	 * Method to create a document
	 * 
	 * @param name
	 * @param url
	 * @return
	 */
	@Transactional
	public Document createDocument(String name, String url, String enrollmentID, String taskName) {
		if (incorrectDocumentDetails(name, url, enrollmentID, taskName)) {
			throw new InvalidParameterException("Your document details are incomplete!");
		}

		Task t = studentEnrollmentRepository.findByEnrollmentID(enrollmentID).getTask(taskName);

		if (t.getDocument(name) != null) {
			t.getDocument(name).setUrl(url);
			taskRepository.save(t);
			return t.getDocument(name);
		} else {
			Document d = new Document();
			d.setName(name);
			d.setUrl(url);
			t.addDocument(d);
			Task saved = taskRepository.save(t);
			return saved.getDocument(name);
		}
	}

	/**
	 * Method to create a document
	 * 
	 * @param d
	 * @param t
	 * @return
	 */
	@Transactional
	public Document createDocument(Document d, String enrollmentID, String taskName) {
		Document savedDocument = createDocument(d.getName(), d.getUrl(), enrollmentID, taskName);
		return savedDocument;
	}

	/**
	 * Method to find a document by its URL
	 * 
	 * @param url
	 * @return
	 */
	@Transactional
	public Document getDocument(String url) {
		Document doc = documentRepository.findByUrl(url);
		if (doc == null)
			throw new EntityNotFoundException("Could not find a Document with URL " + url);
		return doc;
	}

	/**
	 * Method to list all documents
	 * 
	 * @return
	 */
	@Transactional
	public List<Document> getAllDocuments() {
		return toList(documentRepository.findAll());
	}

	/**
	 * Method checks if Document attributes are in an invalid form
	 * 
	 * @param name
	 * @param url
	 * @return
	 */
	private boolean incorrectDocumentDetails(String name, String url, String enrollmentID, String taskName) {
		if (name == null || name.trim().length() == 0 || url == null || url.trim().length() == 0 || enrollmentID == null
				|| taskName == null) {
			return true;
		}
		return false;
	}

	/*------- UTILITY METHODS -------*/

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
