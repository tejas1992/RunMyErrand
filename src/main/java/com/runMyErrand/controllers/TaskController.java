package com.runMyErrand.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;

@Controller

public class TaskController {
	
	private static final Logger logger = Logger.getLogger(TaskController.class);
	
	@RequestMapping(value="/unassignedtask")
	public ModelAndView unassignedTasks(HttpSession session){
		ModelAndView model= new ModelAndView("unassignedtasks");
		ArrayList<UserInfo>roomies = (ArrayList<UserInfo>) session.getAttribute("roomies");
		UserInfo user = (UserInfo)session.getAttribute("user");
		ArrayList unassignedtasks = (ArrayList) TaskServices.retrieveUnassignedTasks(user.getRoom());
		logger.debug(unassignedtasks);
		model.addObject("roomies", roomies);
		model.addObject("unassigned", unassignedtasks);
		return model;
	}
	
	@RequestMapping(value="/Assigntask.do" ,method = RequestMethod.POST)
	public ModelAndView assigntask(@RequestParam("task") String task, @RequestParam("assigned") String assignedto,
			HttpSession session)
	{
		logger.debug("task: "+task);
		logger.debug("assignedto: "+assignedto);
		UserInfo user = (UserInfo)session.getAttribute("user");
		TaskServices.assignTask(task, assignedto, user.getRoom());
		return new ModelAndView("forward:unassignedtask");
	}
	
	@RequestMapping(value = "/addtask.do", method = RequestMethod.POST)
	public ModelAndView addtask(@ModelAttribute("task") TaskInfo task,HttpSession session) {
		logger.debug("Entering add task controller");
		logger.info(task.getTaskDescription());
		logger.info(task.getPoints());
		UserInfo user = (UserInfo)session.getAttribute("user");
		TaskServices.addTask(task, user.getRoom());
		return new ModelAndView("forward:dashboard");

	}
	
	@RequestMapping(value="/edittask", method = RequestMethod.POST)
    public ModelAndView editMyTask(@RequestParam("taskDescription")String Description,
            HttpSession session, @RequestParam("completed")String completed ){
		
		logger.debug("Entering edittask");
        int status = -1;
        ModelAndView model = new ModelAndView("forward:dashboard");
        if(completed.equalsIgnoreCase("done")){
            status = 1;
        }
        else{
            status = 0;
        }
        UserInfo user = (UserInfo)session.getAttribute("user"); 
        TaskServices.updateTaskStatus(Description, user.getRoom(), status);
        
        return model;
    }

	public Object editMyTask(Object description, Object session, Object sess) {
		// TODO Auto-generated method stub
		return null;
	}
}
