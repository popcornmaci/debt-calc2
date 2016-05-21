package hu.popcornmaci.dao.api;

import java.util.List;

import hu.popcornmaci.dao.entity.Person;

public interface PersonDao extends BaseDao<Person>{
	
	List<Person> findByFullName(String name);
	Person findByUsername(String name);
}
