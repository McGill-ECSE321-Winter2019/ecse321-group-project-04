package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ca.mcgill.ecse321.cooperator.model.Employer;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface EmployerRepository extends CrudRepository<Employer, String> {
  Employer findByEmail(@Param(value = "email") String email);

  Employer findByName(@Param(value = "name") String name);

  // Disable default POST end point
  @SuppressWarnings("unchecked")
  @Override
  @RestResource(exported = false)
  Employer save(Employer emp);
}
