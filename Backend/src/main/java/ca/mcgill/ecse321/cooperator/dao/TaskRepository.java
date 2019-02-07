package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Task;

public interface TaskRepository extends CrudRepository<Task, String> {
	Task findTaskByTaskID(String id);
}
