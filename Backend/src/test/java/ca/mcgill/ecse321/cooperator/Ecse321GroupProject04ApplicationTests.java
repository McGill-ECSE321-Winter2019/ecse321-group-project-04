package ca.mcgill.ecse321.cooperator;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.dao.Ecse321GroupProject04ApplicationRepsitory;
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

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Ecse321GroupProject04ApplicationTests {

    // Employer
    private static final String EMPLOYER_NAME = "Google";
    private static final String EMPLOYER_EMAIL = "google@gmail.com";
    // Course
    private static final String COURSE_CODE = "ECSE3XX";
    private static final Integer COURSE_TERM = 2;
    // Student
    private static final String STUDENT_FIRST_NAME = "first name";
    private static final String STUDENT_LAST_NAME = "last name";
    private static final Integer STUDENT_ID = 12345678;
    private static final String STUDENT_EMAIL = "name@mail.mcgill.ca";
    // Co-op course Offering
    private static final Integer YEAR = 2018;
    private static final Term TERM = Term.FALL;
    private static final Boolean ACTIVE = true;
    private static final String OFFER_ID = "ECSE3XX-F2018";
    private static final StudentEnrollment SE = new StudentEnrollment();
    // Student Enrollment
    private static final Boolean ACTIVE_SE = true;
    private static final CourseStatus COURSE_STATUS = CourseStatus.PASSED;
    private static final String ENROLLMENT_ID = STUDENT_ID + "-" + OFFER_ID;
    // Document
    private static final String DOCUMENT_NAME = "some name";
    private static final String DOCUMENT_URL = "www.onedrive.com/...";
    private static final Task TASK = new Task();
    // Task
    private static final Date DATE = new Date(2019,1,1);
    private static final TaskStatus STATUS = TaskStatus.COMPLETED; 
    private static final String TASK_ID = "1234";

    @Autowired
    Ecse321GroupProject04ApplicationRepsitory dao;

    @Test
    public void testCreateEmployer() {
        // create an employer
        dao.createEmployer(EMPLOYER_NAME, EMPLOYER_EMAIL);
        // search for employer by email
        Employer e = dao.getEmployer(EMPLOYER_EMAIL);

        String name =e.getName();
        assertEquals(EMPLOYER_NAME, name);

        String email = e.getEmail();
        assertEquals(EMPLOYER_EMAIL, email);

        dao.removeEmployer(e);
    }

    @Test
    public void testCreateCoopCourse() {
        // create a Co-op Course
        dao.createCoopCourse(COURSE_CODE, COURSE_TERM);
        //search for co-op course by ID
        CoopCourse c = dao.getCoopCourse(COURSE_CODE);

        String code = c.getCourseCode();
        assertEquals(COURSE_CODE, code);

        Integer term = c.getCoopTerm();
        assertEquals(COURSE_TERM, term);

        dao.removeCoopCourse(c);
    }

    @Test
    public void testCreateStudent() {
        // create a student
        dao.createStudent("first_name", "last_name", 260112233, "student@email.com");
        // search for student by id 
        Student s = dao.getStudent(260112233);

        assertEquals("first_name", s.getFirstName());
        assertEquals("last_name", s.getLastName());
        assertEquals((Integer)260112233, s.getMcgillID());
        assertEquals("student@email.com", s.getMcgillEmail());

        dao.removeStudent(s);
    }

    /*The rest of the read/write tests need to be changed --classes with composition relations leave a null row in the table */

    @Test
    public void testCreateCoopCourseOffering() {
        // create Co-op course offering
        CoopCourse c = dao.createCoopCourse("ECSE301", 1);

        dao.createCoopCourseOffering(2018, Term.WINTER, true, c);
        // search for co-op course offering by ID
        CoopCourseOffering cco = dao.getCoopCourseOffering("ECSE301-W2018");

        assertEquals((Integer)2018, cco.getYear());
        assertEquals(Term.WINTER, cco.getTerm());
        assertEquals(true, cco.getActive());
        assertEquals("ECSE301-W2018", cco.getOfferID());

        dao.removeCoopCourseOffering(cco);
        dao.removeCoopCourse(c);
    }

    @Test
    public void testCreateStudentEnrollment() {
        CoopCourse c = dao.createCoopCourse("ECSE302", 1);
        CoopCourseOffering cco = dao.createCoopCourseOffering(2019, Term.FALL, true, c);
        Student s = dao.createStudent("f_name", "l_name", 260654321, "test@mail.com");
        Employer e = dao.createEmployer("Facebook", "fb@email.com");

        dao.createStudentEnrollment(s, e, true, CourseStatus.PASSED, "260123456-ECSE302-F2019", cco);

        StudentEnrollment se = dao.getStudentEnrollment("260123456-ECSE302-F2019");

        assertEquals(true, se.getActive());
        assertEquals("260123456-ECSE302-F2019", se.getEnrollmentID());
        assertEquals(CourseStatus.PASSED, se.getStatus());

        dao.removeStudentEnrollment(se);
        dao.removeCoopCourseOffering(cco);
        dao.removeCoopCourse(c);
        dao.removeEmployer(e);
        dao.removeStudent(s);
    }

    @Test
    public void testCreateTask() {
        // create a task
        Date dueDate =new Date(2019, 1, 1); 
        CoopCourse c = dao.createCoopCourse("ECSE302", 1);
        CoopCourseOffering cco = dao.createCoopCourseOffering(2019, Term.FALL, true, c);
        Student s = dao.createStudent("f_name", "l_name", 260654321, "test@mail.com");
        Employer e = dao.createEmployer("Facebook", "fb@email.com");

        StudentEnrollment se = dao.createStudentEnrollment(s, e, true, CourseStatus.PASSED, "260123456-ECSE302-F2019", cco);

        dao.createTask("Some description", dueDate , TaskStatus.COMPLETED, "1234", se);
        // search for Task by task ID
        Task t = dao.getTask(TASK_ID);

        assertEquals("Some description", t.getDescription());
        assertEquals(dueDate, t.getDueDate());
        assertEquals(TaskStatus.COMPLETED, t.getTaskStatus());
        assertEquals("1234", t.getTaskID());

        dao.removeTask(t);
        dao.removeStudentEnrollment(se);
        dao.removeCoopCourseOffering(cco);
        dao.removeCoopCourse(c);
        dao.removeEmployer(e);
        dao.removeStudent(s);
    }


    @Test
    public void testCreateDocument() {
        Date dueDate =new Date(2019, 1, 1); 
        CoopCourse c = dao.createCoopCourse("ECSE302", 1);
        CoopCourseOffering cco = dao.createCoopCourseOffering(2019, Term.FALL, true, c);
        Student s = dao.createStudent("f_name", "l_name", 260654321, "test@mail.com");
        Employer e = dao.createEmployer("Facebook", "fb@email.com");

        StudentEnrollment se = dao.createStudentEnrollment(s, e, true, CourseStatus.PASSED, "260123456-ECSE302-F2019", cco);

        Task t = dao.createTask("Some description", dueDate , TaskStatus.COMPLETED, "1234", se);

        dao.createDocument("doc name", "http://test-url.this/is/just/for/testing", t);
        // search for doc
        Document d = dao.getDocument("http://test-url.this/is/just/for/testing");

        assertEquals("doc name", d.getName());
        assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());

        dao.removeDocument(d);
        dao.removeTask(t);
        dao.removeStudentEnrollment(se);
        dao.removeCoopCourseOffering(cco);
        dao.removeCoopCourse(c);
        dao.removeEmployer(e);
        dao.removeStudent(s);
    }



}
