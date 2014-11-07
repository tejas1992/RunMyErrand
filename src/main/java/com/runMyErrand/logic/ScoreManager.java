package com.runMyErrand.logic;

public class ScoreManager {

	public static int pendingScore(int currentscore, int totalpoints,
			int totalmembers) {

		int requiredscore = (int) ((int) (totalpoints / totalmembers) * 0.90);
		int pendingscore = requiredscore - currentscore;
		if(pendingscore<0){
			pendingscore = 0;
		}

		return pendingscore;
	}
}