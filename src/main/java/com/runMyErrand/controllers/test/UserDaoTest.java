package com.runMyErrand.controllers.test;
import java.util.List;

import junit.framework.Assert;

//import org.jboss.tools.example.springmvc.domain.Member;
//import org.jboss.tools.example.springmvc.repo.MemberDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runMyErrand.dao.UserDao;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:test-context.xml",
//"classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
//@TransactionConfiguration(defaultRollback=true)
public class UserDaoTest {

	@Autowired
	private UserDao usr;
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
	}

	@Test
	public void testSetJdbcTemplate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSelectOne() {
		UserDao usd=new UserDao();
		String username="ss";
		Assert.assertEquals(null,usd.selectOne(username));//fail("Not yet implemented");
	}

	@Test
	public void testSelectRoomies() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInsertUserInfo() {
		//fail("Not yet implemented");
	}

}
