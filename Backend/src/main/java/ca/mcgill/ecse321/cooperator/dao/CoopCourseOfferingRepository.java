package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;

//@RepositoryRestResource(collectionResourceRel = "", path = "")
public interface CoopCourseOfferingRepository extends CrudRepository<CoopCourseOffering, String> {

	CoopCourseOffering findByOfferID(String id);
}
