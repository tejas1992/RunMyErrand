package com.runMyErrand.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.TaskInfo;
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
	
	@RequestMapping("/logout")
	public String logout()
	{
		logger.debug("Entering /logout");
		return "signin";
	}
	
	@RequestMapping("/loginfailed")
	public ModelAndView loginfailed()
	{
		ModelAndView model = new ModelAndView("signin");
		model.addObject("error", "Invalid Username Or Password");
		return model;
	}
	
		
	@RequestMapping(value="/Register.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userinfo") UserInfo user)
	{
		UserServices.addUser(user);
		
		ModelAndView model = new ModelAndView("signin");
		model.addObject("user", user);
		
		return model;
	}
	
	@RequestMapping("/dashboard**")	
	public ModelAndView dashboard()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   // String name = auth.getName();
		
		String username = auth.getName();
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
	
	@RequestMapping(value = "/addtask.do", method = RequestMethod.POST)
	public ModelAndView addtask(@ModelAttribute("task") TaskInfo task) {
		logger.debug("Entering add task controller");
		logger.info(task.getTaskDescription());
		logger.info(task.getPoints());
		logger.info(task.getStartDate());
		logger.info(task.getEndDate());
		TaskServices.addTask(task);
		return new ModelAndView("forward:dashboard");

	}

}