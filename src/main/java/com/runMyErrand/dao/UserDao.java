package com.runMyErrand.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.runMyErrand.model.UserInfo;
//Manages userinfo table operations
public class UserDao {

private static final Logger logger = Logger.getLogger(UserDao.class);
private static JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	/* Sets score of the user */
	public void setScore(String email, int score){
		String sql = "update userinfo set score = ? where email = ?";
		jdbcTemplate.update(sql, new Object[]{score, email});
		logger.debug("score updated");
	}
	
	/* selects one user and stores its information in model userinfo */ 
	public UserInfo selectOne(String username){
		String selectSql = "SELECT * from userinfo where email = ?";
		UserInfo info;
		try{
			info =  (UserInfo) jdbcTemplate.queryForObject(selectSql, new Object[] {username}, new UserRowMapper());
			logger.debug(info.getFirstName());
		}	
		catch(Exception e)
		{
			info = null;
		}
		return  info;
	}
	
	/* Selects roommates of the user */
	public List<UserInfo> selectRoomies(String room, String email)
	{
		String sql = "Select * from userinfo where room = ? and email != ?";
		
		List<UserInfo> roomies = jdbcTemplate.query(sql, new Object[]{ room, email}, new UserRowMapper());
        return roomies;
	}
	
	/* updates a pending score attribute */
	public void setPendingScore(String email, int pendingscore){
		String sql = "update userinfo set pending_score = ? where email = ?";
		jdbcTemplate.update(sql, new Object[]{pendingscore, email});
		logger.debug("score updated");
	}
	
	/* inserts a new user and updates the necessary authority and user table  */
	public String insertUserInfo(UserInfo user, String password){
		String insertUserInfo ="INSERT INTO Userinfo (fname, lname, sex, dob, room, email, phone) VALUES(?,?,?,?,?,?,?);";
		String insertUser = "INSERT INTO users(username, password, enabled) VALUES(?, ?, ?);";
		String insertAuthority = "INSERT INTO authority(username, role) VALUES(?, ?);";
		
		String fname = user.getFirstName();
		String lname = user.getLastName();
		String sex = user.getSex();
		String dob = user.getDob();
		String email = user.getEmail();
		String room = user.getRoom();
		String phone = user.getPhoneNo();
		
		try{
			jdbcTemplate.update(insertUserInfo,new Object[]{fname,lname, sex, dob, room, email, phone});
			jdbcTemplate.update(insertUser, new Object[]{email, password, 1});
			jdbcTemplate.update(insertAuthority, new Object[]{email, "ROLE_USER"});
		}
		catch(Exception e)
		{
			logger.debug(e);
			return "Email already exist. Try signing up with a different email";
		}
		
		
		return null;
	}
	
	
}	

