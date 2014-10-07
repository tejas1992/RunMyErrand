package com.runMyErrand.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcDaoImpl {
	
	private static JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	public static int getCircleCount(){
		String sql = "select count(*) from circle";
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}
	
	public static String getName(int id){
		String sql = "select name from circle where id = "+id;
		return jdbcTemplate.queryForObject(sql,String.class);
	}
	
}
