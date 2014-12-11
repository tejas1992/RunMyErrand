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
	
	public static int insertMasterTask(TaskInfo task, String room){
		//int defaultdays = DateManager.dateDifference(task.getStart_date(), task.getEnd_date());
		logger.debug("Date Difference calculated");
		getMasterTaskDao().insertMaster(task, room);
		int masterid = getMasterTaskDao().getTaskId(task, room);
		logger.debug("Task updated");
		return masterid;
	}
	
	public static void updateAssignedTaskPoints(int taskid, String room){
		
		TaskInfo task = TaskServices.getSpecificTask(taskid);
		
		logger.info("Current points:"+ task.getPoints());
		float temp = ScoreManager.decreaseTaskPoints(task.getPoints());
		
		logger.info("Decreased points:"+ temp);
		getMasterTaskDao().updatePoints(task.getMasterId(), temp);
		TaskServices.updateAssignedPoints(task.getMasterId(), temp);
		float difference = ScoreManager.pointsDifference(task.getPoints());
		logger.info("difference:"+ difference);
		
		List<MasterTaskInfo> tasks = getMasterTaskDao().getTasks(task.getMasterId(), room);
		logger.info("MasterID:"+ task.getMasterId());
		logger.info("sizeof list:"+ tasks.size());
		float dividingfactor =  MemberServices.totalRoomPoints(room) - task.getPoints();;
		logger.info("dividing factor:"+ dividingfactor);
		logger.debug("Increasing Points");
		for(int i=0; i<tasks.size(); i++){
			logger.info("points before:"+tasks.get(i).getPoints());
			temp = ScoreManager.increaseTaskPoints(tasks.get(i).getPoints(), difference, dividingfactor);
			logger.info("pointsAfter:"+ temp);
			getMasterTaskDao().updatePoints(tasks.get(i).getMasterid(), temp);
			TaskServices.updateAssignedPoints(tasks.get(i).getMasterid(), temp);
		}
		
	}
	public static float getUpdatedPoints(TaskInfo task){
		return getMasterTaskDao().getTaskPoints(task);
	}
	
	public static List<MasterTaskInfo> retrieveMasterTasks(String room) {
		return getMasterTaskDao().selectMaster(room);
	}

}
