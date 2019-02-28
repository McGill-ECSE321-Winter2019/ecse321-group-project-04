package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;

//@RepositoryRestResource(collectionResourceRel = "coopCourses", path = "coopCourses")
public interface CoopCourseRepository extends CrudRepository<CoopCourse, String> {
	CoopCourse findByCourseCode(@Param(value = "courseCode") String courseCode);
	@SuppressWarnings("unchecked")
	@Override
	@RestResource(exported = false)
	CoopCourse save(CoopCourse c);
}
