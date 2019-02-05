package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.HashSet;

@Entity
public class StudentEnrollment {
	private Boolean active;

	public void setActive(Boolean value) {
		this.active = value;
	}

	public Boolean getActive() {
		return this.active;
	}

	private CourseStatus status;

	public void setStatus(CourseStatus value) {
		this.status = value;
	}

	public CourseStatus getStatus() {
		return this.status;
	}

	private Employer studentEmployer;

	@ManyToOne(optional = false)
	public Employer getStudentEmployer() {
		return this.studentEmployer;
	}

	public void setStudentEmployer(Employer studentEmployer) {
		this.studentEmployer = studentEmployer;
	}

	private Student enrolledStudent;

	@ManyToOne(optional = false)
	public Student getEnrolledStudent() {
		return this.enrolledStudent;
	}

	public void setEnrolledStudent(Student enrolledStudent) {
		this.enrolledStudent = enrolledStudent;
	}

	private Set<Task> courseTasks;

	@OneToMany(mappedBy = "studentEnrollment", cascade = { CascadeType.ALL })
	public Set<Task> getCourseTasks() {
		return this.courseTasks;
	}

	public void setCourseTasks(Set<Task> courseTasks) {
		this.courseTasks = courseTasks;
	}

        public void addCourseTask(Task task) {
                if (courseTasks == null)
                    courseTasks = new HashSet<Task>();
                courseTasks.add(task);
        }

	private CoopCourseOffering coopCourseOffering;

	@ManyToOne(optional = false)
	public CoopCourseOffering getCoopCourseOffering() {
		return this.coopCourseOffering;
	}

	public void setCoopCourseOffering(CoopCourseOffering coopCourseOffering) {
		this.coopCourseOffering = coopCourseOffering;
	}

	private String enrollmentID;

	public void setEnrollmentID(String value) {
		this.enrollmentID = value;
	}

	@Id
	public String getEnrollmentID() {
		return this.enrollmentID;
	}
}
