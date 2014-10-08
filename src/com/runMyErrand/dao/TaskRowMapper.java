package com.runMyErrand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.TaskInfo;

public class TaskRowMapper implements RowMapper{

	@Override
	public TaskInfo mapRow(ResultSet rs, int index) throws SQLException {
		
		TaskInfo task = new TaskInfo();
		task.setTaskDescription(rs.getString("taskdescription"));
		task.setPoints(rs.getInt("score"));
		task.setAssignedTo(rs.getString("assignedto"));
		
		return task;
	}
	
	

}
