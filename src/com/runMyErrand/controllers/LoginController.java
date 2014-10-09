package com.runMyErrand.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.dao.JdbcDaoImpl;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

@Controller
public class LoginController{

	//private static final Logger logger = Logger.getLogger(LoginController.class);
	
//	String username ="shah_tejas92@yahoo.co.in";
//	String password = "abcd";

	@RequestMapping("/")
	public ModelAndView login()
	{
	//	logger.debug("Entering Controller");
		System.out.println("From database the value is :"+ JdbcDaoImpl.getCircleCount());
		System.out.println("From database the name is :"+ JdbcDaoImpl.getName(1));
		ModelAndView model = new ModelAndView("signin");		
		return model;
	}
	@RequestMapping("/register")
	public ModelAndView register()
	{
	//	logger.debug("Entering Controller");
		System.out.println("From database the value is :"+ JdbcDaoImpl.getCircleCount());
		System.out.println("From database the name is :"+ JdbcDaoImpl.getName(1));
		ModelAndView model = new ModelAndView("register");		
		return model;
	}

	@RequestMapping(value="/LoginAuthentication.do", method = RequestMethod.POST)
	public ModelAndView authenticateUser(@RequestParam("email") String username, @RequestParam("password") String password){
		
		ModelAndView model = new ModelAndView("signin");
		String errormsg ="";
		ArrayList list_roomy=null;
		ArrayList list_task=null;
				
		UserInfo user = UserServices.selectUser(username); //retriever userdata for the one who is trying to login
				
		if (user!=null)//checking username and password
		{
			System.out.println("User retrieved");
			if(user.getPassword().equals(password))
			{
				System.out.println("password matched");
				model = new ModelAndView("Dashboard");
				list_roomy = (ArrayList) UserServices.selectMyRoomies(user.getRoom(), user.getFirstName());//getting other roommates
				System.out.println(list_roomy);
				list_task = (ArrayList) TaskServices.retriveAllTasks();
				System.out.println(list_task);
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
		model.addObject("user", user);
		model.addObject("roomies", list_roomy);
		model.addObject("tasks", list_task);
						
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
}