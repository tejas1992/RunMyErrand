package com.runMyErrand.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;

public class TaskDao {
	
private static JdbcTemplate jdbcTemplate;
private static String sql;	

private static final Logger logger = Logger.getLogger(TaskDao.class);

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
    
	public static List<TaskInfo> selectAll()
	{
		sql = "select * from task";
		List<TaskInfo> taskList = jdbcTemplate.query(sql, new TaskRowMapper());
		logger.debug(taskList);
		
		return taskList;
	}
	
	public static List<TaskInfo> selectAssigned(String firstname){
		
		String selectSql = "SELECT * from task where assignedto = ?";
		List<TaskInfo> mytask;
		try{
			mytask = jdbcTemplate.query(selectSql, new Object[] {firstname}, new TaskRowMapper());
			logger.debug(mytask);
		}	
		catch(Exception e)
		{
			mytask = null;
		}
		return  mytask;
	}
	
	public static List<TaskInfo> selectUnAssigned(){
		
		String selectSql = "SELECT * from task where assignedto is ?";
		List<TaskInfo> unassignedtask;
		try{
			unassignedtask = jdbcTemplate.query(selectSql, new Object[] {null}, new TaskRowMapper());
			logger.debug(unassignedtask);
		}	
		catch(Exception e)
		{
			unassignedtask = null;
		}
		return  unassignedtask;
	}
	
	public void updateTaskAssignedto(String task, String name)
	{
		jdbcTemplate.update("update task set assignedto = ? where taskDescription = ?", name, task);
	}

	public void insert(TaskInfo task) {
		String insertSql ="INSERT INTO task (TaskDescription, Score, Start_Date, End_Date, Completed) VALUES(?,?,?,?,?);";

		String desc = task.getTaskDescription();
		int points = task.getPoints();
		String sdate = task.getStartDate();
		String edate = task.getEndDate();

		jdbcTemplate.update(insertSql,new Object[]{desc,points, sdate, edate,0});
		
	}
	
	
}
