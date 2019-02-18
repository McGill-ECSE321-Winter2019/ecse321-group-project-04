package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;

@RepositoryRestResource(collectionResourceRel = "coopCourses", path = "coopCourses")
public interface CoopCourseRepository extends CrudRepository<CoopCourse, String> {
	CoopCourse findByCourseCode(@Param(value = "courseCode") String courseCode);
}
