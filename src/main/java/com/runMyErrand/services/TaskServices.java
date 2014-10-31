package com.runMyErrand.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.TaskDao;
import com.runMyErrand.model.TaskInfo;

@SuppressWarnings("rawtypes")

public class TaskServices {

private static final Logger logger = Logger.getLogger(TaskServices.class);
private static TaskDao taskdao;
	
	public static TaskDao getTaskDao(){
		return taskdao;
	}
	
	@Autowired
	public static void setTaskDao(TaskDao taskd)
	{
		taskdao = taskd;
	}
	
	public static List retriveAllTasks()
	{
		return getTaskDao().selectAll();
	}
	
	public static List retrieveMyTasks(String firstname)
	{
		return getTaskDao().selectAssigned(firstname);
	}
	
	public static List retrieveUnassignedTasks()
	{
		return getTaskDao().selectUnAssigned();
	}
	
	public static void assignTask(String task, String name)
	{
		logger.debug("Entering Assign task");
		logger.debug(task);
		logger.debug(name);
		getTaskDao().updateTaskAssignedto(task, name);
	}

	public static void addTask(TaskInfo task) {
		getTaskDao().insert(task);		
	}
	

}
