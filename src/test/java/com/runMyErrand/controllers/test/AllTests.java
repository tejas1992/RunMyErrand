package com.runMyErrand.controllers.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginControllerTest.class,TaskControllerTest.class,UserControllerTest.class,UserDaoTest.class})
public class AllTests {

	 
}
