package com.runMyErrand.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

@Controller
@SessionAttributes("user")
@SuppressWarnings("unchecked")
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(DashboardController.class);
	
	/* /dashboard fetches all the necessary data which is required in the session it is redirected to 
	 * Dashboard.jsp. It sets the session attributes as well as the tasks of the user as well as his roommates */
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
		
		session.setAttribute("user", user);
		session.setAttribute("roomies", list_roomy);
		
		model.addObject("user", user);
		model.addObject("roomies", list_roomy);
		model.addObject("tasks", list_task);
		model.addObject("mytasks", mytasks);
				
		return model;
	}
}	

