package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Document;

public interface DocumentRepository extends CrudRepository<Document, String> {
	Document findByUrl(String url);
}
