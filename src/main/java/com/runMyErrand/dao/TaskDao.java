package com.runMyErrand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.logic.DateManager;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.services.SchedulingService;

public class TaskDao{
	
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
		
		sql = "SELECT * from task where useremail = ?";
		List<TaskInfo> mytask;
		try{
			mytask = jdbcTemplate.query(sql, new Object[] {email}, new TaskRowMapper());
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
		
		sql = "SELECT * from task where useremail is ? and room = ?";
		List<TaskInfo> unassignedtask;
		try{
			unassignedtask = jdbcTemplate.query(sql, new Object[] {null, room}, new TaskRowMapper());
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
	public void insertTask(TaskInfo task, String room) {
		sql ="INSERT INTO task (TaskDescription, points, Start_Date, End_Date, Completed, useremail, room, recurrence, masterid) VALUES(?,?,?,?,?,?,?,?,?);";

		String desc = task.getTaskDescription();
		float points = task.getPoints();
		String sdate = task.getStart_date();
		String edate = task.getEnd_date();
		String recurrence = task.getRecurrence();
		int masterid = task.getMasterId();
		if(room == null){
			room = task.getRoom();
		}
		jdbcTemplate.update(sql,new Object[]{desc, points, sdate, edate,0, null, room, recurrence, masterid});
		
	}
	
	public float getTotalUnassigned(String room){
        logger.debug("update score");
        sql = "SELECT sum(points) FROM task WHERE useremail is ? AND room = ?";
        float total = 0;
        try{
         total = jdbcTemplate.queryForObject(sql, new Object[]{null, room}, Integer.class);
        }
        catch(Exception e){
            total = 0;
        }
        return total;
    }
	
	
	/* updates task status */
	public void updateTaskStatus(int taskid, int completed){
		logger.debug("entering updatetaskstatus");
        sql = "UPDATE task SET completed = ? WHERE taskid = ?";
        jdbcTemplate.update(sql, new Object[]{completed, taskid});
    }
	
	/* selects the total tasks completed and add the points to get score of the user */
	public float updateScore(String email){
		logger.debug("update score");
		sql = "Select sum(points) from task where useremail = ? and completed = 1";
		float score = 0;
		try{
		 score = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
		}
		catch(Exception e){
			logger.debug(e);
			score = 0;
		}
		return score;
	}
	
	/* gives the task information about a particular tasks*/
	public TaskInfo getTask(int taskid){
		logger.debug("getting task");
		sql = "Select * from task where taskid = ?";
		
		List<TaskInfo> task  = (List<TaskInfo>)jdbcTemplate.query(sql, new Object[] {taskid}, new TaskRowMapper());
		
		logger.debug("task "+ task);
		return task.get(0);
	}
	
	public static List<TaskInfo> selectAll(Date date){
		
		sql = "SELECT * FROM task where END_DATE = ? AND RECURRENABLE = 0";
		String d = DateManager.convertDateString(date);
		List<TaskInfo> tasks = jdbcTemplate.query(sql, new Object[]{d}, new TaskRowMapper());
		
		return tasks;
	}				
	
	/* removes a task */
	public void removeTask(int taskid){
		sql = "Delete from task where taskDescription = ? and room = ?";
		jdbcTemplate.update(sql, new Object[]{taskid});
	}
	
	public void updatePoints(int taskid, float points){
		 sql = "UPDATE task SET points = ? WHERE masterid = ? AND useremail is ?";
		 jdbcTemplate.update(sql, new Object[]{points, taskid, null});
		 logger.debug("task updated");
	 }
	
	public void disableRecurrence(int taskid){
		sql = "UPDATE task SET recurrenable=1 WHERE taskid = ?";
		jdbcTemplate.update(sql, new Object[]{taskid});
		logger.debug("disabled recurrence");
	}
	
	public List<String> getRooms(){
		sql = "SELECT DISTINCT room FROM task";
		List<String> rooms = jdbcTemplate.query(sql, new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) 
                    throws SQLException {
            		return rs.getString(1);
            }
		});
		return rooms;
	}
	
	public float getTimeboxPoints(String room){
		//sql = "SELECT sum(points) FROM task WHERE end_date >= ? AND room = ? AND completed==0";
		sql = "SELECT sum(points) FROM task WHERE useremail is ? AND room =? AND start_date >= ?";
		float points = 0;
		try{
			//points = jdbcTemplate.queryForObject(sql, new Object[]{SchedulingService.getTimeboxstartDate(), room}, Float.class);
			points = jdbcTemplate.queryForObject(sql, new Object[]{null, room, SchedulingService.getTimeboxstartDate()}, Float.class);
			
		}
		catch(Exception e){}
		return points;
	}
	
	public String getCurrentSystemDate(){
		sql = "SELECT current FROM timebox";
		String date = null;
		date = jdbcTemplate.queryForObject(sql, String.class);
		return date;
	}
	
	public String getTimeboxStartDate(){
		sql = "SELECT start FROM timebox";
		String date = null;
		date = jdbcTemplate.queryForObject(sql, String.class);
		return date;
	}
	
	public String getTimeboxEndDate(){
		sql = "SELECT end FROM timebox";
		String date = null;
		date = jdbcTemplate.queryForObject(sql, String.class);
		return date;
	}
	
	public void setCurrentDate(String date){
		sql = "UPDATE timebox SET current = ?";
		jdbcTemplate.update(sql, new Object[]{date});
		logger.debug("Current set");
	}
	public void setTimeboxStartDate(String date){
		sql = "UPDATE timebox SET start = ?";
		jdbcTemplate.update(sql, new Object[]{date});
		logger.debug("Timebox set");
	}
	public void setTimeboxEndDate(String date){
		sql = "UPDATE timebox SET end = ?";
		jdbcTemplate.update(sql, new Object[]{date});
		logger.debug("End set");
	}
	
	public static List<TaskInfo> alldueTasks(String room)
    {
        sql = "select * from task  where room = ? and completed=0 and DATE(NOW())>= DATE(End_Date)";
        List<TaskInfo> overduetaskList = jdbcTemplate.query(sql,new Object[]{room}, new TaskRowMapper());
        logger.debug("tasklist" +overduetaskList);
        return overduetaskList;
    }
}
