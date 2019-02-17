package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.Student;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends CrudRepository<Student, Integer> {
	Student findByMcgillID(Integer id);
}
