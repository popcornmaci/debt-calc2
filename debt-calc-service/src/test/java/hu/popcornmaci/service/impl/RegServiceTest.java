package hu.popcornmaci.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.service.api.RegException;
import hu.popcornmaci.service.api.RegService;

public class RegServiceTest {

	@Test
	public void testRegSuccessful() throws RegException {
		PersonDao daoMock = mock(PersonDao.class);
		Person p= new Person();
		when(daoMock.findByUsername(p.getUsername())).thenReturn(null);
		RegService ps = new RegServiceImpl(daoMock);
		ps.register(p.getFullName(), p.getUsername(), p.getPassword());
	}
	@Test(expected=RegException.class)
	public void testRegFailed() throws RegException {
		PersonDao daoMock = mock(PersonDao.class);
		Person p= new Person();
		when(daoMock.findByUsername(p.getUsername())).thenReturn(new Person());
		RegService ps = new RegServiceImpl(daoMock);
		ps.register(p.getFullName(), p.getUsername(), p.getPassword());
	}

}
