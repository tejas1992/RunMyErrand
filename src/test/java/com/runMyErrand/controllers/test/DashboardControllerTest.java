package com.runMyErrand.controllers.test;
import static org.junit.Assert.*;

import java.lang.Object;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import com.runMyErrand.controllers.DashboardController;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;

import static org.mockito.Mockito.*;

import org.junit.runner.Runner;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
@RunWith(MockitoJUnitRunner.class)
public class DashboardControllerTest {
	@InjectMocks
	static
	DashboardController dashcon;
	@Mock
	UserServices mockUserServices;
	@Mock
	TaskServices mockTaskServices;
	HttpSession session;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	


	@Before
	public void setUp() throws Exception {
		 // Process mock annotations
        MockitoAnnotations.initMocks(this);
    
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@SuppressWarnings("static-access")
	@Test(expected=NullPointerException.class)
	public void testDashboard() {
		//fail("Not yet implemented");
/*		System.out.println("inside dash");
		assert dashcon.dashboard(session)!=null:"session does not return null";*/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   		String username = auth.getName();
		UserInfo user = (UserInfo)UserServices.selectUser(username);
		TaskInfo user1 = (TaskInfo) TaskServices.retriveAllTasks(user.getRoom());
		TaskInfo user2 = (TaskInfo) TaskServices.retrieveMyTasks(user.getEmail());
		ArrayList<UserInfo> expected_list_roomy = (ArrayList<UserInfo>) UserServices.selectMyRoomies(user.getRoom(), user.getEmail());
		@SuppressWarnings("unchecked")
		ArrayList<TaskInfo> expected_list_task=(ArrayList<TaskInfo>) TaskServices.retriveAllTasks(user1.getRoom());
		@SuppressWarnings("unchecked")
		ArrayList<TaskInfo> expected_mytasks=(ArrayList<TaskInfo>) TaskServices.retrieveMyTasks("shah_tejas92@yahoo.co.in");
		//when(mockUserServices.selectMyRoomies(user.getRoom(),user.getEmail())).thenReturn(expected_list_roomy);
		when(mockUserServices.selectMyRoomies("910N","shah_tejas92@yahoo.co.in")).thenReturn(expected_list_roomy);
		when(mockTaskServices.retriveAllTasks("910N")).thenReturn(expected_list_task);
		when(mockTaskServices.retrieveMyTasks("shah_tejas92@yahoo.co.in")).thenReturn(expected_mytasks);
		//System.out.println("inside dash test"+ expected_list_roomy);
		ModelAndView model=new ModelAndView("Dashboard");
		 ModelAndView model2= dashcon.dashboard(session);
		 assert model==model2:"expected equal to actual";	 
	}
}
