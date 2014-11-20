package com.runMyErrand.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
// handles all the operations of roominfo table
public class MemberDao {
	
	private static JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(MemberDao.class);
	private static String sql;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	/* get the total points of particular room */
	public float getTotalPoints(String room){
		sql = "Select totalpoints from roominfo where room = ?" ;
		float total = 0;
		try{
			total = jdbcTemplate.queryForObject(sql, new Object[]{room}, Integer.class);
		}
		catch(Exception e){
			logger.debug(e);
			total = 0;
		}
		return total;
	}
	
	/* update total points */
	public void updateTotalPoints(float updatedpoints, String room){
		
		sql = "update roominfo set totalpoints = ? where room = ?";
		jdbcTemplate.update(sql, new Object[]{updatedpoints, room});
		logger.debug("updated totalpoints");
	}
	
	/* gets the total no of members of the particular room */
	public int getNoMembers(String room){
		sql = "Select members from roominfo where room = ?" ;
		int total = 0;
		try{
			total = jdbcTemplate.queryForObject(sql, new Object[]{room}, Integer.class);
		}
		catch(Exception e){
			total = 0;
		}
		return total;
	}
	
	/* update no of members */
	public void updateMembers(int members, String room){
		sql = "update roominfo set members = ? where room = ?";
		jdbcTemplate.update(sql, new Object[]{members, room});
		logger.debug("updated totalpoints"); 
	}
	
	/* inserts new rooms */
	public void insertNewRoom(String room){
		sql = "INSERT INTO `roominfo`(`room`, `totalpoints`, `members`) VALUES (?,?,?)";
		jdbcTemplate.update(sql, new Object[]{room, 0, 1});
		logger.debug("inserted new room"); 
		
	}
}
