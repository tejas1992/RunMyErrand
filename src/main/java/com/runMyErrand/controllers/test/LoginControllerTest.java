/**
 * 
 */
package com.runMyErrand.controllers.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runMyErrand.controllers.LoginController;

/**
 * @author Purnima
 *
 */
public class LoginControllerTest {

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
	}
	@Test
	public void testRegister()
	{
		System.out.println("inside test 2");
		assert stLoginController.register()!=null:"the register return value is not null";

	}
	@Test
	public void testLogout()
	{
		System.out.println("inside test 3");
		assert stLoginController.logout()!=null:"the logout return valueis not null";
		
	}

}
