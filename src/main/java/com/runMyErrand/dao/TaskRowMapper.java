package com.runMyErrand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.TaskInfo;

public class TaskRowMapper implements RowMapper{

	public TaskInfo mapRow(ResultSet rs, int index) throws SQLException {
		
		TaskInfo task = new TaskInfo();
		task.setTaskDescription(rs.getString("taskdescription"));
		task.setPoints(rs.getInt("points"));
		task.setStart_date(rs.getString(4));
		task.setEnd_date(rs.getString(5));
		task.setCompleted(rs.getInt(6));
		task.setUseremail(rs.getString(7));
		task.setRoom(rs.getString(8));
		task.setRecurrence(rs.getString(9));
	
		return task;
	}
	
	

}
