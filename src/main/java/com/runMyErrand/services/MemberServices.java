package com.runMyErrand.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.MemberDao;
import com.runMyErrand.logic.ScoreManager;
import com.runMyErrand.model.UserInfo;

/* Services gives a service to a controller so that the controller manages only the control flow
 * It hides the multiple tasks if necessary from controller */
public class MemberServices {
	
	private static final Logger logger = Logger.getLogger(MemberServices.class);
	private static MemberDao memberdao;
	
	public static MemberDao getMemberDao(){
		return memberdao;
	}
	
	@Autowired
	public static void setMemberDao(MemberDao membd){
		memberdao = membd;
	}
	
	/* Manages the logic for pending score retrieves necessary values to calculate */
	public static int updatePendingScore(String room, int currentScore){
		logger.debug("updatePendingScore");
		int totalpoints = getMemberDao().getTotalPoints(room);
		logger.debug(totalpoints);
		int totalmembers = getMemberDao().getNoMembers(room);
		logger.debug(totalmembers);

		ScoreManager sc = new ScoreManager();
		return sc.pendingScore(currentScore, totalpoints, totalmembers);
	}
	
	/* Adds points to roomyinfo table when added points */
	public static void addPoints(int taskpoints, String room){
		
		logger.debug("Entering add points");
		int totalpoints = getMemberDao().getTotalPoints(room);
		logger.debug(totalpoints);
		taskpoints += totalpoints;
		logger.debug(totalpoints);
		getMemberDao().updateTotalPoints(taskpoints, room);
		
	}
	
	//updates new user in roominfo table: sets pending score if room already exists 
	public static void updateMembers(UserInfo user){
		
		logger.debug("Entering update user");
		int members = getMemberDao().getNoMembers(user.getRoom());
		if(members != 0){
			members+=1;
			getMemberDao().updateMembers(members, user.getRoom());
			int pending = updatePendingScore(user.getRoom(), 0);
			UserServices.getUserDao().setPendingScore(user.getEmail(), pending);
			
		}
		else{
			getMemberDao().insertNewRoom(user.getRoom());
		}
	}

}