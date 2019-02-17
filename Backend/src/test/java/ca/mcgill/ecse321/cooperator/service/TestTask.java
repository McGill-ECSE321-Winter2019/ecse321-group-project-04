package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.TaskRepository;
import ca.mcgill.ecse321.cooperator.model.Task;
import ca.mcgill.ecse321.cooperator.model.TaskStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTask {
	@Autowired
	private CooperatorService service;
	@Autowired
	private TaskRepository taskRepository;

	@After
	public void cleanDataBase() {
		taskRepository.deleteAll();
	}

	@Test
	public void testCreateTask() {
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		try {
			service.createTask("Some description", dueDate, TaskStatus.COMPLETED, "1234");
		} catch (IllegalArgumentException e) {
			fail();
		}
		Task t = service.getTask("1234");
		// check attributes
		assertEquals("Some description", t.getDescription());
		assertEquals(dueDate, t.getDueDate());
		assertEquals(TaskStatus.COMPLETED, t.getTaskStatus());
		assertEquals("1234", t.getTaskID());

		assertEquals(1, service.getAllTasks().size());
	}

	@Test
	public void testCreateNullDescriptionTask() {
		String error = null;
		@SuppressWarnings("deprecation")
		Date dueDate = new Date(2019, 1, 1);

		try {
			service.createTask(null, dueDate, TaskStatus.COMPLETED, "1234");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error message
		assertEquals("Your task details are incomplete!", error);
		// check nothing was added
		assertEquals(0, service.getAllTasks().size());

	}
}
