package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.CoopCourseRepository;
import ca.mcgill.ecse321.cooperator.model.CoopCourse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourse {
	@Autowired
	private CooperatorService service;

	@Autowired
	private CoopCourseRepository coopCourseRepository;

	@After
	public void cleanDataBase() {
		coopCourseRepository.deleteAll();
	}

	@Test
	public void testCreateCoopCourse() {
		CoopCourse param = new CoopCourse();
		param.setCourseCode("ECSE300");
		param.setCoopTerm(1);
		try {
			service.createCoopCourse(param);
		} catch (IllegalArgumentException e) {
			fail();
		}
		CoopCourse c = service.getCoopCourse("ECSE300");
		// Check attributes
		assertEquals("ECSE300", c.getCourseCode());
		assertEquals((Integer) 1, c.getCoopTerm());

		assertEquals(1, service.getAllCoopCourses().size());
	}
	
	@Test
	public void testCreateCoopCourseWithObject() {
		try {
			service.createCoopCourse("ECSE300", 1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		CoopCourse c = service.getCoopCourse("ECSE300");
		// Check attributes
		assertEquals("ECSE300", c.getCourseCode());
		assertEquals((Integer) 1, c.getCoopTerm());

		assertEquals(1, service.getAllCoopCourses().size());
	}

	@Test
	public void testCreateNullCodeCoopCourse() {
		String error = null;
		try {
			service.createCoopCourse(null, 1);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourses().size());
	}
	
	public void testCreateNullTermCoopCourse() {
		String error = null;
		try {
			service.createCoopCourse("ECSE300", null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourses().size());
	}
	
	@Test
	public void testCreateNegativeTermCoopCourse() {
		String error = null;
		try {
			service.createCoopCourse("ECSE300", -5);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your course details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllCoopCourses().size());
	}
	
	@Test
	public void testGetNonexistentCoopCourse() {
		String error = null;
		try {
			service.getCoopCourse("ECSE300");
		} catch (EntityNotFoundException e){
			error = e.getMessage();
		}
		
		assertEquals(error, "Could not find a CO-OP Course with ID ECSE300");
	}
}
