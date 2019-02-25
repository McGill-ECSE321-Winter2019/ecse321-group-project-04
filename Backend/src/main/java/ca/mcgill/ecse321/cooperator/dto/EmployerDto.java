package ca.mcgill.ecse321.cooperator.dto;

public class EmployerDto {

	private String name;
	private String email;

	public EmployerDto() {
	}

	public EmployerDto(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

}
