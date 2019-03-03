package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ca.mcgill.ecse321.cooperator.model.Document;

// @RepositoryRestResource(collectionResourceRel = "documents", path = "documents")
public interface DocumentRepository extends CrudRepository<Document, Long> {
  Document findByUrl(@Param(value = "url") String url);

  @SuppressWarnings("unchecked")
  @Override
  @RestResource(exported = false)
  Document save(Document doc);
}
