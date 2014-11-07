package com.runMyErrand.controllers.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.runMyErrand.logic.DateManager;

public class DateManagerTest {
	DateManager dateManagertest;
	@Before
	public void setUp() throws Exception {
		dateManagertest = new DateManager(); 
	}

	@Test
	public void testRecurring() throws ParseException {
		String resultWeekly = dateManagertest.recurring("2014-11-30", "Weekly");
//		assertEquals("2014-12-30",resultWeekly);
		String resultMonthly = dateManagertest.recurring("2014-11-30", "Monthly");
		assertEquals("2014-12-30",resultMonthly);
	}


}
