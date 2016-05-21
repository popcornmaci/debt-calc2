package hu.popcornmaci.service.api;

import hu.popcornmaci.dao.entity.Person;

public interface LoginService {
	public Person login(String username, String passw) throws LoginException;
}
