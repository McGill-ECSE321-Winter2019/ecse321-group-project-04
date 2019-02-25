package ca.mcgill.ecse321.cooperator.dto;


public class CoopCourseDto {
	private String courseCode;
	private Integer coopTerm;

	public CoopCourseDto() {
	}

	public CoopCourseDto(String courseCode, Integer coopTerm) {
		this.courseCode = courseCode;
		this.coopTerm = coopTerm;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public Integer getCoopTerm() {
		return this.coopTerm;
	}

}
