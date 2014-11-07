package com.runMyErrand.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class MemberDao {
	
	private static JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(MemberDao.class);
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}

	public int getTotalPoints(String room){
		String sql = "Select totalpoints from roominfo where room = ?" ;
		int total = 0;
		try{
			total = jdbcTemplate.queryForObject(sql, new Object[]{room}, Integer.class);
		}
		catch(Exception e){
			logger.debug(e);
			total = 0;
		}
		return total;
	}
	
	public int getNoMembers(String room){
		String sql = "Select members from roominfo where room = ?" ;
		int total = 0;
		try{
			total = jdbcTemplate.queryForObject(sql, new Object[]{room}, Integer.class);
		}
		catch(Exception e){
			total = 0;
		}
		return total;
	}
}
