package com.runMyErrand.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.runMyErrand.model.MasterTaskInfo;
import com.runMyErrand.model.TaskInfo;

/**
 * Manages the queries that are related to MasterTask Table 
 */
public class MasterTaskDao {

	private static String sql;
	private static JdbcTemplate jdbcTemplate;
		
	private static final Logger logger = Logger.getLogger(MasterTaskDao.class);
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	
	/**
	 * Inserts new Master task
	 * 
	 * @param task
	 * 		task information
	 * @param room
	 * 		room no for he task
	 */
	public void insertMaster(TaskInfo task, String room){
		
		sql = "INSERT into mastertask(mastertaskdesc, room, points, Defaultdays) VALUES(?,?,?,?)";
		
		String mastertask = task.getTaskDescription();
		float points = task.getPoints();
		int defaultdays = task.getNumber_of_days();
		jdbcTemplate.update(sql, new Object[]{mastertask, room, points, defaultdays});
		
	}
	
	/**
	 * Gets TaskId of a particular MAster task 
	 * 
	 * @param task
	 * @param room
	 * @return int Id of the task
	 */
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
	
	/**
	 * Retrieves updated tasks points from master
	 * 
	 * @param task
	 * @return points
	 */
	public float getTaskPoints(TaskInfo task){
		sql = "SELECT points FROM mastertask WHERE masterid = ?";
		int masterid = task.getMasterId();
		float points = 0;
		try{
			points = jdbcTemplate.queryForObject(sql, new Object[]{masterid}, Float.class);
		}
		catch(Exception e){
			logger.debug(e);
		}
		
		return points;
		
	}
	
	
	/**
	 * updates points of a particular master task
	 * 
	 * @param masterid
	 * @param points
	 */
	public void updatePoints(int masterid, float points){
		sql = "UPDATE mastertask SET points = ? WHERE masterid = ?";
		jdbcTemplate.update(sql, new Object[]{points, masterid});
		logger.debug("points updated");
	}
	
	/*/**
	 * retrieves tasks other than the given task 
	 * @param taskid
	 * @param room
	 * @return
	 *
	public List<MasterTaskInfo> getTasks(int taskid, String room){ 
		sql = "SELECT * FROM mastertask WHERE room = ? AND masterid != ?"; 
		List<MasterTaskInfo> tasks = jdbcTemplate.query(sql, new Object[]{room, taskid}, new MasterTaskRowMapper());
		logger.debug("List retrieved");
		return tasks;
	}*/
	
	/**
	 * Retrieves all master tasks for a particular room
	 * @param room
	 * @return Lists of Tasks
	 */
	public List<MasterTaskInfo> selectMaster(String room) {

		sql = "SELECT * from mastertask where room = ?";
		List<MasterTaskInfo> masterTasks;
		try {
			masterTasks = jdbcTemplate.query(sql, new Object[] { room },
					new MasterTaskRowMapper());
			logger.debug(masterTasks);
		} catch (Exception e) {
			masterTasks = null;
		}

		return masterTasks;
	}
	
	/** Dead Code
	 * 
	 * @param masterid
	 * @param room
	 * @return
	 *
	public int getTotalPoints(int masterid, String room) {
		sql = "SELECT sum(points) from mastertask where masterid != ? and room = ?";

		int id = -1;
		try {
			id = jdbcTemplate.queryForObject(sql,
					new Object[] { masterid, room }, Integer.class);
		} catch (Exception e) {
			logger.debug(e);
			id = 0;
		}
		return id;
	}*/

}


