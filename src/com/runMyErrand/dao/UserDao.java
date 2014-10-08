package com.runMyErrand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.UserInfo;

public class UserDao {
	
private static JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	public UserInfo selectOne(String username)
	{
		String selectSql = "SELECT * from userinfo where email = ?";
		UserInfo info;
		try{
			info =  (UserInfo) jdbcTemplate.queryForObject(selectSql, new Object[] {username}, new UserRowMapper());
			System.out.println(info.getFirstName());
		}	
		catch(Exception e)
		{
			info = null;
		}
		return  info;
	}
	
	public List<String> selectRoomies(String room, String name)
	{
		String sql = "Select fname from userinfo where room = ? and fname != ?";
		//return jdbcTemplate.query(sql, Object[]{room, name});
		List<String> roomies = jdbcTemplate.query(sql, new Object[]{ room, name}, new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("fname");
            }
		});
        return roomies;
	}
	
	public void insert(UserInfo user){
		String insertSql ="INSERT INTO Userinfo (fname, lname, sex, dob, room, email, phone, password) VALUES(?,?,?,?,?,?,?,?);";
		
		String fname = user.getFirstName();
		String lname = user.getLastName();
		String sex = user.getSex();
		String dob = user.getDob();
		String email = user.getEmail();
		String room = user.getRoom();
		String phone = user.getPhoneNo();
		String password = user.getPassword();
		
		jdbcTemplate.update(insertSql,new Object[]{fname,lname, sex, dob, room, email, phone, password});
		 
		}
}	

