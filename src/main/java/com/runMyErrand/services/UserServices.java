package com.runMyErrand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.UserDao;
import com.runMyErrand.model.UserInfo;

public class UserServices {
	
	private static UserDao userdao;
	
	public static UserDao getUserDao(){
		return userdao;
	}
	
	@Autowired
	public static void setUserDao(UserDao userd)
	{
		userdao = userd;
	}
	
	public static UserInfo selectUser(String username)
	{
		return getUserDao().selectOne(username);
	}
	
	public static List<UserInfo> selectMyRoomies(String room, String email)
	{
		return getUserDao().selectRoomies(room, email);
	}
	public static String addUser(UserInfo userinfo, String password)
	{
		String success = getUserDao().insertUserInfo(userinfo, password);
		return success;
	}

}
