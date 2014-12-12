package com.runMyErrand.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;


public class ScoreManager {

	private static final Logger logger = Logger.getLogger(ScoreManager.class);


	
	public static float pendingScore(float points, int totalmembers){
		float requiredscore = ((points / totalmembers) * 0.90f);
		return requiredscore;
		
	}
	//Calculates the pending score 
	public static float pendingScore(float currentscore, float totalpoints,
			int totalmembers) {

		float requiredscore = ((totalpoints / totalmembers) * 0.90f);
		float pendingscore = requiredscore - currentscore;
		if(pendingscore<0){
			pendingscore = 0;
		}

		return pendingscore;
	}
	
	public static float decreaseTaskPoints(float currentpoints,float adjustmentValue){
		currentpoints -= currentpoints*adjustmentValue;
//		currentpoints -= currentpoints*0.20;
		return currentpoints;
	}
	
	public static float pointsDifference(float points,float adjustmentValue){
		return points*adjustmentValue;
//		return points*0.20f;
	}
	
	public static float increaseTaskPoints(float points, float difference, float dividingfactor){
		
		points += difference*(points/dividingfactor);
		return points;
	}
}