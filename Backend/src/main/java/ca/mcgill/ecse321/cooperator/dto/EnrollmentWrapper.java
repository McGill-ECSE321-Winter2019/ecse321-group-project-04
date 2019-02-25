package ca.mcgill.ecse321.cooperator.dto;

public class EnrollmentWrapper {
	private StudentDto student;
	private EmployerDto employer;
	private CoopCourseOfferingDto cco;

	public CoopCourseOfferingDto getCco() {
		return cco;
	}

	public void setCco(CoopCourseOfferingDto cco) {
		this.cco = cco;
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public EmployerDto getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerDto employer) {
		this.employer = employer;
	}

}
