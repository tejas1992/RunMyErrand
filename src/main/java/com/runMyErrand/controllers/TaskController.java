package com.runMyErrand.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.logic.DateManager;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.MasterTaskServices;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

/**
 * Manages all tasks related functionality
 */
@Controller
@SuppressWarnings({"rawtypes", "unchecked"})
public class TaskController {
	
    /**
     * retrieves adjustment value for points from properties file 
     */
    @Value("${auto.adjustment}")
    private String adjustment;
	
	private static final Logger logger = Logger.getLogger(TaskController.class);
	
	/**
	 * Retrieves all unassigned tasks of a particular room so that tasks can be assigned
	 * 
	 * @param session
	 * @return ModelAndView (redirects to unassigned.jsp)
	 */
	@RequestMapping(value="/unassignedtask")
	public ModelAndView unassignedTasks(HttpSession session){
		
		ModelAndView model= new ModelAndView("unassignedtasks");
		
		ArrayList<UserInfo>roomies = (ArrayList<UserInfo>)session.getAttribute("roomies");
		UserInfo user = (UserInfo)session.getAttribute("user");
		ArrayList unassignedtasks = (ArrayList) TaskServices.retrieveUnassignedTasks(user.getRoom());
		logger.debug("unassignedtasks retrieved");
		
		model.addObject("roomies", roomies);
		model.addObject("unassigned", unassignedtasks);
		return model;
	}
	
	/**
	 * Assigns a task to a particular user and manages audjestment of points
	 * 
	 * @param taskid
	 * 			Id of task being assigned
	 * @param assignedto
	 * 			task being assigned to
	 * @param session
	 * @param length
	 * 			length of tasks 
	 * @return
	 */
	@RequestMapping(value="/Assigntask.do" ,method = RequestMethod.POST)
	public ModelAndView assigntask(@RequestParam("taskid") int taskid, @RequestParam("assigned") String assignedto,
			HttpSession session, @RequestParam("length") int length){
		
		float adjustmentValue = Float.parseFloat(adjustment);
		logger.debug("Adjustment value: "+adjustmentValue);
		logger.info("task: "+taskid);
		logger.info("assignedto: "+assignedto);
		UserInfo user = (UserInfo)session.getAttribute("user");
		TaskServices.assignTask(taskid, assignedto, user.getRoom());
		if(length>1){
			MasterTaskServices.updateAssignedTaskPoints(taskid, user.getRoom(),adjustmentValue);
			logger.debug("updated tasks");
		}
		return new ModelAndView("forward:unassignedtask");
	}
	
	/** 
	 *  The method adds tasks to task and master table;
	 * 	it further updates total points in roominfo which is required while calculating pending score; 
	 * 	and updates pending scores of all the users of the room.
	 * 
	 * @param task (Model Object TaskInfo)
	 * 		 ModelAttributes sets the task information given by user
	 * @param session
	 * 		 
	 * @return ModelAndView (forwards request to Dashboard Controller)			
	 **/
	
	@RequestMapping(value = "/addtask.do", method = RequestMethod.POST)
	public ModelAndView addtask(@ModelAttribute("task") TaskInfo task,HttpSession session,@RequestParam("flag") String flag) {
		logger.debug("Entering add task controller");
		logger.debug("Flag from page: "+flag);
		UserInfo user = (UserInfo)session.getAttribute("user");
		logger.debug("Task Description: "+task.getTaskDescription());
		logger.debug("Task points: "+task.getPoints());
		logger.debug("Number of days: "+task.getNumber_of_days());
		String end_date = DateManager.addDate(task.getStart_date(), task.getNumber_of_days());
		task.setEnd_date(end_date);
		int addFlag = Integer.parseInt(flag);
		if(addFlag == 0){
			int masterid = MasterTaskServices.insertMasterTask(task, user.getRoom());
			task.setMasterId(masterid);			
		}
		TaskServices.addTask(task, user.getRoom());
		logger.debug("adding points to roominfo");
		return new ModelAndView("forward:dashboard");
	}

	/**
	 * Changes the status of task once completed; 
	 * manages related scores
	 * 
	 * @param taskid
	 * 			Id of task being completed
	 * @param session
	 * @param completed
	 * 			status
	 * @return
	 */
	@RequestMapping(value = "/edittask", method = RequestMethod.POST)
	public ModelAndView editMyTask(
			@RequestParam("taskid") int taskid, HttpSession session, @RequestParam("completed") String completed) {

		logger.debug("Entering edittask");
		ModelAndView model = new ModelAndView("forward:dashboard");
		
		UserInfo user = (UserInfo) session.getAttribute("user");
		TaskInfo task = TaskServices.getSpecificTask(taskid);

		TaskServices.updateTaskStatus(taskid ,1, user.getEmail());
		
		float score = user.getScore()+task.getPoints();
		user.setScore(score);
		logger.debug("new user score" + user.getScore()+task.getPoints());
		logger.debug("Pending Score: Previous " + user.getPendingscore());
		float pendingscore = 0;
		if((user.getPendingscore()- task.getPoints())>=0){
			pendingscore = user.getPendingscore()- task.getPoints();
			user.setPendingscore(pendingscore);
		}
		else{
			user.setPendingscore(0);
		}
		if((user.getWeeklygoal()-task.getPoints()>=0)){
			user.setWeeklygoal(user.getWeeklygoal()-task.getPoints());
		}
		else{
			user.setWeeklygoal(0);
		}
		UserServices.updateWeeklyGoal(user.getEmail(), user.getWeeklygoal());
		logger.debug("Updating User Score");
		UserServices.updateUserScore(user.getEmail(), score, pendingscore);
		
		return model;
	}
	
	/**
	 * Retrieves all overdue Tasks
	 * 
	 * @param session
	 * @return (redirects to overduetasks.jsp)
	 */
	@RequestMapping(value="/overduetasks",method=RequestMethod.GET)
    public static ModelAndView overdueTasks(HttpSession session)
    {
        logger.debug("entering overdue tasks");
        ModelAndView model=new ModelAndView("overduetasks");
        UserInfo user = (UserInfo)session.getAttribute("user");
        ArrayList list_comp=(ArrayList) TaskServices.alloverduetasks(user.getRoom());
        logger.debug("after modelview"+list_comp);
        model.addObject("list_comp",list_comp);
        return model;
    
    }
	
	/**
	 * Retrieves all task from users roon
	 * @param session
	 * @return redirects to alltasks.jsp
	 */
	@RequestMapping(value="/alltasks")
    public ModelAndView allTasks(HttpSession session){
        
        ModelAndView model= new ModelAndView("alltasks");
        
        UserInfo user = (UserInfo)session.getAttribute("user");
        ArrayList<TaskInfo> Alltasks = (ArrayList<TaskInfo>) TaskServices.completetask(user.getRoom());
        logger.debug("unassignedtasks retrieved");
        model.addObject("alltasks", Alltasks);
        return model;
    }   
}
