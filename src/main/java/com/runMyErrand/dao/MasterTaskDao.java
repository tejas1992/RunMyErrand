package com.runMyErrand.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.runMyErrand.model.MasterTaskInfo;
import com.runMyErrand.model.TaskInfo;

public class MasterTaskDao {

	private static String sql;
	private static JdbcTemplate jdbcTemplate;
		
	private static final Logger logger = Logger.getLogger(MasterTaskDao.class);
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	public void insertMaster(TaskInfo task, String room){
		
		sql = "INSERT into mastertask(mastertaskdesc, room, points, Defaultdays) VALUES(?,?,?,?)";
		
		String mastertask = task.getTaskDescription();
		float points = task.getPoints();
		int defaultdays = task.getNumber_of_days();
		jdbcTemplate.update(sql, new Object[]{mastertask, room, points, defaultdays});
		
	}
	
	public int getTaskId(TaskInfo task, String room){
		sql = "SELECT masterid from mastertask where mastertaskdesc = ? and room = ?";

		String description = task.getTaskDescription();
		int id = -1;
		try{
			id = jdbcTemplate.queryForObject(sql, new Object[]{description, room}, Integer.class);
		}
		catch(Exception e){
			id = 0;
		}
		return id;
	}
	
	public float getTaskPoints(TaskInfo task){
		sql = "SELECT points FROM mastertask WHERE masterid = ?";
		int masterid = task.getMasterId();
		float points = 0;
		try{
			points = jdbcTemplate.queryForObject(sql, new Object[]{masterid}, Float.class);
		}
		catch(Exception e){}
		
		return points;
		
	}
	
	public void updatePoints(int masterid, float points){
		sql = "UPDATE mastertask SET points = ? WHERE masterid = ?";
		jdbcTemplate.update(sql, new Object[]{points, masterid});
		logger.debug("points updated");
	}
	
	public List<MasterTaskInfo> getTasks(int taskid, String room){
		sql = "SELECT * FROM mastertask WHERE room = ? AND masterid != ?";
		List<MasterTaskInfo> tasks = jdbcTemplate.query(sql, new Object[]{room, taskid}, new MasterTaskRowMapper());
		logger.debug("List retrieved");
		return tasks;
	}
	
	public List<MasterTaskInfo> selectMaster(String room) {

		sql = "SELECT * from mastertask where room = ?";
		List<MasterTaskInfo> masterTasks;
		try{
			masterTasks = jdbcTemplate.query(sql, new Object[] {room}, new MasterTaskRowMapper());
			logger.debug(masterTasks);
		}	
		catch(Exception e){
			masterTasks = null;
		}

		return  masterTasks;
	}
	
	public int getTotalPoints(int masterid, String room){
		sql = "SELECT sum(points) from mastertask where masterid != ? and room = ?";

		int id = -1;
		try{
			id = jdbcTemplate.queryForObject(sql, new Object[]{masterid, room}, Integer.class);
		}
		catch(Exception e){
			logger.debug(e);
			id = 0;
		}
		return id;
	}

}


