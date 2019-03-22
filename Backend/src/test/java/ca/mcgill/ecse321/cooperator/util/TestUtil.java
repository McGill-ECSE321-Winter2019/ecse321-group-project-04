package ca.mcgill.ecse321.cooperator.util;

import java.sql.Date;
import java.util.Calendar;
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

public class TestUtil {

  // Constants used to populate a new StudentEnrollment
  private static final String T1_NAME = "Report CO-OP Position Acceptance";
  private static final String T2_NAME = "Upload Employer Contract";
  private static final String T3_NAME = "Initial Workload Report";
  private static final String T4_NAME = "Technical Experience Report";
  private static final String T5_NAME = "Internship Evaluation Report";
  private static final String T1_DESC = "Submit the CO-OP position acceptance form.";
  private static final String T2_DESC = "Submit the employer contract document.";
  private static final String T3_DESC =
      "Submit an initial report of the tasks and workload of the internship.";
  private static final String T4_DESC =
      "Submit the term technical report about the internship experience.";
  private static final String T5_DESC =
      "Submit the final evaluation report for the internship experience.";

  private static final String D1_NAME = "CO-OP Position Acceptance Form";
  private static final String D2_NAME = "Employer Contract";

  public static Student createStudent(String firstName, String lastName, Integer mcgillID,
      String mcgillEmail) {
    Student s = new Student();
    s.setFirstName(firstName);
    s.setLastName(lastName);
    s.setMcgillID(mcgillID);
    s.setMcgillEmail(mcgillEmail);
    return s;
  }

  public static Employer createEmployer(String name, String email, String address) {
    Employer e = new Employer();
    e.setName(name);
    e.setEmail(email);
    e.setAddress(address);
    return e;
  }

  public static CoopCourse createCoopCourse(String courseCode, Integer coopTerm) {
    CoopCourse cc = new CoopCourse();
    cc.setCourseCode(courseCode);
    cc.setCoopTerm(coopTerm);
    return cc;
  }

  public static CoopCourseOffering createCoopCourseOffering(Integer year, Term term, boolean active,
      CoopCourse cc) {
    CoopCourseOffering cco = new CoopCourseOffering();
    cco.setYear(year);
    cco.setTerm(term);
    cco.setActive(active);
    cco.setCoopCourse(cc);
    cco.setOfferID();
    return cco;
  }

  public static StudentEnrollment createStudentEnrollment(Boolean active, CourseStatus status,
      Student s, Employer e, CoopCourseOffering cco, String d1URL, String d2URL, Date startDate,
      Date endDate, Boolean workPermit, String jobID) {
    StudentEnrollment se = new StudentEnrollment();
    se.setEnrolledStudent(s);
    se.setStudentEmployer(e);
    se.setActive(active);
    se.setStatus(status);
    se.setEnrollmentID(s, cco);
    se.setCoopCourseOffering(cco);
    se.setStartDate(startDate);
    se.setEndDate(endDate);
    se.setWorkPermit(workPermit);
    se.setJobID(jobID);

    // Populate the enrollment
    Calendar currentCal = Calendar.getInstance();
    Calendar dateInTwoWeeks = Calendar.getInstance();
    dateInTwoWeeks.add(Calendar.DAY_OF_MONTH, +14);
    Calendar dateInFourMonths = Calendar.getInstance();
    dateInFourMonths.add(Calendar.MONTH, +4);

    Task t1 =
        createTask(T1_NAME, T1_DESC, new Date(currentCal.getTimeInMillis()), TaskStatus.COMPLETED);
    Task t2 =
        createTask(T2_NAME, T2_DESC, new Date(currentCal.getTimeInMillis()), TaskStatus.COMPLETED);
    Task t3 = createTask(T3_NAME, T3_DESC, new Date(dateInTwoWeeks.getTimeInMillis()),
        TaskStatus.COMPLETED);
    Task t4 = createTask(T4_NAME, T4_DESC, new Date(dateInFourMonths.getTimeInMillis()),
        TaskStatus.COMPLETED);
    Task t5 = createTask(T5_NAME, T5_DESC, new Date(dateInFourMonths.getTimeInMillis()),
        TaskStatus.COMPLETED);

    Document d1 = createDocument(D1_NAME, d1URL);
    Document d2 = createDocument(D2_NAME, d2URL);
    t1.addDocument(d1);
    t2.addDocument(d2);

    se.addCourseTasks(t1);
    se.addCourseTasks(t2);
    se.addCourseTasks(t3);
    se.addCourseTasks(t4);
    se.addCourseTasks(t5);
    return se;
  }

  public static Task createTask(String name, String description, Date dueDate, TaskStatus status) {
    Task t = new Task();
    t.setName(name);
    t.setDescription(description);
    t.setDueDate(dueDate);
    t.setTaskStatus(status);
    return t;
  }

  public static Document createDocument(String name, String url) {
    Calendar currentCal = Calendar.getInstance();
    Date currentDate = new Date(currentCal.getTimeInMillis());
    Document d = new Document();
    d.setName(name);
    d.setUrl(url);
    d.setSubmissionDate(currentDate);
    return d;
  }

}
