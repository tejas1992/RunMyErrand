package com.runMyErrand.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.services.TaskServices;

@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/roomyinfo", method = RequestMethod.GET)
	public static ModelAndView roomyInfo(@RequestParam("email") String email, @RequestParam("name") String name){
		
		logger.debug("Entered roomyinfo "+ email);
		
		ModelAndView model = new ModelAndView("member");
		ArrayList roomytask = (ArrayList) TaskServices.retrieveMyTasks(email);
		logger.debug(name);
		model.addObject("roomyName",name);
		model.addObject("roomytask", roomytask);
		logger.debug("roomyInfo:" + roomytask);
		
		return model;
	}
	
	
}
