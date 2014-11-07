package com.runMyErrand.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.MemberServices;
import com.runMyErrand.services.UserServices;

/* This controller manages all the login functionalities*/
@Controller
@SessionAttributes("user")
public class LoginController{

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	//login() redirects directly to the signin page
	@RequestMapping("/")
	public ModelAndView login()
	{
		logger.debug("Entering Controller");
		ModelAndView model = new ModelAndView("signin");		
		return model;
	}
	
	//redirects to sigin page when logged out
	@RequestMapping("/logout")
	public String logout()
	{
		logger.debug("Entering /logout");
		return "signin";
	}
	
	//@RequestMapping
	
	//displays a error message when login is failed
	@RequestMapping("/loginfailed")
	public ModelAndView loginfailed()
	{
		ModelAndView model = new ModelAndView("signin");
		model.addObject("error", "Invalid Username Or Password");
		return model;
	}
	
	//handles the registration control and displays the necessary message	
	@RequestMapping(value = "/Register.do", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userinfo") UserInfo user,
			@RequestParam("password") String password) {
		
		ModelAndView model = new ModelAndView("signin");
		logger.debug("adding user");
		String success = UserServices.addUser(user, password);
		
		if (success != null) {
			model.addObject("error", success);
		} 
		else {
			model.addObject("message", "Registration Successful. Please Login");
			model.addObject("user", user);
			MemberServices.updateMembers(user);
			
		}
		return model;
	}
}