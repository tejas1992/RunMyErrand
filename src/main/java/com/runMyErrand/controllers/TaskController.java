package com.runMyErrand.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;
import com.runMyErrand.services.MemberServices;
/*Manages all task related mechanisms */
@Controller
@SuppressWarnings({"rawtypes"})
public class TaskController {
	
	private static final Logger logger = Logger.getLogger(TaskController.class);
	
	/* retrieves all unassigned tasks of a particular room so that tasks can be assigned*/
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
	
	/* updates tables when task is assigned*/
	@RequestMapping(value="/Assigntask.do" ,method = RequestMethod.POST)
	public ModelAndView assigntask(@RequestParam("taskid") int taskid, @RequestParam("assigned") String assignedto,
			HttpSession session){
		
		logger.debug("task: "+taskid);
		logger.debug("assignedto: "+assignedto);
		UserInfo user = (UserInfo)session.getAttribute("user");
		TaskServices.assignTask(taskid, assignedto, user.getRoom());
		return new ModelAndView("forward:unassignedtask");
	}
	
	/* Manages add task related process*/
	@RequestMapping(value = "/addtask.do", method = RequestMethod.POST)
	public ModelAndView addtask(@ModelAttribute("task") TaskInfo task,HttpSession session) {
		logger.debug("Entering add task controller");
		
		UserInfo user = (UserInfo)session.getAttribute("user");
		TaskServices.addTask(task, user.getRoom());
		//MemberServices.addPoints(task.getPoints(), user.getRoom());
		
		return new ModelAndView("forward:dashboard");

	}
	
	/* Edit task does the necessary job when the user completes the task. the database is updated and 
	 * recurrence is checked*/
	@RequestMapping(value = "/edittask", method = RequestMethod.POST)
	public ModelAndView editMyTask(
			@RequestParam("taskid") int taskid, HttpSession session, @RequestParam("completed") String completed) {

		logger.debug("Entering edittask");
		int status = -1;
		String check = null;
		ModelAndView model = new ModelAndView("forward:dashboard");
		
		logger.debug("checking if task is todo or completed");
				if (completed.equalsIgnoreCase("done")) {
			logger.debug("taskdone");
			status = 1;
		} else {
			logger.debug("taskdone");
			status = 0;
		}
				
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		logger.debug("getting score and setting Pending Score: Previous " + user.getPendingscore());
		int score = TaskServices.updateTaskStatus(taskid ,status, user.getEmail());
		user.setPendingscore(MemberServices.updatePendingScore(user.getRoom(), score));
		
		logger.debug("Updating User Score");
		UserServices.updateUserScore(user.getEmail(), score, user.getPendingscore());
		
		logger.debug("Updating dates if tasks complete");
		if (status == 1) {
			TaskServices.checkRecurrence(taskid, user.getRoom());
		}
		
		return model;
	}
}
