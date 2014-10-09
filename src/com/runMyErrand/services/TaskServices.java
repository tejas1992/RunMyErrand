package com.runMyErrand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.TaskDao;

public class TaskServices {
	
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
	

}
