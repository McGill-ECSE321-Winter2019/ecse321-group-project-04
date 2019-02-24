package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.CoopCourseOfferingRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;
import ca.mcgill.ecse321.cooperator.model.CoopCourseOffering;
import ca.mcgill.ecse321.cooperator.model.Term;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseOffering {

	@Autowired
	private CooperatorService service;
	@Autowired
	private CoopCourseRepository coopCourseRepository;
	@Autowired
	private CoopCourseOfferingRepository coopCourseOfferingRepository;

	@After
	public void cleanDataBase() {
		coopCourseOfferingRepository.deleteAll();
		coopCourseRepository.deleteAll();
	}

	@Test
	public void testCreateCoopCourseOffering() {
		CoopCourse c = service.createCoopCourse("ECSE301", 1);

		try {
			service.createCoopCourseOffering(2018, Term.WINTER, true, c);
		} catch (IllegalArgumentException e) {
			fail();
		}

		CoopCourseOffering cco = service.getCoopCourseOffering("ECSE301-W18");
		// Check attributes
		assertEquals((Integer) 2018, cco.getYear());
		assertEquals(Term.WINTER, cco.getTerm());
		assertEquals(true, cco.getActive());
		assertEquals("ECSE301-W18", cco.getOfferID());
		// Check references
		assertEquals("ECSE301", cco.getCoopCourse().getCourseCode());
		assertEquals(1, service.getAllCoopCourseOfferings().size());
	}

	@Test
	public void testCreateNullYearCoopCourseOffering() {
		CoopCourse c = service.createCoopCourse("ECSE301", 1);
		String error = null;
		try {
			service.createCoopCourseOffering(null, Term.WINTER, true, c);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course offering details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourseOfferings().size());
	}

	@Test
	public void testCreateNullTermCoopCourseOffering() {
		CoopCourse c = service.createCoopCourse("ECSE301", 1);
		String error = null;
		try {
			service.createCoopCourseOffering(2018, null, true, c);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course offering details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourseOfferings().size());
	}

	@Test
	public void testCreateNullActiveCoopCourseOffering() {
		CoopCourse c = service.createCoopCourse("ECSE301", 1);
		String error = null;
		try {
			service.createCoopCourseOffering(2018, Term.WINTER, null, c);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course offering details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourseOfferings().size());
	}
}
