package com.runMyErrand.controllers.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.runMyErrand.dao.TaskDao;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.services.TaskServices;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TaskDao.class)
public class TestTaskServices {

	TaskServices taskservicestest;
	@Mock
	TaskDao taskdao;
	
	ArrayList<TaskInfo> newList = new ArrayList<TaskInfo>();
	ArrayList<TaskInfo> expected = new ArrayList<TaskInfo>();

	@Before
	public void setUp() throws Exception {
		taskservicestest = new TaskServices();
		PowerMockito.mockStatic(TaskDao.class);
	}

	@Test
	public void testRetriveAllTasks() {
		TaskInfo taskinfo = new TaskInfo ();
		taskinfo.setTaskDescription("clean room");
		newList.add(taskinfo);
		TaskServices.setTaskDao(taskdao);
		PowerMockito.when(TaskDao.selectAll("910A")).thenReturn(newList);
		expected = (ArrayList<TaskInfo>) TaskServices.retriveAllTasks("910A");
		TaskInfo taskinfo2 = new TaskInfo();
		taskinfo2 = expected.get(0);
		String taskdescription = taskinfo2.getTaskDescription();
		assertEquals(taskdescription , "clean room");

	}

	@Test
	public void testRetrieveMyTasks() {
		TaskInfo taskinfo = new TaskInfo ();
		taskinfo.setTaskDescription("clean room");
		newList.add(taskinfo);		
		TaskServices.setTaskDao(taskdao);
	    PowerMockito.when(taskdao.selectAssigned("abhino@gmail.com")).thenReturn(newList);		
		expected = (ArrayList<TaskInfo>) TaskServices.retrieveMyTasks("abhino@gmail.com");
		TaskInfo taskinfo2 = new TaskInfo();
		taskinfo2 = expected.get(0);
		String taskdescription = taskinfo2.getTaskDescription();
		assertEquals(taskdescription , "clean room");	
	}

	@Test
	public void testRetrieveUnassignedTasks() {
		TaskInfo taskinfo = new TaskInfo ();
		taskinfo.setTaskDescription("clean room");
		newList.add(taskinfo);		
		TaskServices.setTaskDao(taskdao);
	    PowerMockito.when(taskdao.selectUnAssigned("910A")).thenReturn(newList);		
		expected = (ArrayList<TaskInfo>) TaskServices.retrieveUnassignedTasks("910A");
		TaskInfo taskinfo2 = new TaskInfo();
		taskinfo2 = expected.get(0);
		String taskdescription = taskinfo2.getTaskDescription();
		assertEquals(taskdescription , "clean room");

	}

	@Test
	public void testAssignTask() {
		PowerMockito.doNothing().when(taskdao).updateTaskAssignedto(1, "abhino@gmail.com", "910A");
		
	}

	@Test
	public void testAddTask() {
		TaskInfo task = new TaskInfo();
		PowerMockito.doNothing().when(taskdao).insertTask(task, "910A");

	}

	@Test
	public void testUpdateTaskStatus() {
		PowerMockito.doNothing().when(taskdao).updateTaskStatus(11, 1);

	}

	

	@Test
	public void testGetSpecificTask() {
		TaskInfo taskinfo = new TaskInfo ();
		TaskServices.setTaskDao(taskdao);
		PowerMockito.when(taskdao.getTask(11)).thenReturn(taskinfo);
		TaskInfo expectedtask = new TaskInfo();
		expectedtask = TaskServices.getSpecificTask(11);
		TaskInfo taskinfo2 = new TaskInfo();
		int taskid = taskinfo2.getTaskid();
		assertEquals(expectedtask ,taskinfo);
	}

	

	@Test
	public void testUpdateAssignedPoints() {
		PowerMockito.doNothing().when(taskdao).updatePoints(11, 64);
	}

	

}
