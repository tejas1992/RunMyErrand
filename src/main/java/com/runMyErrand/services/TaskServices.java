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
	
	//service to retrieve all tasks
	public static List retriveAllTasks(String room) {
		logger.debug("Entering retrieveTasks");
		return getTaskDao().selectAll(room);
	}
	
	//service to retrieve users tasks
	public static List retrieveMyTasks(String email)
	{
		logger.debug("retrieveMyTasks");
		getTaskDao();
		return TaskDao.selectAssigned(email);
	}
	
	//service to retrieve unassigned tasks 
	public static List retrieveUnassignedTasks(String room)
	{
		return getTaskDao().selectUnAssigned(room);
	}
	
	//service to assin a task to user and update necessary tables
	public static void assignTask(int taskid, String name, String room)
	{
		logger.debug("Entering Assign task");
		getTaskDao().updateTaskAssignedto(taskid, name, room);
	}

	//service to add task and insert the the new task
	public static void addTask(TaskInfo task, String room) {
		getTaskDao().insertTask(task, room); 
		
	}

	//service to update task status as to completed or not and thus update the score
	public static int updateTaskStatus(int taskid, int completed, String email) {
		logger.debug("Entering updatetask");
        getTaskDao().updateTaskStatus(taskid, completed);
        
        logger.debug("entering updatescore");
        return getTaskDao().updateScore(email);
	}
	
	public static TaskInfo getSpecificTask(int taskid){
		return getTaskDao().getTask(taskid);
	}
	//service to check recurrence and do the necessary updates
	public static void checkRecurrence(int taskid, String room){
		
		TaskInfo task = TaskServices.getSpecificTask(taskid); 
		logger.debug("Entering checkrecurrence "+ task.getTaskDescription());
		String type = null;
		String date = null;
		
		logger.debug("checking if recurrence is weekly, monthly or null");
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
			
			//getTaskDao().removeTask(task.getTaskid());
			logger.debug("taskremoved");
			
			logger.debug("setting updated dates");
			task.setStart_date(task.getEnd_date());
			task.setEnd_date(date);
			task.setCompleted(0);
			task.setUseremail(null);
			
			getTaskDao().insertTask(task, room);
			logger.debug("task inserted");
		}
	}
	
	public static void updateAssignedPoints(int mastertaskid, float points){
		getTaskDao().updatePoints(mastertaskid, points);
	}
}
