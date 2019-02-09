package ca.mcgill.ecse321.cooperator;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
public class CooperatorApplicationTests {

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
    
    private  CoopCourse c;
    
    @Test
    public void contextLoads() {

    }

}
