package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;

public interface StudentEnrollmentRepository extends CrudRepository<StudentEnrollment, String> {
	StudentEnrollment findStudentEnrollmentByEnrollmentID(String id);
}
