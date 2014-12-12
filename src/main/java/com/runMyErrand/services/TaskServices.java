package com.runMyErrand.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runMyErrand.dao.TaskDao;
import com.runMyErrand.logic.DateManager;
import com.runMyErrand.logic.ScoreManager;
import com.runMyErrand.model.TaskInfo;

@SuppressWarnings({"rawtypes", "static-access"})

public class TaskServices {

private static final Logger logger = Logger.getLogger(TaskServices.class);
private static TaskDao taskdao;
	
	public static TaskDao getTaskDao(){
		return taskdao;
	}
	
	@Autowired
	public static void setTaskDao(TaskDao taskd){
		taskdao = taskd;
	}
	
	/** Service to retrieve all tasks
	 * @param room
	 * @return
	 */
	public static List retriveAllTasks(String room) {
		logger.debug("Entering retrieveTasks");
		return getTaskDao().selectAll(room);
	}
	
	/** Service to retrieve users tasks
	 * @param email
	 * @return
	 */
	public static List<TaskInfo> retrieveMyTasks(String email)
	{
		logger.debug("retrieveMyTasks");
		getTaskDao();
		return TaskDao.selectAssigned(email);
	}
	
	/** Service to retrieve unassigned tasks
	 * @param room
	 * @return
	 */
	public static List retrieveUnassignedTasks(String room)
	{
		return getTaskDao().selectUnAssigned(room);
	}
	
	/** Service to assign a task to user and update necessary tables
	 * @param taskid
	 * @param name
	 * @param room
	 */
	public static void assignTask(int taskid, String name, String room)
	{
		logger.debug("Entering Assign task");
		getTaskDao().updateTaskAssignedto(taskid, name, room);
	}

	/** Service to add task and insert the the new task
	 * @param task
	 * @param room
	 */
	public static void addTask(TaskInfo task, String room) {
		getTaskDao().insertTask(task, room);
		int totalmembers = MemberServices.numberOfMembers(room);
		float pendingscore = ScoreManager.pendingScore(task.getPoints(), totalmembers);
		//MemberServices.addPoints(task.getPoints(), room);
		UserServices.pendingScoresBatchUpdate(room, pendingscore);
	}
	
	
	/** Service to update task status as to completed or not and thus update the score
	 * @param taskid
	 * @param completed
	 * @param email
	 */
	public static void updateTaskStatus(int taskid, int completed, String email) {
		logger.debug("Entering updatetask");
        getTaskDao().updateTaskStatus(taskid, completed);
        
        logger.debug("entering updatescore");
       // return TaskServices.changeUserScore(email);
	}
	
	/** Gets value for total unassigned points for a particular room
	 * @param room
	 * @return
	 */
	public static float totalUnassigned(String room){
        return getTaskDao().getTotalUnassigned(room);
    }
	/** Changes user Score for a particular user
	 * @param email
	 * @return
	 */
	public static float changeUserScore(String email){
		return getTaskDao().updateScore(email);
	}
	
	/** Get Specific Task
	 * @param taskid
	 * @return
	 */
	public static TaskInfo getSpecificTask(int taskid){
		return getTaskDao().getTask(taskid);
	}
	
	/** service to check recurrence and do the necessary updates
	 * @param date
	 */
	public static void checkRecurrence(String date){
		logger.debug("---Checking for RecurringTasks Due on "+ SchedulingService.getCurrentSystemDate()+"---");
		Date d = DateManager.convertStringDate(date);
		List<TaskInfo> tasks = getTaskDao().selectAll(d);
		logger.debug("Potential Recurring Tasks" + tasks);
		String type = null;
		
		for(int i=0; i<tasks.size(); i++){
			
			TaskInfo task = tasks.get(i);
			String room = task.getRoom();
			logger.debug("checking if recurrence is weekly, monthly or null");
			if (task.getRecurrence().equalsIgnoreCase("weekly")){
				logger.debug("weekly");
				type = "weekly";
			}
			else if(task.getRecurrence().equalsIgnoreCase("monthly")){
				logger.debug("monthly");
				type = "monthly";
			}
		
			if(type != null){
				logger.debug("recurring taskfound");
				if(type.equalsIgnoreCase("weekly"))
					date = DateManager.addDate(task.getEnd_date(), 8);
				else{
					date = DateManager.addDate(task.getEnd_date(), 31);
				}
			logger.debug("setting updated dates");
			task.setStart_date(DateManager.addDate(task.getEnd_date(),1));
			task.setEnd_date(date);
			task.setCompleted(0);
			task.setUseremail(null);
			task.setPoints(MasterTaskServices.getUpdatedPoints(task));
			TaskServices.addTask(task, room);
			//getTaskDao().insertTask(task, room);
			logger.debug("task inserted");
			getTaskDao().disableRecurrence(task.getTaskid());
			}
		}
		
		
		
	}
	/** Retrieve a list of rooms
	 * @return
	 */
	public static List<String> getRooms(){
		return getTaskDao().getRooms();
	}
	
	/** Get timebox points for a particular room
	 * @param room
	 * @return
	 */
	public static float getTimeboxPoints(String room){
		return getTaskDao().getTimeboxPoints(room);
	}
	
	/** Updates assigned points for a master task
	 * @param mastertaskid
	 * @param points
	 */
	public static void updateAssignedPoints(int mastertaskid, float points){
		getTaskDao().updatePoints(mastertaskid, points);
	}
	
	/** Get list of overdue tasks for a room
	 * @param room
	 * @return
	 */
	public static List alloverduetasks(String room) {
        logger.debug("Entering alloverdue tasks");
        getTaskDao();
        return TaskDao.alldueTasks(room);
    }
	
	/** Get list of all completed Tasks for a room
	 * @param room
	 * @return
	 */
	public static List completetask(String room)
    {
        return getTaskDao().selectAll(room);
    }
}
