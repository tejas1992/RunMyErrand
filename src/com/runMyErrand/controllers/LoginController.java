package com.runMyErrand.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

@Controller
@SessionAttributes("user")
public class LoginController{

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("/")
	public ModelAndView login()
	{
		logger.debug("Entering Controller");
		ModelAndView model = new ModelAndView("signin");		
		return model;
	}
	@RequestMapping("/register")
	public ModelAndView register()
	{
		ModelAndView model = new ModelAndView("register");		
		return model;
	}

	@RequestMapping(value="/LoginAuthentication.do", method = RequestMethod.POST)
	public ModelAndView authenticateUser(@RequestParam("email") String username, @RequestParam("password") String password){
		
		ModelAndView model = new ModelAndView("signin");
		String errormsg ="";
		UserInfo user1 = UserServices.selectUser(username); 
				
		if (user1!=null)
		{
			logger.debug("User retrieved");
			if(user1.getPassword().equals(password))
			{
				model.addObject("user", user1);
			//	model.addObject("email", )
				logger.debug("password matched");
				model = new ModelAndView("forward:dashboard");
			}
			else
			{
				errormsg = "Incorrect password";
			}
		}
		else
		{
			errormsg = "Email Not found";
		}
		model.addObject("error", errormsg);		
		logger.debug("leaving controller");				
		return model;
	}
	
	@RequestMapping("/dashboard")	
	public ModelAndView dashboard(@RequestParam("email") String username)
	{
		logger.debug("Entered dashboard"+ username);
		UserInfo user = UserServices.selectUser(username); 
		
		ArrayList list_roomy = (ArrayList) UserServices.selectMyRoomies(user.getRoom(), user.getFirstName());//getting other roommates
		logger.debug(list_roomy);
		ArrayList list_task = (ArrayList) TaskServices.retriveAllTasks();
		logger.debug(list_task);
		ArrayList mytasks = (ArrayList) TaskServices.retrieveMyTasks(user.getFirstName());
		logger.debug(mytasks);
		ArrayList unassignedtasks = (ArrayList) TaskServices.retrieveUnassignedTasks();
		logger.debug(unassignedtasks);
		ModelAndView model = new ModelAndView("Dashboard");

	
		model.addObject("user", user);
		model.addObject("roomies", list_roomy);
		model.addObject("tasks", list_task);
		model.addObject("mytasks", mytasks);
		model.addObject("unassigned", unassignedtasks);
		
		return model;
	}
	
	@RequestMapping(value="/Assigntask.do" ,method = RequestMethod.POST)
	public ModelAndView assigntask(@RequestParam("task") String task, @RequestParam("assigned") String assignedto)
	{
		logger.debug("task: "+task);
		logger.debug("assignedto: "+assignedto);
		TaskServices.assignTask(task, assignedto);
		return new ModelAndView("forward:dashboard");
	}
	
	@RequestMapping(value="/Register.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userinfo") UserInfo user)
	{
		UserServices.addUser(user);
		
		ModelAndView model = new ModelAndView("signin");
		model.addObject("user", user);
		
		return model;
	}
}