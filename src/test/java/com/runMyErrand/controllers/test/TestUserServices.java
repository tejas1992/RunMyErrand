package com.runMyErrand.controllers.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.runMyErrand.dao.UserDao;
import com.runMyErrand.model.UserInfo;
import com.runMyErrand.services.UserServices;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserDao.class)

public class TestUserServices {

UserServices userservicestest;
	
	@Mock
	 UserDao userdao;
	ArrayList<UserInfo> newList = new ArrayList<UserInfo>();
	ArrayList<UserInfo> expected = new ArrayList<UserInfo>();
	UserInfo userinfo = new UserInfo();
	


	@Before
	public void setUp() throws Exception {
		userservicestest =new UserServices();
		PowerMockito.mockStatic(UserDao.class);
	}


	@Test
	public void testSelectUser() {
		UserInfo userinfo = new UserInfo();
		UserServices.setUserDao(userdao);
		PowerMockito.when(userdao.selectOne("njagasia@indiana.edu")).thenReturn(userinfo);
		UserInfo expecteduser = new UserInfo();
		expecteduser = UserServices.selectUser("njagasia@indiana.edu");
			assertEquals(expecteduser ,userinfo);
	}

	@Test
	public void testSelectMyRoomies() {
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Namrata");
		newList.add(userInfo);
		UserServices.setUserDao(userdao);
		PowerMockito.when(userdao.selectRoomies("910A", "jmmodi@indiana.edu"))
				.thenReturn(newList);
		expected = (ArrayList<UserInfo>) UserServices.selectMyRoomies("910A", "jmmodi@indiana.edu");
		UserInfo userInfo2 = new UserInfo();
		userInfo2 = expected.get(0);
		String firstName =  userInfo2.getFirstName();
		assertEquals(firstName, "Namrata");
	}


	@Test
	public void testUpdateUserScore() {
		PowerMockito.doNothing().when(userdao).setScore("njagasia@indiana.edu", 100);
		PowerMockito.doNothing().when(userdao).setPendingScore("njagasia@indiana.edu", 10);

	}

	@Test
	public void testUpdateWeeklyGoal() {
		
		PowerMockito.doNothing().when(userdao).updateGoal("shah_tejas92@yahoo.co.in", 10);
	}

	/*@Test
	public void testPendingScoresBatchUpdateStringFloat() {
		List<UserInfo> users = UserServices.selectMembers(room);
		for(int i=0; i<users.size(); i++){
			users.get(i).setPendingscore(users.get(i).getPendingscore()+points);
		}
		getUserDao().batchUpdatePendingScore(room, users);
		fail("Not yet implemented");
		
		
	}

	@Test
	public void testPendingScoresBatchUpdateString() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testSelectMembers() {
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Namrata");
		newList.add(userInfo);
		UserServices.setUserDao(userdao);
		PowerMockito.when(userdao.selectAll("910A")).thenReturn(newList);
		expected = (ArrayList<UserInfo>) UserServices.selectMembers("910A");
		UserInfo userInfo2 = new UserInfo();
		userInfo2 = expected.get(0);
		String firstName =  userInfo2.getFirstName();
		assertEquals(firstName, "Namrata");
	}

}
