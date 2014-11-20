/*
package com.runMyErrand.controllers.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runMyErrand.services.TaskServices;

public class TaskServicesTest {
	
	TaskServices taskservicestest;

	@Before
	public void setUp() throws Exception {
		
		taskservicestest = new TaskServices();
	
	}

	@Test
	public void testretrieveMyTasks() {
		//fail("Not yet implemented");
		List list_tasktest = taskservicestest.retrieveMyTasks("jmmodi@indiana.edu") ;
		ArrayList<String> expectedlist_tasktest = new ArrayList<String>();
		expectedlist_tasktest.add("Cooking");
		assertEquals(expectedlist_tasktest,list_tasktest);
		}
	
	@Test
	public void testretriveAllTasks()
	{
		List list_alltasktest=taskservicestest.retriveAllTasks("910N");
		ArrayList<String> expectedlist_alltasktest = new ArrayList<String>();
		expectedlist_alltasktest.add("Cooking");
		expectedlist_alltasktest.add("Vacuum Cleaning");
		expectedlist_alltasktest.add("Trash");
		expectedlist_alltasktest.add("Clean the coffee pot");
		expectedlist_alltasktest.add("Pay the utilities bill");
		expectedlist_alltasktest.add("Pay the Rent");
		expectedlist_alltasktest.add("dfebvrfbv");
		assertEquals(expectedlist_alltasktest,list_alltasktest);
	}
	
	@Test
	public void testretrieveUnassignedTasks()
	{
		List list_retrieveUnassignedTasks = TaskServices.retrieveUnassignedTasks("910N");
		ArrayList<String> expectedlist_UnassignedTaskstest = new ArrayList<String>();
		expectedlist_UnassignedTaskstest.add("Trash");
		expectedlist_UnassignedTaskstest.add("Pay the Rent");
		expectedlist_UnassignedTaskstest.add("dfebvrfbv");
		assertEquals(expectedlist_UnassignedTaskstest,list_retrieveUnassignedTasks);
	}

}
*/