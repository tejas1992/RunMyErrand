package com.runMyErrand.dao;

import java.sql.SQLException;



import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.UserInfo;

public class UserRowMapper implements RowMapper<UserInfo> 
{
	
	@Override
	public UserInfo mapRow(java.sql.ResultSet resultSet, int rowNumber)
			throws SQLException {

		UserInfo user = new UserInfo();
		user.setFirstName(resultSet.getString(2));
		user.setLastName(resultSet.getString(3));
		user.setSex(resultSet.getString(4));
		user.setDob(resultSet.getString(5));
		user.setEmail(resultSet.getString(6));
		user.setPhoneNo(resultSet.getString(7));
		user.setRoom(resultSet.getString(8));
		user.setPassword(resultSet.getString(9));
		
		return user;
	}
}
