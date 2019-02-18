package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.Employer;

@RepositoryRestResource(collectionResourceRel = "employers", path = "employers")
public interface EmployerRepository extends CrudRepository<Employer, String> {
	Employer findByEmail(@Param(value = "email") String email);
}
