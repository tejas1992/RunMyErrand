package com.runMyErrand.logic;

public class ScoreManager {

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
	
	public static float decreaseTaskPoints(float currentpoints){
		
		currentpoints -= currentpoints*0.20;
		return currentpoints;
	}
	
	public static float pointsDifference(float points){
		return points*0.20f;
	}
	
	public static float increaseTaskPoints(float points, float difference, float dividingfactor){
		
		points += difference*(points/dividingfactor);
		return points;
	}
}