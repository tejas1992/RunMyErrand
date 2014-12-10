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
import com.runMyErrand.services.SchedulingService;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

/**
 *  The class is a controller that manages all the login functionalities
 *  Spring-security.xml is configured to use this controller to manage the control flow while user logs in
 *  
 *  @author Tejas
 *  */
@Controller
@SessionAttributes("user")
public class LoginController{
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * As soon as the application starts this method is called
	 *  
	 * @return redirects to signin page where login credentials are required
	 */
	@RequestMapping("/")
	public ModelAndView login()
	{
		SchedulingService.setCurrentSystemDate(TaskServices.getTaskDao().getCurrentSystemDate());
		SchedulingService.setTimeboxstartDate(TaskServices.getTaskDao().getTimeboxStartDate());
		SchedulingService.setTimeboxendDate(TaskServices.getTaskDao().getTimeboxEndDate());
		logger.debug("Entering Controller");
		ModelAndView model = new ModelAndView("signin");		
		return model;
	}
	
	/**
	 * Logs out the user 
	 * @return String (redirects to sigin page)
	 */
	@RequestMapping("/logout")
	public String logout()
	{
		logger.debug("Entering /logout");
		return "signin";
	}
	
	
	
	/**
	 * Displays error if user puts invalid credentials
	 * 
	 * @return ModelAndView (redirects to sigin page)
	 */
	@RequestMapping("/loginfailed")
	public ModelAndView loginfailed()
	{
		ModelAndView model = new ModelAndView("signin");
		model.addObject("error", "Invalid Username Or Password");
		return model;
	}
	
	
	/**
	 * Redirect to customized access Denied page.
	 * 
	 * @return ModelAndView (redirects to accessdenied store)
	 */
	@RequestMapping("/403")
	public ModelAndView accessDenied(){
		ModelAndView model = new ModelAndView("accessdenied");
		return model;
	}
	
		
	/**
	 * Validates new user;
	 * Updates database when a new user registers.
	 * 
	 * @param user Sets the information of new registered user into UserInfo
	 * @param password password entered by the user
	 * @return ModelAndView (Redirects to signin page)
	 */
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
			UserServices.pendingScoresBatchUpdate(user.getRoom());
			
		}
		return model;
	}
}