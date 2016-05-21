package hu.popcornmaci.gui;

import hu.popcornmaci.dao.entity.Person;

public class LoginInfo {
	private static Person person;

	public static Person getPerson() {
		return person;
	}

	public static void setPerson(Person person) {
		LoginInfo.person = person;
	}
	private LoginInfo() {
	}
	
}
