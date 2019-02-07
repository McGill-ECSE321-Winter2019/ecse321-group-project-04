package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;

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
        Student s = studentRepository.findStudentByMcgillID(id);
        return s;
    }
    
    /*--- EMPLOYER METHODS ---*/
    @Transactional
    public Employer createEmployer(String name, String email) {
        Employer e = new Employer();
        e.setName(name);
        e.setEmail(email);
        employerRepository.save(e);
        return e;
    }

    @Transactional
    public Employer getEmployer(String email) {
        Employer e = employerRepository.findEmloyerByEmail(email);
        return e;
    }
    
    /*--- COOP COURSE METHODS ---*/
    @Transactional
    public CoopCourse createCoopCourse(String courseCode, Integer coopTerm) {
        CoopCourse c = new CoopCourse();
        c.setCourseCode(courseCode);
        c.setCoopTerm(coopTerm);
        coopCourseRepository.save(c);
        return c;
    }

    @Transactional
    public CoopCourse getCoopCourse(String coopCourseID) {
        CoopCourse c  = coopCourseRepository.findCoopCourseByCourseCode(coopCourseID);
        return c;
    }

    /*--- COOP COURSE OFFERING METHODS ---*/
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
            offerID += year % 2000;
            cco.setOfferID(offerID);
            coopCourseOfferingRepository.save(cco);
            return cco;
    }

    @Transactional
    public CoopCourseOffering getCoopCourseOffering(String offerID) {
        CoopCourseOffering cco = coopCourseOfferingRepository.findCoopCourseOfferingByOfferID(offerID);
        return cco;
    }
    
    /*--- STUDENT ENROLLMENT METHODS ---*/
    @Transactional
    public StudentEnrollment createStudentEnrollment(Boolean active, CourseStatus status, Student s, Employer e, CoopCourseOffering cco) {
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
        StudentEnrollment se = studentEnrollmentRepository.findStudentEnrollmentByEnrollmentID(id);
        return se;
    }
	
    /*--- TASK METHODS ---*/
    @Transactional
    public Task createTask(String description, Date dueDate, TaskStatus status, String taskID, StudentEnrollment se) {
        Task t = new Task();
        t.setDescription(description);
        t.setDueDate(dueDate);
        t.setTaskStatus(status);
        t.setTaskID(taskID);
        t.setStudentEnrollment(se);

        se.addCourseTask(t);

        taskRepository.save(t);
        return t;
    }

    @Transactional
    public Task getTask(String taskID) {
        Task t  = taskRepository.findTaskByTaskID(taskID);
        return t;
    }
    
    /*--- DOCUMENT METHODS ---*/
    @Transactional
    public Document createDocument(String name, String url, Task t) {
        Document d = new Document();
        d.setName(name);
        d.setUrl(url);
        d.setTask(t);

        t.addDocument(d);
        documentRepository.save(d);
        return d;
    }

    @Transactional
    public Document getDocument(String url) {
        Document doc = documentRepository.findDocumentByUrl(url);
        return doc;
    }

}
