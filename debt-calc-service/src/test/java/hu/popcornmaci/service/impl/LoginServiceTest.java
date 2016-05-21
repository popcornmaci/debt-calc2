package hu.popcornmaci.service.impl;

/*
 * #%L
 * debt-calc-service
 * %%
 * Copyright (C) 2016 Faculty of Informatics, University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
