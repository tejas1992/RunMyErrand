package com.runMyErrand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.TaskInfo;

/* Maps each row of result into the modelattribute taskinfo*/
public class TaskRowMapper implements RowMapper<TaskInfo>{

	public TaskInfo mapRow(ResultSet rs, int index) throws SQLException {
		
		TaskInfo task = new TaskInfo();
		task.setTaskid(rs.getInt(1));
		task.setMasterId(rs.getInt(2));
		task.setTaskDescription(rs.getString(3));
		task.setPoints(rs.getFloat(4));
		task.setStart_date(rs.getString(5));
		task.setEnd_date(rs.getString(6));
		task.setCompleted(rs.getInt(7));
		task.setUseremail(rs.getString(8));
		task.setRoom(rs.getString(9));
		task.setRecurrence(rs.getString(10));
	
		return task;
	}
	
	

}
