package hu.popcornmaci.service.impl;

import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.impl.PersonDaoImpl;
import hu.popcornmaci.service.api.PersonService;

public class PersonServiceImpl implements PersonService{
	
	private PersonDao dao = new PersonDaoImpl();
	
	@Override
	public Person getPerson(String user) {
		Person person = dao.findByUsername(user);
		return person;
	}

}
