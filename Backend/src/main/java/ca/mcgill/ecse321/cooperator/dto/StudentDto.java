package ca.mcgill.ecse321.cooperator.dto;

public class StudentDto {
	private String firstName;
	private String lastName;
	private Integer mcgillID;
	private String mcgillEmail;

	public StudentDto() {
	}

	public StudentDto(String firstName, String lastName, Integer mcgillID, String mcgillEmail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mcgillID = mcgillID;
		this.mcgillEmail = mcgillEmail;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Integer getMcgillID() {
		return this.mcgillID;
	}

	public String getMcgillEmail() {
		return this.mcgillEmail;
	}

}
