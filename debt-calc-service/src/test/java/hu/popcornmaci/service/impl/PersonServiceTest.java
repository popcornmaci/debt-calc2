package hu.popcornmaci.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import hu.popcornmaci.dao.api.PersonDao;

public class PersonServiceTest {

	@Test
	public void testGetPerson() {
		PersonDao dao=Mockito.mock(PersonDao.class);
		Mockito.when(dao.findByUsername("")).thenReturn(null);
		Assert.assertNull(new PersonServiceImpl(dao).getPerson(""));
	}

}
