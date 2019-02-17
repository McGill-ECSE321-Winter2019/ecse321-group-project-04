package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Employer;

public interface EmployerRepository extends CrudRepository<Employer, String> {
	Employer findByEmail(String email);
}
