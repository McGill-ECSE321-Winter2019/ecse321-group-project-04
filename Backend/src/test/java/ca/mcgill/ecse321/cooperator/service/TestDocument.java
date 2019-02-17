package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.DocumentRepository;
import ca.mcgill.ecse321.cooperator.model.Document;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDocument {
	@Autowired
	private CooperatorService service;
	@Autowired
	private DocumentRepository documentRepository;

	@After
	public void cleanDataBase() {
		documentRepository.deleteAll();
	}

	@Test
	public void testCreateDocument() {
		try {
			service.createDocument("doc name", "http://test-url.this/is/just/for/testing");
		} catch (IllegalArgumentException e) {
			fail();
		}
		Document d = service.getDocument("http://test-url.this/is/just/for/testing");

		assertEquals("doc name", d.getName());
		assertEquals("http://test-url.this/is/just/for/testing", d.getUrl());

		assertEquals(1, service.getAllDocuments().size());
	}

	@Test
	public void testCreateNullNameDocument() {
		String error = null;

		try {
			service.createDocument(null, "http://test-url.this/is/just/for/testing");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your document details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllDocuments().size());
	}

}
