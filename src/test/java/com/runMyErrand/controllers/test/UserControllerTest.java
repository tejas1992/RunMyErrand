/*package com.runMyErrand.controllers.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.runMyErrand.controllers.UserController;
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	static UserController usrcon;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	

	@After
	public void tearDown() throws Exception {
		usrcon=null;
	}

	@Test
	public void testRoomyInfo() {
		//fail("Not yet implemented");
		String  email="pp";
		ModelAndView model = new ModelAndView("NewFile");
		assert usrcon.roomyInfo(email)!=null:"roomyinfo not equal to null";
		assert model!=null :"model returned is not empty";
	}

}
	*/