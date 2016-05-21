package hu.popcornmaci.dao.impl;

/*
 * #%L
 * debt-calc-dao
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
