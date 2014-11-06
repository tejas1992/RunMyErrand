package com.runMyErrand.services;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.TaskDao;
import com.runMyErrand.logic.DateManager;
import com.runMyErrand.model.TaskInfo;

@SuppressWarnings({"rawtypes", "static-access"})

public class TaskServices {

private static final Logger logger = Logger.getLogger(TaskServices.class);
private static TaskDao taskdao;
	
	public static TaskDao getTaskDao(){
		return taskdao;
	}
	
	@Autowired
	public static void setTaskDao(TaskDao taskd){
		taskdao = taskd;
	}
	
	public static List retriveAllTasks(String room) {
		logger.debug("Entering retrieveTasks");
		return getTaskDao().selectAll(room);
	}

	public static List retrieveMyTasks(String email)
	{
		logger.debug("retrieveMyTasks");
		getTaskDao();
		return TaskDao.selectAssigned(email);
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

	public static int updateTaskStatus(String taskDescription, String Room, int completed, String email) {
		logger.debug("Entering updatetask");
        getTaskDao().updateTaskStatus(taskDescription, Room, completed);
        logger.debug("entering updatescore");
        return getTaskDao().updateScore(email);
	}
	
	public static void checkRecurrence(String taskDescription, String room){
		
		TaskInfo task =  getTaskDao().getTask(taskDescription, room);
		logger.debug("Entering checkrecurrence "+ task.getTaskDescription());
		String type = null;
		String date = null;
		logger.debug(task.getRecurrence());
		if (task.getRecurrence().equalsIgnoreCase("weekly")){
			logger.debug("weekly");
			type = "weekly";			
		}
		else if(task.getRecurrence().equalsIgnoreCase("monthly")){
			logger.debug("monthly");
			type = "monthly";
		}
		if(type != null){
			try {
				logger.debug("recurring taskfound");
				date = DateManager.recurring(task.getEnd_date(), type);
			} catch (ParseException e) {
				logger.error("Unable to Parse Date");
			}
			getTaskDao().removeTask(task.getTaskDescription(), room);
			logger.debug("taskremoved");
			task.setStart_date(task.getEnd_date());
			task.setEnd_date(date);
			task.setCompleted(0);
			task.setUseremail(null);
			getTaskDao().insertTask(task, room);
			logger.debug("task inserted");
		}
	}

	

}
