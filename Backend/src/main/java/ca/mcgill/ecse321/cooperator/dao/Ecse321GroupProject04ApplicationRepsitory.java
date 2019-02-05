package ca.mcgill.ecse321.cooperator.dao;


import java.sql.Date;
import java.util.List;
import java.util.Set;

//import java.sql.Date;
import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	
	private static final Set<StudentEnrollment> studentEnrollmentss = null;
	@Autowired
	@PersistenceContext
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
        public void removeStudent(Student s) {
                entityManager.remove(entityManager.contains(s)? s: entityManager.merge(s));
                entityManager.flush();
        }

	@Transactional
	public StudentEnrollment createStudentEnrollment(Student student, Employer employer, Boolean active, CourseStatus status, String enrollmentID, CoopCourseOffering cco) {
		StudentEnrollment se = new StudentEnrollment();
                se.setEnrolledStudent(student);
                se.setStudentEmployer(employer);
		se.setActive(active);
		se.setStatus(status);
		se.setEnrollmentID(enrollmentID);
                se.setCoopCourseOffering(cco);

                cco.addStudentEnrollment(se);

		entityManager.persist(se);
		return se;
	}
	
	@Transactional
	public StudentEnrollment getStudentEnrollment(String id) {
		StudentEnrollment se = entityManager.find(StudentEnrollment.class, id);
		return se;
	}

        @Transactional
        public void removeStudentEnrollment(StudentEnrollment se) {
                entityManager.remove(entityManager.contains(se)? se: entityManager.merge(se));
                entityManager.flush();
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
        public void removeEmployer(Employer employer) {
                entityManager.remove(entityManager.contains(employer)? employer: entityManager.merge(employer));
                entityManager.flush();
        }
	
	@Transactional
	public CoopCourseOffering createCoopCourseOffering(Integer year, Term term, Boolean active, CoopCourse coopCourse) {
		CoopCourseOffering cco = new CoopCourseOffering();
		cco.setYear(year);
		cco.setTerm(term);
		cco.setActive(active);
                cco.setCoopCourse(coopCourse);
                String offerID = coopCourse.getCourseCode();
                switch(term) {
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
                offerID += year;
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
        public void removeCoopCourseOffering(CoopCourseOffering cco) {
                entityManager.remove(entityManager.contains(cco)? cco: entityManager.merge(cco));
                entityManager.flush();
        }
	
	@Transactional
	public CoopCourse createCoopCourse(String courseCode, Integer coopTerm) {
		CoopCourse c = new CoopCourse();
		c.setCourseCode(courseCode);
		c.setCoopTerm(coopTerm);
		entityManager.persist(c);
		return c;
	}
	
	@Transactional
	public CoopCourse getCoopCourse(String coopCourseID) {
		CoopCourse c  = entityManager.find(CoopCourse.class, coopCourseID);
		return c;
	}

        @Transactional
        public void removeCoopCourse(CoopCourse c) {
                entityManager.remove(entityManager.contains(c)? c: entityManager.merge(c));
                entityManager.flush();
        }
	
	@Transactional
	public Task createTask(String description, Date dueDate, TaskStatus status, String taskID, StudentEnrollment se) {
		Task t = new Task();
		t.setDescription(description);
		t.setDueDate(dueDate);
		t.setTaskStatus(status);
		t.setTaskID(taskID);
                t.setStudentEnrollment(se);

                se.addCourseTask(t);

		entityManager.persist(t);
		return t;
	}
	
	@Transactional
	public Task getTask(String taskID) {
		Task t  = entityManager.find(Task.class, taskID);
		return t;
	}

        @Transactional
        public void removeTask(Task t) {
                entityManager.remove(entityManager.contains(t)? t: entityManager.merge(t));
                entityManager.flush();
        }
	
	@Transactional
	public Document createDocument(String name, String url, Task t) {
		Document d = new Document();
		d.setName(name);
		d.setUrl(url);
		d.setTask(t);

                t.addDocument(d);

		entityManager.persist(d);
		return d;
	}
	
	@Transactional
	public Document getDocument(String url) {
		Document doc = entityManager.find(Document.class, url);
		return doc;
	}

        @Transactional
        public void removeDocument(Document d) {
                entityManager.remove(entityManager.contains(d)? d: entityManager.merge(d));
                entityManager.flush();
        }
	

}
