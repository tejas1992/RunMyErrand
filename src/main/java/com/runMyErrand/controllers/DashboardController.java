package com.runMyErrand.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.logic.DateManager;
import com.runMyErrand.model.MasterTaskInfo;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.MasterTaskServices;
import com.runMyErrand.services.SchedulingService;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

/**
 * The class contains functions manages the admin and user dashboard.
 * 
 * @author Tejas
 * @author Abhinav
 *
 */

@Controller
@SessionAttributes("user")
@SuppressWarnings("unchecked")
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(DashboardController.class);
	
	/**
	 * Fetches all the necessary user data which is required in the session;
	 * It sets the session attributes;
	 * retrieves the tasks and scores of the user as well as that of his\her roommates 
	 * 
	 * @return ModelAndView - redirects to Dashboard.jsp
	 *  
	 * */
	@RequestMapping("/dashboard**")	
	public ModelAndView dashboard(HttpSession session){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   		
		String username = auth.getName();
		
		logger.debug("Entered dashboard "+ username);
		UserInfo user = UserServices.selectUser(username); 
		ModelAndView model = new ModelAndView("Dashboard");
		
		ArrayList<UserInfo> list_roomy = (ArrayList<UserInfo>) UserServices.selectMyRoomies(user.getRoom(), user.getEmail());//getting other roommates
		
		logger.debug(list_roomy);
		ArrayList<TaskInfo> list_task = (ArrayList<TaskInfo>) TaskServices.retriveAllTasks(user.getRoom());
		
		logger.debug(list_task);
		ArrayList<TaskInfo> mytasks = (ArrayList<TaskInfo>) TaskServices.retrieveMyTasks(user.getEmail());
		logger.debug("Login:"+mytasks);
		
		ArrayList<MasterTaskInfo> masterTasks = (ArrayList<MasterTaskInfo>) MasterTaskServices.retrieveMasterTasks(user.getRoom());
		logger.debug(masterTasks);
		
		session.setAttribute("user", user);
		session.setAttribute("roomies", list_roomy);
		
		model.addObject("user", user);
		model.addObject("roomies", list_roomy);
		model.addObject("tasks", list_task);
		model.addObject("mytasks", mytasks);
		model.addObject("masterTasks", masterTasks);
		
		return model;
	}
	
	/**
	 * Accessed only when admin logs in into the system;
	 * fetches current system date, as well as timebox dates
	 * 
	 *  @return ModelAndView (redirects to admindashboard.jsp)
	 * */
	@RequestMapping("/admin**")
	public ModelAndView adminDashboard(){
		ModelAndView model = new ModelAndView("admindashboard");
		model.addObject("currentdate", SchedulingService.getCurrentSystemDate());
		model.addObject("timeboxstartdate", SchedulingService.getTimeboxstartDate());
		model.addObject("timeboxenddate", SchedulingService.getTimeboxendDate());
		return model;
	}
	
	/**
	 * Processes artificial dates set by administrator;
	 * Validates artificial dates;
	 * Calculates difference between current and artificial dates;
	 * Calls the functions used by SchedularService to perform updates of each day.
	 * 
	 * @param date 
	 * @return ModelAndView (redirects to admindashboard.jsp)
	 */
	@RequestMapping(value = "/fastforward", method= RequestMethod.POST)
	public ModelAndView fastforward(@RequestParam("date") String date){
		
		ModelAndView model = new ModelAndView("admindashboard");
		Date givenDate = DateManager.convertStringDate(date);
		Date currentSystemDate = DateManager.convertStringDate(SchedulingService.getCurrentSystemDate());
		TimeUnit timeunit = TimeUnit.DAYS;
		if(givenDate.compareTo(currentSystemDate)<=0){
			model.addObject("error", "Please enter a date after the systems current date");
		}
		else{
			int days = (int) timeunit.convert(givenDate.getTime() - currentSystemDate.getTime(), TimeUnit.MILLISECONDS);
			logger.debug("days:"+ days);
			for(int i=1; i<=days; i++){
				logger.debug("current date:"+currentSystemDate);
				//SchedulingService.setCurrentSystemDate(DateManager.recurring(SchedulingService.getCurrentSystemDate(), 1));
				SchedulingService.changeCurrentDate();;
			}
			model.addObject("error", "Done Successfully");
			model.addObject("currentdate", SchedulingService.getCurrentSystemDate());
			model.addObject("timeboxstartdate", SchedulingService.getTimeboxstartDate());
			model.addObject("timeboxenddate", SchedulingService.getTimeboxendDate());
		}
		return model;
	}
}	

