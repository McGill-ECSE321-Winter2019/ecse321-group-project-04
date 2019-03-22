package ca.mcgill.ecse321.cooperator.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.StudentEnrollment;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface StudentEnrollmentRepository extends CrudRepository<StudentEnrollment, String> {
  StudentEnrollment findByEnrollmentID(@Param(value = "id") String id);

  List<StudentEnrollment> findByStudentEmployer(
      @Param(value = "employerEmail") Employer employerEmail);

  // Disable default POST end point
  @SuppressWarnings("unchecked")
  @Override
  @RestResource(exported = false)
  StudentEnrollment save(StudentEnrollment se);
}
