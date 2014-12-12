package com.runMyErrand.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.MasterTaskDao;
import com.runMyErrand.logic.DateManager;
import com.runMyErrand.logic.ScoreManager;
import com.runMyErrand.model.MasterTaskInfo;
import com.runMyErrand.model.TaskInfo;

public class MasterTaskServices {

	private static final Logger logger = Logger.getLogger(MemberServices.class);
	private static MasterTaskDao mastertaskdao;
	
	public static MasterTaskDao getMasterTaskDao(){
		return mastertaskdao;
	}
	
	@Autowired
	public static void setMasterTaskDao(MasterTaskDao membd){
		mastertaskdao = membd;
	}
	
	/** Inserting a master task entry into database
	 * @param task
	 * @param room
	 * @return
	 */
	public static int insertMasterTask(TaskInfo task, String room){
		logger.debug("Date Difference calculated");
		getMasterTaskDao().insertMaster(task, room);
		int masterid = getMasterTaskDao().getTaskId(task, room);
		logger.debug("Task updated");
		return masterid;
	}
	
/** Updating Assigned Task points in database when task is assigned
 * @param taskid
 * @param room
 * @param adjustmentValue
 */
public static void updateAssignedTaskPoints(int taskid, String room,float adjustmentValue){
        
        TaskInfo task = TaskServices.getSpecificTask(taskid);
        
        logger.info("Current points:"+ task.getPoints());
        float temp = ScoreManager.decreaseTaskPoints(task.getPoints(),adjustmentValue);
        
        logger.info("Decreased points:"+ temp);
        getMasterTaskDao().updatePoints(task.getMasterId(), temp);
        TaskServices.updateAssignedPoints(task.getMasterId(), temp);
        float difference = ScoreManager.pointsDifference(task.getPoints(),adjustmentValue);
        logger.info("difference:"+ difference);
        
        List<TaskInfo> tasks = TaskServices.retrieveUnassignedTasks(room);
        logger.info("MasterID:"+ task.getMasterId());
        logger.info("sizeof list:"+ tasks.size());
        //float dividingfactor =  MemberServices.totalRoomPoints(room) - task.getPoints();
        float dividingfactor = TaskServices.totalUnassigned(room);
        logger.info("dividing factor:"+ dividingfactor);
        logger.debug("Increasing Points");
        if(tasks.size()==1){
        	getMasterTaskDao().updatePoints(tasks.get(0).getMasterId(), tasks.get(0).getPoints()+difference);
        	TaskServices.updateAssignedPoints(tasks.get(0).getMasterId(), tasks.get(0).getPoints()+difference);
        }
        else{
        	for(int i=0; i<tasks.size(); i++){
        		logger.info("points before:"+tasks.get(i).getPoints());
        		temp = ScoreManager.increaseTaskPoints(tasks.get(i).getPoints(), difference, dividingfactor);
        		logger.info("pointsAfter:"+ temp);
        		getMasterTaskDao().updatePoints(tasks.get(i).getMasterId(), temp);
        		TaskServices.updateAssignedPoints(tasks.get(i).getMasterId(), temp);
        	}
        }
}
	/** Retrieves Updated Points for master tasks
	 * @param task
	 * @return
	 */
	public static float getUpdatedPoints(TaskInfo task){
		return getMasterTaskDao().getTaskPoints(task);
	}
	
	/** Retrieves master tasks for a particular room
	 * @param room
	 * @return
	 */
	public static List<MasterTaskInfo> retrieveMasterTasks(String room) {
		return getMasterTaskDao().selectMaster(room);
	}

}
