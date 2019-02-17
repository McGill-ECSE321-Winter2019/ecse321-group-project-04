package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;


public interface CoopCourseOfferingRepository extends CrudRepository<CoopCourseOffering, String> {

	CoopCourseOffering findByOfferID(String id);
}
