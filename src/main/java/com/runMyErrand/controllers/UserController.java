package com.runMyErrand.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.SchedulingService;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

/**
 * Controls the flow of user related information 
 * 
 */
@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	
	/**
	 * retrieves the information of a particular roommate 
	 * @param email
	 * 		email of roommate 
	 * @return
	 * 		redirects to member.jsp
	 */
	@RequestMapping(value = "/roomyinfo", method = RequestMethod.GET)
	public static ModelAndView roomyInfo(@RequestParam("email") String email) {

		logger.debug("Entered roomyinfo " + email);
		String current_date = SchedulingService.getCurrentSystemDate();
		ModelAndView model = new ModelAndView("member");
		ArrayList<TaskInfo> roomytask = (ArrayList<TaskInfo>) TaskServices.retrieveMyTasks(email);
		UserInfo user = UserServices.selectUser(email);
		
		model.addObject("current", current_date);
		model.addObject("roomytask", roomytask);
		model.addObject("roomy", user);
		
		logger.debug("roomyInfo:" + roomytask);

		return model;
	}
	
	
}
