package hu.popcornmaci.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import hu.popcornmaci.dao.EFMManager;
import hu.popcornmaci.dao.api.PersonDao;
import hu.popcornmaci.dao.entity.Person;

public class PersonDaoImpl implements PersonDao {

	@Override
	public Person save(Person entity) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.flush();
		manager.getTransaction().commit();
		return entity;
	}

	@Override
	public Person findById(Long id) {
		return null;
	}

	@Override
	public List<Person> findAll() {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<Person> query = manager.createQuery("SELECT p from Person p", Person.class);
		return query.getResultList();
	}

	@Override
	public List<Person> findByFullName(String name) {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<Person> query = manager.createQuery("SELECT p from Person p where p.fullName=:fname", Person.class);
		query.setParameter("fname", name);
		return query.getResultList();
	}

	@Override
	public Person findByUsername(String name) {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<Person> query = manager.createQuery("SELECT p from Person p where p.username=:uname", Person.class);
		query.setParameter("uname", name);
		List<Person> list = query.getResultList();
		return list.size() == 0 ? null : list.get(0);
	}

	@Override
	public List<Person> save(List<Person> entities) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		for (Person person : entities) {
			manager.persist(person);
		}
		manager.flush();
		manager.getTransaction().commit();
		return entities;
	}

}
