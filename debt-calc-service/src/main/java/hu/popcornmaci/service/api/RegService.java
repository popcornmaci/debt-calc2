package hu.popcornmaci.service.api;

import hu.popcornmaci.dao.entity.Person;

public interface RegService {
	public Person register(String fullName,String username, String passw) throws RegException;
	
}
