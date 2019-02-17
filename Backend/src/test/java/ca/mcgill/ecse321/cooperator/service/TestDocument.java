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

	/* Really messy, but I think it works? */
//	@Test
//	public void testReplaceDocument() {
//		@SuppressWarnings("deprecation")
//		Date dueDate = new Date(2019, 1, 1);
//		CoopCourse c = service.createCoopCourse("ECSE303", 1);
//		CoopCourseOffering cco = service.createCoopCourseOffering(2017, Term.FALL, true, c);
//		Student s = service.createStudent("f_name", "l_name", 260654322, "test@mail.com");
//		Employer emp = service.createEmployer("Facebook", "fb@email.ca");
//		StudentEnrollment se = service.createStudentEnrollment(true, CourseStatus.PASSED, s, emp, cco);
//		Task t = service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1235", se);
//		try {
//			service.createDocument("doc name", "http://test-url.this/is/just/for/testing", t);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//		Document oldDoc = service.getDocument("http://test-url.this/is/just/for/testing");
//		// check that old doc was persisted
//		assertEquals("doc name", oldDoc.getName());
//		assertEquals("http://test-url.this/is/just/for/testing", oldDoc.getUrl());
//		assertEquals(t.getTaskID(), oldDoc.getTask().getTaskID());
//
//		// create a new doc
//		Document newDoc = service.createDocument("doc name", "http://replacement/doc", t);
//
//		// delete the old doc and persist the new doc
//		service.replaceTaskDocument(t.getTaskID(), newDoc, oldDoc.getUrl());
//
//		// find the new doc assuming it should be associated with the same task ID
//		t = service.getTask("1235");
//		newDoc = service.getDocument("http://replacement/doc");
//
//		assertEquals(t.getTaskID(), newDoc.getTask().getTaskID()); // old task id same as task id associated with new
//																	// document
//		assertEquals(1, service.getAllDocuments().size()); // there should only be one doc since the other is deleted in
//															// the transaction
//	}

}
