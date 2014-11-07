package com.runMyErrand.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.runMyErrand.model.TaskInfo;

public class TaskDao {
	
private static JdbcTemplate jdbcTemplate;
private static String sql;	

private static final Logger logger = Logger.getLogger(TaskDao.class);

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
    
	/* Selects all the tasks of a room */
	public static List<TaskInfo> selectAll(String room)
	{
		sql = "select * from task where room = ?";
		List<TaskInfo> taskList = jdbcTemplate.query(sql,new Object[]{room}, new TaskRowMapper());
		logger.debug("tasklist" +taskList);
		
		return taskList;
	}
	
	/* selects all assigned tasks to particular user */
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
	
	/* selects unassigned tasks in a particular room */
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
	
	/* updates the table when the user is assigned a task*/
	public void updateTaskAssignedto(int taskid, String email, String room)
	{
		jdbcTemplate.update("update task set useremail = ? where taskid = ? and room=?", email, taskid, room);
	}
	
	/* inserts a new task */
	public void insertTask(TaskInfo task, String room ) {
		String insertSql ="INSERT INTO task (TaskDescription, points, Start_Date, End_Date, Completed, useremail, room, recurrence) VALUES(?,?,?,?,?,?,?,?);";

		String desc = task.getTaskDescription();
		int points = task.getPoints();
		String sdate = task.getStart_date();
		String edate = task.getEnd_date();
		String recurrence = task.getRecurrence();
		jdbcTemplate.update(insertSql,new Object[]{desc, points, sdate, edate,0, null, room, recurrence});
		
	}
	
	/* updates task status */
	public void updateTaskStatus(int taskid, int completed){
		logger.debug("entering updatetaskstatus");
        String sql = "UPDATE task SET completed = ? WHERE taskid = ?";
        jdbcTemplate.update(sql, new Object[]{completed, taskid});
    }
	
	/* selects the total tasks completed and add the points to get score of the user */
	public int updateScore(String email){
		logger.debug("update score");
		String sql = "Select sum(points) from task where useremail = ? and completed = 1";
		int score = 0;
		try{
		 score = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
		}
		catch(Exception e){
			score = 0;
		}
		return score;
	}
	
	/* gives the task information about a particular tasks*/
	public TaskInfo getTask(int taskid){
		logger.debug("getting task");
		String sql = "Select * from task where taskid = ?";
		List task  = (List)jdbcTemplate.query(sql, new Object[] {taskid}, new TaskRowMapper());
		
		logger.debug("task "+ task);
		return (TaskInfo)task.get(0) ;
	}
	
	/* removes a task */
	public void removeTask(int taskid){
		String sql = "Delete from task where taskDescription = ? and room = ?";
		jdbcTemplate.update(sql, new Object[]{taskid});
	}
	
	 
	
	
	
	
}
