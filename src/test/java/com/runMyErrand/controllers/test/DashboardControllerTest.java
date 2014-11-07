package com.runMyErrand.controllers.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runMyErrand.controllers.DashboardController;

public class DashboardControllerTest {
	DashboardController dashcon=new DashboardController();
	HttpSession session;
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
		dashcon=null;
	}

	@Test
	public void testDashboard() {
		//fail("Not yet implemented");
		System.out.println("inside dash");
		assert dashcon.dashboard(session)!=null:"session does not return null";
			
			
	}

}
