package hu.popcornmaci.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.impl.PersonDaoImpl;
import hu.popcornmaci.service.api.RegException;
import hu.popcornmaci.service.api.RegService;

public class RegServiceImpl implements RegService{

	private PersonDao dao = new PersonDaoImpl();
	
	@Override
	public void register(String fullName, String username, String passw) throws RegException {
		String hashed = BCrypt.hashpw(passw, BCrypt.gensalt(12));
		Person person = dao.findByUsername(username);
		if(person!=null){
			throw new RegException("Már létezik ilyen felhasználó");
		}
		person= new Person(fullName, username, hashed);
		dao.save(person);
	}

}
