package hu.popcornmaci.dao.impl;

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
