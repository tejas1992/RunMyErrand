package com.runMyErrand.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
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
	
		
	@RequestMapping(value = "/Register.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userinfo") UserInfo user,
			@RequestParam("password") String password) {
		
		ModelAndView model = new ModelAndView("signin");
		String success = UserServices.addUser(user, password);
		
		if (success != null) {
			model.addObject("error", success);
		} 
		else {
			model.addObject("message", "Registration Successful. Please Login");
			model.addObject("user", user);
		}
		return model;
	}
}