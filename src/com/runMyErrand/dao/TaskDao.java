package com.runMyErrand.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.runMyErrand.model.TaskInfo;

public class TaskDao {
	
private static JdbcTemplate jdbcTemplate;
private static String sql;	

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
    
	public static List<TaskInfo> selectAll()
	{
		sql = "select * from task";
		List<TaskInfo> taskList = jdbcTemplate.query(sql, new TaskRowMapper());
		System.out.println(taskList);
		
		return taskList;
		
	}

}
