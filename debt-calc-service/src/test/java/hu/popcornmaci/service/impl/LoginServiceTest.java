package hu.popcornmaci.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.service.api.LoginException;
import hu.popcornmaci.service.api.RegException;
import hu.popcornmaci.service.api.RegService;

public class LoginServiceTest {
	private static Person p;

	@BeforeClass
	public static void setup() throws RegException {
		PersonDao daoMock = mock(PersonDao.class);
		when(daoMock.findByUsername("alma")).thenReturn(null);
		RegService ps = new RegServiceImpl(daoMock);
		p = ps.register("alma", "alma", "korte");

	}

	@Test
	public void testLoginSuccessful() throws LoginException {
		PersonDao dao = mock(PersonDao.class);
		when(dao.findByUsername("alma")).thenReturn(p);
		new LoginServiceImpl(dao).login("alma", "korte");
	}

	@Test(expected = LoginException.class)
	public void testLoginFailedUsername() throws LoginException {
		PersonDao dao = mock(PersonDao.class);
		when(dao.findByUsername("alma")).thenReturn(null);
		new LoginServiceImpl(dao).login("alma", "korte");
	}

	@Test(expected = LoginException.class)
	public void testLoginFailedPassword() throws LoginException {
		PersonDao dao = mock(PersonDao.class);
		when(dao.findByUsername("alma")).thenReturn(p);
		new LoginServiceImpl(dao).login("alma", "korte2");
	}

}
