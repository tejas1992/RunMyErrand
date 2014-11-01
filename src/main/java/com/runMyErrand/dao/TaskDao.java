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
    
	public static List<TaskInfo> selectAll(String room)
	{
		sql = "select * from task where room = ?";
		List<TaskInfo> taskList = jdbcTemplate.query(sql,new Object[]{room}, new TaskRowMapper());
		logger.debug("tasklist" +taskList);
		
		return taskList;
	}
	
	public static List<TaskInfo> selectAssigned(String email){
		
		String selectSql = "SELECT * from task where useremail = ?";
		List<TaskInfo> mytask;
		try{
			mytask = jdbcTemplate.query(selectSql, new Object[] {email}, new TaskRowMapper());
			logger.debug("Mytask:"+mytask);
		}	
		catch(Exception e)
		{
			logger.debug("no Mytask");
			mytask = null;
		}
		return  mytask;
	}
	
	public static List<TaskInfo> selectUnAssigned(String room){
		
		String selectSql = "SELECT * from task where useremail is ? and room = ?";
		List<TaskInfo> unassignedtask;
		try{
			unassignedtask = jdbcTemplate.query(selectSql, new Object[] {null, room}, new TaskRowMapper());
			logger.debug(unassignedtask);
		}	
		catch(Exception e)
		{
			unassignedtask = null;
		}
		return  unassignedtask;
	}
	
	public void updateTaskAssignedto(String task, String email, String room)
	{
		jdbcTemplate.update("update task set useremail = ? where taskDescription = ? and room=?", email, task, room);
	}

	public void insertTask(TaskInfo task, String room ) {
		String insertSql ="INSERT INTO task (TaskDescription, points, Start_Date, End_Date, Completed, useremail, room) VALUES(?,?,?,?,?,?,?);";

		String desc = task.getTaskDescription();
		int points = task.getPoints();
		String sdate = task.getStart_date();
		String edate = task.getEnd_date();

		jdbcTemplate.update(insertSql,new Object[]{desc, points, sdate, edate,0, null, room});
		
	}
	
	
}
