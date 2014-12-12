package com.runMyErrand.logic;

public class ScoreManager {
	
	/** Calculation of pending score when adding a new task
	 * @param points
	 * @param totalmembers
	 * @return
	 */
	public static float pendingScore(float points, int totalmembers){
		float requiredscore = ((points / totalmembers) * 0.90f);
		return requiredscore;
		
	}
	/** Calculates the pending score when updating pending score
	 * @param currentscore
	 * @param totalpoints
	 * @param totalmembers
	 * @return
	 */
	public static float pendingScore(float currentscore, float totalpoints,
			int totalmembers) {

		float requiredscore = ((totalpoints / totalmembers) * 0.90f);
		float pendingscore = requiredscore - currentscore;
		if(pendingscore<0){
			pendingscore = 0;
		}

		return pendingscore;
	}
	
	/** Decreases task points depending upon adjustment value
	 * @param currentpoints
	 * @param adjustmentValue
	 * @return
	 */
	public static float decreaseTaskPoints(float currentpoints,float adjustmentValue){
		currentpoints -= currentpoints*adjustmentValue;
		return currentpoints;
	}
	
	/** Updates adjusted task points for Assigned Tasks
	 * @param points
	 * @param adjustmentValue
	 * @return
	 */
	public static float pointsDifference(float points,float adjustmentValue){
		return points*adjustmentValue;
	}
	
	/** Increases and adjusts task points based on dividing factor for auto ajustment
	 * @param points
	 * @param difference
	 * @param dividingfactor
	 * @return
	 */
	public static float increaseTaskPoints(float points, float difference, float dividingfactor){
		
		points += difference*(points/dividingfactor);
		return points;
	}
}