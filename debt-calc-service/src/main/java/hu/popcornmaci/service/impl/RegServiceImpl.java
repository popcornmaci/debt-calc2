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


import org.mindrot.jbcrypt.BCrypt;

import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.impl.PersonDaoImpl;
import hu.popcornmaci.service.api.RegException;
import hu.popcornmaci.service.api.RegService;

public class RegServiceImpl implements RegService{

	private PersonDao dao;
	
	public RegServiceImpl() {
		dao=new PersonDaoImpl();
	}
	
	public RegServiceImpl(PersonDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Person register(String fullName, String username, String passw) throws RegException {
		String hashed = BCrypt.hashpw(passw, BCrypt.gensalt(12));
		Person person = dao.findByUsername(username);
		if(person!=null){
			throw new RegException("Már létezik ilyen felhasználó");
		}
		person= new Person(fullName, username, hashed);
		dao.save(person);
		return person;
	}

}
