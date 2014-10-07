package com.runMyErrand.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.dao.JdbcDaoImpl;
import com.runMyErrand.model.Login;

@Controller
public class LoginController{

	String username ="shah_tejas92@yahoo.co.in";
	String password = "abcd";

	@RequestMapping("/")
	public ModelAndView login()
	{
		System.out.println("From database the value is :"+ JdbcDaoImpl.getCircleCount());
		System.out.println("From database the name is :"+ JdbcDaoImpl.getName(1));
		System.out.println("Hello");
		ModelAndView model = new ModelAndView("signin");		
		return model;
	}
	
	@RequestMapping(value="/LoginAuthentication.do", method = RequestMethod.POST)
	public ModelAndView authenticateUser(@ModelAttribute("user1") Login user1){
		
		ModelAndView model = new ModelAndView("signin");
		String errormsg ="";
		
		if (user1.getEmail().equalsIgnoreCase(username))
		{
			if(user1.getPassword().equals(password))
			{
				model = new ModelAndView("Dashboard");
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
				
		return model;
		
	}
	

}