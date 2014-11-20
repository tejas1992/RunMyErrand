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
	private static String sql;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	/* Sets score of the user */
	public void setScore(String email, float score){
		sql = "update userinfo set score = ? where email = ?";
		jdbcTemplate.update(sql, new Object[]{score, email});
		logger.debug("score updated");
	}
	
	/* selects one user and stores its information in model userinfo */ 
	public UserInfo selectOne(String username){
		sql = "SELECT * from userinfo where email = ?";
		UserInfo info;
		try{
			info =  (UserInfo) jdbcTemplate.queryForObject(sql, new Object[] {username}, new UserRowMapper());
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
		sql = "Select * from userinfo where room = ? and email != ?";
		
		List<UserInfo> roomies = jdbcTemplate.query(sql, new Object[]{ room, email}, new UserRowMapper());
        return roomies;
	}
	
	/* updates a pending score attribute */
	public void setPendingScore(String email, float pendingscore){
		sql = "update userinfo set pending_score = ? where email = ?";
		jdbcTemplate.update(sql, new Object[]{pendingscore, email});
		logger.debug("pending score updated");
	}
	
	/*selects all users of a particular room*/
	public List<UserInfo> selectAll(String room){
		sql = "SELECT * FROM USERINFO WHERE ROOM = ? ";
		logger.debug("Selecting all users");
		List<UserInfo> users = jdbcTemplate.query(sql, new Object[]{room}, new UserRowMapper());
		
		return users;
	}
	
	/*updates pending score of each user*/
	public void batchUpdatePendingScore(String Room, List<UserInfo>users){
		sql ="update userinfo set pending_score=? where email = ?";
		for(int i=0; i<users.size(); i++){
			jdbcTemplate.update(sql, new Object[]{users.get(i).getPendingscore(), users.get(i).getEmail()});
		}
	}
	
	/* inserts a new user and updates the necessary authority and user table  */
	public String insertUserInfo(UserInfo user, String password){
		String insertUserInfo ="INSERT INTO Userinfo (fname, lname, sex, dob, room, email, phone, pending_score) VALUES(?,?,?,?,?,?,?,?);";
		String insertUser = "INSERT INTO users(username, password, enabled) VALUES(?, ?, ?);";
		String insertAuthority = "INSERT INTO authority(username, role) VALUES(?, ?);";
		
		String fname = user.getFirstName();
		String lname = user.getLastName();
		String sex = user.getSex();
		String dob = user.getDob();
		String email = user.getEmail();
		String room = user.getRoom();
		String phone = user.getPhoneNo();
		float pendingscore = user.getPendingscore(); 
		
		try{
			jdbcTemplate.update(insertUserInfo,new Object[]{fname,lname, sex, dob, room, email, phone, pendingscore});
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

