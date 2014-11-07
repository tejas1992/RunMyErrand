package com.runMyErrand.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.MemberDao;
import com.runMyErrand.logic.ScoreManager;

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
	
	public static int updatePendingScore(String room, int currentScore){
		logger.debug("updatePendingScore");
		int totalpoints = getMemberDao().getTotalPoints(room);
		logger.debug(totalpoints);
		int totalmembers = getMemberDao().getNoMembers(room);
		logger.debug(totalmembers);

		ScoreManager sc = new ScoreManager();
		return sc.pendingScore(currentScore, totalpoints, totalmembers);
	}

}
