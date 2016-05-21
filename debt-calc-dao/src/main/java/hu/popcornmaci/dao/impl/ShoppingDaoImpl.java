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
import hu.popcornmaci.dao.api.ShoppingDao;
import hu.popcornmaci.dao.entity.Shopping;

public class ShoppingDaoImpl implements ShoppingDao {

	@Override
	public Shopping save(Shopping entity) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.flush();
		manager.getTransaction().commit();
		return entity;
	}

	@Override
	public Shopping findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopping> findAll() {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<Shopping> query = manager.createQuery("SELECT sh from Shopping sh", Shopping.class);
		return query.getResultList();
	}

	@Override
	public List<Shopping> findByPersonId(Long id) {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<Shopping> query = manager.createQuery("SELECT sh from Shopping sh JOIN sh.pinfo pi where pi.person.id=:id",
				Shopping.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<Shopping> save(List<Shopping> entities) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		for (Shopping sh : entities) {
			manager.persist(sh);
		}
		manager.flush();
		manager.getTransaction().commit();
		return entities;
	}
}
