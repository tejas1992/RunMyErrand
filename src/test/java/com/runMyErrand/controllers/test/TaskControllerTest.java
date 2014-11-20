/*package com.runMyErrand.controllers.test;
import org.mockito.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.controllers.TaskController;
import com.runMyErrand.model.TaskInfo;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.TaskServices;
import com.runMyErrand.services.UserServices;
@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

	@InjectMocks
	static TaskController taskcontroller;
	HttpSession session ;
	@Mock
	TaskServices mocktaskservices;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		taskcontroller=null;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testAssigntask() {
		//fail("Not yet implemented");
		System.out.println("in test assign task");
		String assignedto = "pp";
		String task = "task";
		HttpSession session = null ;
		ModelAndView model=new ModelAndView("forward:unassignedtask");
		//assert taskcontroller.assigntask(task, assignedto, session)== null:"return object not null";
		
	}
	@Test
	public void testeditMyTask()
	{
		System.out.println("editask test");
		//assert taskcontroller.editMyTask("test", session,"check complete")==null:"object is null";
		Assert.assertNull("to check the object is not null", taskcontroller);
		
		
	}
	@SuppressWarnings("static-access")*/
	/* The test returns null as the database does'nt hold values for assignedtasks*/
/*	@Test(expected=NullPointerException.class)
	public void testunassignedTasks()
	{
		System.out.println("editask test");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		String username = auth.getName();
		UserInfo user=(UserInfo)UserServices.selectUser(username);
		//assert taskcontroller.editMyTask("test", session,"check complete")==null:"object is null";
		@SuppressWarnings("rawtypes")
		ArrayList expected_unassignedtasks = (ArrayList) TaskServices.retrieveUnassignedTasks(user.getRoom());
		when(mocktaskservices.retrieveUnassignedTasks("910N")).thenReturn(expected_unassignedtasks);
	}*/
	/* The test returns null as the database does'nt hold values for assignedtasks*/
/*	@Test(expected=NullPointerException.class)
	public void testaddtash()
	{
		UserInfo user = (UserInfo)session.getAttribute("user");
		TaskInfo task = (TaskInfo)session.getAttribute("user");
		TaskServices.addTask(task, user.getRoom());
		ModelAndView model=new ModelAndView("forward:dashboard");
		assertEquals(model, mocktaskservices);
		assert model!=null :"object value returning not null";		
	}
	}
*/