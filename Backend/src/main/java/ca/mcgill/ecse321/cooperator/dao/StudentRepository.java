package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	Student findStudentByMcgillID(Integer id);
}
