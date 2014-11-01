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
	
	public static List retriveAllTasks(String room)
	{
		logger.debug("Entering retrieveTasks");
		return getTaskDao().selectAll(room);
	}
	
	public static List retrieveMyTasks(String email)
	{
		logger.debug("retrieveMyTasks");
		return getTaskDao().selectAssigned(email);
	}
	
	public static List retrieveUnassignedTasks(String room)
	{
		return getTaskDao().selectUnAssigned(room);
	}
	
	public static void assignTask(String task, String name, String room)
	{
		logger.debug("Entering Assign task");
		logger.debug(task);
		logger.debug(name);
		getTaskDao().updateTaskAssignedto(task, name, room);
	}

	public static void addTask(TaskInfo task, String room) {
		getTaskDao().insertTask(task, room);		
	}
	

}
