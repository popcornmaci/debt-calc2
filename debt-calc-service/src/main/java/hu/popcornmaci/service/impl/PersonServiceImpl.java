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


import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.impl.PersonDaoImpl;
import hu.popcornmaci.service.api.PersonService;

public class PersonServiceImpl implements PersonService{
	
	private PersonDao dao = new PersonDaoImpl();
	public PersonServiceImpl() {
	}
	public PersonServiceImpl(PersonDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Person getPerson(String user) {
		Person person = dao.findByUsername(user);
		return person;
	}

}
