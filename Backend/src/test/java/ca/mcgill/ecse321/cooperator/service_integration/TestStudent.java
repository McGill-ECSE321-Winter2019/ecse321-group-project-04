package ca.mcgill.ecse321.cooperator.service_integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.requesthandler.InvalidParameterException;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudent {
    @Autowired
    private CooperatorService service;

    @Autowired
    private StudentRepository studentRepository;

    @Before
    @After
    public void cleanDataBase() {
        studentRepository.deleteAll();
    }

    @Test
    public void testCreateStudent() {
        // Create and persist a student
        try {
            service.createStudent("first_name", "last_name", 260112233, "student@email.com");
        } catch (InvalidParameterException e) {
            fail();
        }
        // find the student by id
        Student s = service.getStudent(260112233);
        // Check attributes
        assertEquals("first_name", s.getFirstName());
        assertEquals("last_name", s.getLastName());
        assertEquals((Integer) 260112233, s.getMcgillID());
        assertEquals("student@email.com", s.getMcgillEmail());

        assertEquals(1, service.getAllStudents().size());
    }
    
    @Test
    public void testCreateStudentWithObject() {
        // Create and persist a student
        Student param = new Student();
        param.setFirstName("first_name");
        param.setLastName("last_name");
        param.setMcgillID(260112233);
        param.setMcgillEmail("student@email.com");
        try {
            service.createStudent(param);
        } catch (InvalidParameterException e) {
            fail();
        }
        // find the student by id
        Student s = service.getStudent(260112233);
        // Check attributes
        assertEquals("first_name", s.getFirstName());
        assertEquals("last_name", s.getLastName());
        assertEquals((Integer) 260112233, s.getMcgillID());
        assertEquals("student@email.com", s.getMcgillEmail());

        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    public void testCreateNullNameStudent() {
        String error = null;
        try {
            service.createStudent(null, "last_name", 260112233, "student@email.com");
        } catch (InvalidParameterException e) {
            error = e.getMessage();
        }
        // check error message
        assertEquals("Your student details are incomplete!", error);
        // check nothing was added
        assertEquals(0, service.getAllStudents().size());
    }

    @Test
    public void testCreateEmptyNameStudent() {
        String error = null;
        try {
            service.createStudent(" ", "last_name", 260112233, "student@email.com");
        } catch (InvalidParameterException e) {
            error = e.getMessage();
        }
        // check error message
        assertEquals("Your student details are incomplete!", error);

        assertEquals(0, service.getAllStudents().size());
    }

    @Test
    public void testCreateNullIDStudent() {
        String error = null;
        try {
            service.createStudent("first_name", "last_name", null, "student@email.com");
        } catch (InvalidParameterException e) {
            error = e.getMessage();
        }
        // check error message
        assertEquals("Your student details are incomplete!", error);
        // check nothing was added
        assertEquals(0, service.getAllStudents().size());
    }

    @Test
    public void testCreateNullEmailStudent() {
        String error = null;
        try {
            service.createStudent("first_name", "last_name", 260112233, null);
        } catch (InvalidParameterException e) {
            error = e.getMessage();
        }
        // check error message
        assertEquals("Your student details are incomplete!", error);
        // check nothing was added
        assertEquals(0, service.getAllStudents().size());
    }
    
    @Test
    public void testGetNonexistentStudent() {
        String error = null;
        try {
            service.getStudent(260112233);
        } catch (EntityNotFoundException e){
            error = e.getMessage();
        }
        
        assertEquals(error, "Could not find a Student with ID 260112233");
    }

}
