/*
package com.runMyErrand.controllers.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.runMyErrand.dao.UserDao;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.UserServices;

@RunWith(MockitoJUnitRunner.class)
public class UserServicesTest {
	UserServices userservicestest;
	@Mock
	UserDao userDao;
	@Before
	public void setUp() throws Exception {

		userservicestest = new UserServices();
		userDao = new UserDao();
	}

	@Test
	public void testselectMyRoomies() {
		// fail("Not yet implemented");
		UserServices.setUserDao(userDao);
		ArrayList<String> newList = new ArrayList<String>();
		newList.add("jay");
		Mockito.when(userDao.selectRoomies("910N","jmmodi@indiana.edu")).thenReturn((List)newList);
		List<UserInfo> actual = UserServices.selectMyRoomies("910N",
				"jmmodi@indiana.edu");
		

		System.out.println(actual);
	}

}
*/