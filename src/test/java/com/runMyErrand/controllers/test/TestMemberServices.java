package com.runMyErrand.controllers.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.runMyErrand.dao.MemberDao;
import com.runMyErrand.services.MemberServices;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MemberDao.class)
public class TestMemberServices {
	
	MemberServices testmemberservices;
	
	@Mock
	MemberDao memberdao;

	@Before
	public void setUp() throws Exception {
		testmemberservices = new MemberServices();
		PowerMockito.mockStatic(MemberDao.class);	}

	@Test
	public void testUpdatePoints() {
		
		PowerMockito.doNothing().when(memberdao).updateTotalPoints(10, "910A");
	}


}
