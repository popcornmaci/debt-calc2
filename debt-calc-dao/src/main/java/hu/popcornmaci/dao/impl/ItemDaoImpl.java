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
import hu.popcornmaci.dao.api.ItemDao;
import hu.popcornmaci.dao.entity.Item;

public class ItemDaoImpl implements ItemDao {

	@Override
	public Item save(Item entity) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.flush();
		manager.getTransaction().commit();
		return entity;
	}

	@Override
	public Item findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<Item> query = manager.createQuery("SELECT i from Item i", Item.class);
		return query.getResultList();
	}

	@Override
	public List<Item> save(List<Item> entities) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		for (Item item : entities) {			
			manager.persist(item);
		}
		manager.flush();
		manager.getTransaction().commit();
		return entities;
	}

}
