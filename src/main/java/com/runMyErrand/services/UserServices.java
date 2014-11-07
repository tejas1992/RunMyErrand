package com.runMyErrand.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.UserDao;
import com.runMyErrand.model.UserInfo;

public class UserServices {
	
	private static final Logger logger = Logger.getLogger(UserServices.class);
	
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
	
	public static void updateUserScore(String email, int score, int pendingscore){
		logger.debug("updating user score");
		getUserDao().setScore(email, score);
		getUserDao().setPendingScore(email, pendingscore);
	}

}
