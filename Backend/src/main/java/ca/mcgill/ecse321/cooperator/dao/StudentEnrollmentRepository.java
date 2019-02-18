package ca.mcgill.ecse321.cooperator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;

@RepositoryRestResource(collectionResourceRel = "studentEnrollments", path = "studentEnrollments")
public interface StudentEnrollmentRepository extends CrudRepository<StudentEnrollment, String> {
	StudentEnrollment findByEnrollmentID(@Param(value = "id") String id);
	List<StudentEnrollment> findByStudentEmployer(@Param(value = "employerEmail") Employer employerEmail);
}
