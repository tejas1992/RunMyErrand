/*******
 * 
 */
package com.runMyErrand.controllers.test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.AssertionFailedException;
import com.runMyErrand.controllers.DashboardController;
import com.runMyErrand.controllers.LoginController;
import com.runMyErrand.model.Login;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.UserServices;
@RunWith(MockitoJUnitRunner.class)
/**
 * @author Purnima
 *
 */
public class LoginControllerTest {
	@InjectMocks
	static
	LoginController logincon;
	@Mock
	UserServices userserv;
	static LoginController stLoginController=null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stLoginController=new LoginController();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		stLoginController=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.runMyErrand.controllers.LoginController#login()}.
	 */
	@Test
	public void testLogin() {
		//
		System.out.println("inside test case 1");
		assert stLoginController.login()!=null:"The Object is not null";
		ModelAndView mav =logincon.login();
		assert mav!=null :"returning object passes";
		
	}
	
	/*@Test
	public void testRegister()
	{
		System.out.println("inside test 2");
		assert stLoginController.register()!=null:"the register return value is not null";

	}*/
	@Test
	public void testLogout()
	{
		System.out.println("inside test 3");
		assert stLoginController.logout()!=null:"the logout return valueis not null";
		
	}
	/*@RunWith(SpringJUnit4ClassRunner.class)
	@WebAppConfiguration
	@Configuration(locations= {"file:src/main/webapp/WEB-INF/spring-dispatcher-servlet.xml","file:src/main/webapp/WEB-INF/web.xml"})*/
	@SuppressWarnings("static-access")
	/*the signup will return an exception as we there is no database*/
	@Test
	public void testsignup()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		UserInfo user = (UserInfo)UserServices.selectUser(username);
		System.out.println("inside test signup ");
		ModelAndView model = new ModelAndView("signin");
		assertEquals("shah_tejas92@yahoo.co.in","abcd");
		assert stLoginController.signup(user,"abcd" )!=null:"the logout return valueis not null";
		/*ModelAndView model = new ModelAndView("signin");
		String success = UserServices.addUser(user, "aa");
		when(userserv.addUser(user, null)).thenReturn(model);*/
				
	}
	
	public void testloginfailed()
	{
		ModelAndView model = new ModelAndView("signin");
		model.addObject("error", "Invalid Username Or Password");
		assert model!=null :"checking for login null";
		
	}
}
