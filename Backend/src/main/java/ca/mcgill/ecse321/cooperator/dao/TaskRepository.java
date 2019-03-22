package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ca.mcgill.ecse321.cooperator.model.Task;

@CrossOrigin(origins = "*")
public interface TaskRepository extends CrudRepository<Task, Long> {
  Task findByTaskID(@Param(value = "id") Long id);

  // Disable default POST end point
  @SuppressWarnings("unchecked")
  @Override
  @RestResource(exported = false)
  Task save(Task task);
}
