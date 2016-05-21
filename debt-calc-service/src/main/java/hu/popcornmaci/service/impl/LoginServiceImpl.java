package hu.popcornmaci.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.impl.PersonDaoImpl;
import hu.popcornmaci.service.api.LoginException;
import hu.popcornmaci.service.api.LoginService;

public class LoginServiceImpl implements LoginService {

	private PersonDao dao = new PersonDaoImpl();
	
	@Override
	public Person login(String username, String passw) throws LoginException {
		Person person = dao.findByUsername(username);
		if(person==null || !BCrypt.checkpw(passw, person.getPassword())){
			throw new LoginException("Rossz felhasználónév/jelszó");
		}
		return person;
	}

}
