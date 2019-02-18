package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;

@RepositoryRestResource(collectionResourceRel = "coop-courses", path = "coop-courses")
public interface CoopCourseRepository extends CrudRepository<CoopCourse, String> {
	CoopCourse findByCourseCode(String courseCode);
}
