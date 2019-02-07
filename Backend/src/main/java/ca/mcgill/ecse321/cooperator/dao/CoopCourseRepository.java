package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CoopCourse;

public interface CoopCourseRepository extends CrudRepository<CoopCourse, String> {
	CoopCourse findCoopCourseByCourseCode(String courseCode);
}
