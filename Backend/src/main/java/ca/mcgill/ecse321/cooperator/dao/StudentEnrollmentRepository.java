package ca.mcgill.ecse321.cooperator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;

@RepositoryRestResource(collectionResourceRel = "enrollments", path = "enrollments")
public interface StudentEnrollmentRepository extends CrudRepository<StudentEnrollment, String> {
	StudentEnrollment findByEnrollmentID(String id);
	List<StudentEnrollment> findByStudentEmployer(Employer employerEmail);
}
