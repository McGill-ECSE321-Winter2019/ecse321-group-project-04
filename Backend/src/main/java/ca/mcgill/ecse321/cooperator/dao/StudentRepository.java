package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ca.mcgill.ecse321.cooperator.model.Student;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface StudentRepository extends CrudRepository<Student, Integer> {

  Student findByMcgillID(@Param(value = "id") Integer id);
  Iterable<Student> findAll();

  // Disable default POST end point
  @Override
  @SuppressWarnings("unchecked")
  @RestResource(exported = false)
  Student save(Student student);
}
