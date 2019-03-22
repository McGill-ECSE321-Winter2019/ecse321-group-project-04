package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface CoopCourseOfferingRepository extends CrudRepository<CoopCourseOffering, String> {

  CoopCourseOffering findByOfferID(@Param(value = "id") String id);

  // Disable default POST end point
  @SuppressWarnings("unchecked")
  @Override
  @RestResource(exported = false)
  CoopCourseOffering save(CoopCourseOffering cco);
}
