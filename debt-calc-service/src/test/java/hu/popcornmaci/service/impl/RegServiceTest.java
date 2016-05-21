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
