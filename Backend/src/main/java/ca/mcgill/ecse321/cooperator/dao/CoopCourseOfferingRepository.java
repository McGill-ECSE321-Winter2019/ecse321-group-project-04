package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;

//@RepositoryRestResource(collectionResourceRel = "coop-course-offerings", path = "coop-course-offerings")
public interface CoopCourseOfferingRepository extends CrudRepository<CoopCourseOffering, String> {

	CoopCourseOffering findByOfferID(@Param(value = "id") String id);
	@SuppressWarnings("unchecked")
	@Override
	@RestResource(exported = false)
	CoopCourseOffering save(CoopCourseOffering cco);
}
