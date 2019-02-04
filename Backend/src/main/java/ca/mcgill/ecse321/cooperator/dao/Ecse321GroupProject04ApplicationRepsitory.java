package ca.mcgill.ecse321.cooperator.dao;


import java.sql.Date;

//import java.sql.Date;
import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.CourseStatus;
import ca.mcgill.ecse321.cooperator.model.Document;
import ca.mcgill.ecse321.cooperator.model.Employer;
//import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;

@Repository
public class Ecse321GroupProject04ApplicationRepsitory {
	
	@Autowired
	EntityManager entityManager;
	/*Some methods here are incomplete (ones related to classes with compositions)*/
	
	@Transactional
	public Student createStudent(String firstName, String lastName, Integer id, String email) {
		Student s = new Student();
		s.setFirstName(firstName);
		s.setLastName(lastName);
		s.setMcgillID(id);
		s.setMcgillEmail(email);
		entityManager.persist(s);
		return s;
	}
	
	@Transactional
	public Student getStudent(Integer id) {
		Student s = entityManager.find(Student.class, id);
		return s;
	}
	

	@Transactional
	public StudentEnrollment createStudentEnrollment(Boolean active, CourseStatus status, String enrollmentID) {
		StudentEnrollment se = new StudentEnrollment();
		se.setActive(active);
		se.setStatus(status);
		se.setEnrollmentID(enrollmentID);
		entityManager.persist(se);
		return se;
	}
	
	@Transactional
	public StudentEnrollment getStudentEnrollment(Integer id) {
		StudentEnrollment se = entityManager.find(StudentEnrollment.class, id);
		return se;
	}
	
	@Transactional
	public Employer createEmployer(String name, String email) {
		Employer e = new Employer();
		e.setName(name);
		e.setEmail(email);
		entityManager.persist(e);
		return e;
	}
	
	@Transactional
	public Employer getEmployer(String email) {
		Employer e  = entityManager.find(Employer.class, email);
		return e;
	}
	
	@Transactional
	public CoopCourseOffering createCoopCourseOffering(Integer year, Term term, Boolean active, String offerID) {
		CoopCourseOffering cco = new  CoopCourseOffering ();
		cco.setYear(year);
		cco.setTerm(term);
		cco.setActive(active);
		cco.setOfferID(offerID);
		entityManager.persist(cco);
		return cco;
	}
	
	@Transactional
	public CoopCourseOffering getCoopCourseOffering(String offerID) {
		CoopCourseOffering cco = entityManager.find(CoopCourseOffering.class, offerID);
		return cco;
	}
	
	@Transactional
	public CoopCourse createCoopCourse(String courseCode, Integer coopTerm, String coopCourseID) {
		CoopCourse c = new CoopCourse();
		c.setCourseCode(courseCode);
		c.setCoopTerm(coopTerm);
		c.setCoopCourseID(coopCourseID);
		entityManager.persist(c);
		return c;
	}
	
	@Transactional
	public CoopCourse getCoopCourse(String coopCourseID) {
		CoopCourse c  = entityManager.find(CoopCourse.class, coopCourseID);
		return c;
	}

	
	@Transactional
	public Task createTask(String description, Date dueDate, TaskStatus status, String taskID) {
		Task t = new Task();
		t.setDescription(description);
		t.setDueDate(dueDate);
		t.setStatus(status);
		t.setTaskID(taskID);
		entityManager.persist(t);
		return t;
	}
	
	@Transactional
	public Task getTask(String taskID) {
		Task t  = entityManager.find(Task.class, taskID);
		return t;
	}
	
	@Transactional
	public Document createDocument(String name, String url, CoopCourseOffering coopCourseOffering, Task task) {
		Document doc = new Document();
		doc.setName(name);
		doc.setUrl(url);
		doc.setCoopCourseOffering(coopCourseOffering);
		doc.setTask(task);
		entityManager.persist(doc);
		return doc;
	}
	
	@Transactional
	public Document getDocument(String url) {
		Document doc = entityManager.find(Document.class, url);
		return doc;
	}
	

}
