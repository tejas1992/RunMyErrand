package com.runMyErrand.dao;

import java.sql.SQLException;



import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.UserInfo;

//Maps each row of resultset into model userinfo
public class UserRowMapper implements RowMapper<UserInfo> 
{
	
	public UserInfo mapRow(java.sql.ResultSet resultSet, int rowNumber)
			throws SQLException {

		UserInfo user = new UserInfo();
		
		user.setUserid(resultSet.getInt(1));
		user.setFirstName(resultSet.getString(2));
		user.setLastName(resultSet.getString(3));
		user.setSex(resultSet.getString(4));
		user.setDob(resultSet.getString(5));
		user.setEmail(resultSet.getString(6));
		user.setPhoneNo(resultSet.getString(7));
		user.setRoom(resultSet.getString(8));
		user.setScore(resultSet.getFloat(9));
		user.setPendingscore(resultSet.getFloat(10));
				
		return user;
	}
}
