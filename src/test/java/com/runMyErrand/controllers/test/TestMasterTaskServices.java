package com.runMyErrand.controllers.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.runMyErrand.dao.MasterTaskDao;
import com.runMyErrand.model.MasterTaskInfo;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.services.MasterTaskServices;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MasterTaskDao.class,MasterTaskServices.class})
public class TestMasterTaskServices {
	
	MasterTaskServices testmastertaskservice;
	@Mock
	MasterTaskDao testmastertaskdao;
	@Mock
	TaskInfo task;
	@Mock
	Logger logger;
	int actual;
	int expected;
	MasterTaskDao masterTaskDao;


	@Before
	public void setUp() throws Exception {
		testmastertaskservice = new MasterTaskServices();
		PowerMockito.mockStatic(MasterTaskDao.class);
		masterTaskDao = new MasterTaskDao();

	}

	@Test
	public void testInsertMasterTask() {
		PowerMockito.mockStatic(MasterTaskServices.class);
		actual = 5;
		PowerMockito.doNothing().when(testmastertaskdao).insertMaster(task, "910A");
		MasterTaskServices.setMasterTaskDao(testmastertaskdao);
		PowerMockito.when(task.getTaskDescription()).thenReturn("wash utensils");
		PowerMockito.when(testmastertaskdao.getTaskId(task, "910A")).thenReturn(actual);
		expected = MasterTaskServices.insertMasterTask(task, "910A");
		assertEquals(expected, 0);

	}


	@Test
	public void testGetUpdatedPoints() {

		MasterTaskServices.setMasterTaskDao(testmastertaskdao);
		float actual=0;
		float expected=0;
		Double d1 = new Double(actual);		
		MasterTaskInfo mastertaskinfo = new MasterTaskInfo();
		mastertaskinfo.setPoints(10);	
		PowerMockito.when(testmastertaskdao.getTaskPoints(task)).thenReturn(actual);
		TaskInfo masttask = new TaskInfo();
	     masttask.setPoints(10);
		expected = MasterTaskServices.getUpdatedPoints(masttask);
		Double d2 = new Double(expected);
		assertEquals(d2,d1);
	}

	@Test
	public void testRetrieveMasterTasks() {
		
		MasterTaskInfo mastertaskinfo = new MasterTaskInfo();
		mastertaskinfo.setMastertaskdesc("wash utensils");
		ArrayList<MasterTaskInfo> newList = new ArrayList<MasterTaskInfo>();
		newList.add(mastertaskinfo);
		MasterTaskServices.setMasterTaskDao(testmastertaskdao);
		PowerMockito.when(testmastertaskdao.selectMaster("910A")).thenReturn(newList);
		ArrayList<MasterTaskInfo> expected = new ArrayList<MasterTaskInfo>();
		expected = (ArrayList<MasterTaskInfo>) MasterTaskServices.retrieveMasterTasks("910A");
		MasterTaskInfo taskinfo2 = new MasterTaskInfo();
		taskinfo2 = expected.get(0);
		String taskdescription = taskinfo2.getMastertaskdesc();
		assertEquals(taskdescription , "wash utensils");

	}

}
