package ca.mcgill.ecse321.cooperator.dao;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
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
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;
import ca.mcgill.ecse321.cooperator.model.Term;

@Repository
public class Ecse321GroupProject04ApplicationRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;

    /*--- STUDENT METHODS ---*/
    @Transactional
    public Student createStudent(String firstName, String lastName, Integer id, String email) {
        Student s = new Student();
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setMcgillID(id);
        s.setMcgillEmail(email);

        em.persist(s);
        return s;
    }

    @Transactional
    public Student getStudent(Integer id) {
        Student s = em.find(Student.class, id);
        return s;
    }

    @Transactional
    public void removeStudent(Student s) {
        em.remove(em.contains(s)? s: em.merge(s));
        em.flush();
    }

    /*--- EMPLOYER METHODS ---*/
    @Transactional
    public Employer createEmployer(String name, String email) {
        Employer e = new Employer();
        e.setName(name);
        e.setEmail(email);
        em.persist(e);
        return e;
    }

    @Transactional
    public Employer getEmployer(String email) {
        Employer e  = em.find(Employer.class, email);
        return e;
    }

    @Transactional
    public void removeEmployer(Employer employer) {
        em.remove(em.contains(employer)? employer: em.merge(employer));
        em.flush();
    }

    /*--- COOP COURSE METHODS ---*/
    @Transactional
    public CoopCourse createCoopCourse(String courseCode, Integer coopTerm) {
        CoopCourse c = new CoopCourse();
        c.setCourseCode(courseCode);
        c.setCoopTerm(coopTerm);
        em.persist(c);
        return c;
    }

    @Transactional
    public CoopCourse getCoopCourse(String coopCourseID) {
        CoopCourse c  = em.find(CoopCourse.class, coopCourseID);
        return c;
    }

    @Transactional
    public void removeCoopCourse(CoopCourse c) {
        em.remove(em.contains(c)? c: em.merge(c));
        em.flush();
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
            em.persist(cco);
            return cco;
    }

    @Transactional
    public CoopCourseOffering getCoopCourseOffering(String offerID) {
        CoopCourseOffering cco = em.find(CoopCourseOffering.class, offerID);
        return cco;
    }

    @Transactional
    public void removeCoopCourseOffering(CoopCourseOffering cco) {
        em.remove(em.contains(cco)? cco: em.merge(cco));
        em.flush();
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

        em.persist(se);
        return se;
    }

    @Transactional
    public StudentEnrollment getStudentEnrollment(String id) {
        StudentEnrollment se = em.find(StudentEnrollment.class, id);
        return se;
    }

    @Transactional
    public void removeStudentEnrollment(StudentEnrollment se) {
        em.remove(em.contains(se)? se: em.merge(se));
        em.flush();
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

        em.persist(t);
        return t;
    }

    @Transactional
    public Task getTask(String taskID) {
        Task t  = em.find(Task.class, taskID);
        return t;
    }

    @Transactional
    public void removeTask(Task t) {
        em.remove(em.contains(t)? t: em.merge(t));
        em.flush();
    }

    /*--- DOCUMENT METHODS ---*/
    @Transactional
    public Document createDocument(String name, String url, Task t) {
        Document d = new Document();
        d.setName(name);
        d.setUrl(url);
        d.setTask(t);

        t.addDocument(d);

        em.persist(d);
        return d;
    }

    @Transactional
    public Document getDocument(String url) {
        Document doc = em.find(Document.class, url);
        return doc;
    }

    @Transactional
    public void removeDocument(Document d) {
        em.remove(em.contains(d)? d: em.merge(d));
        em.flush();
    }


}
