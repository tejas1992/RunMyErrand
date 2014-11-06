package com.runMyErrand.controllers.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runMyErrand.controllers.TaskController;
import com.runMyErrand.model.UserInfo;

public class TaskControllerTest {

	static TaskController taskcontroller=new TaskController();
	HttpSession session ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		taskcontroller=null;
	}

	/*@Test
	public void testAssigntask() {
		//fail("Not yet implemented");
		System.out.println("in test assign task");
		String assignedto = "pp";
		String task = "task";
		HttpSession session = null ;
		assert taskcontroller.assigntask(task, assignedto, session)== null:"return object not null";
		
	}*/
	@Test
	public void testeditMyTask()
	{
		System.out.println("editask test");
		assert taskcontroller.editMyTask("oo", "pp", session)==null:"object is null";
		
		
	}

}
