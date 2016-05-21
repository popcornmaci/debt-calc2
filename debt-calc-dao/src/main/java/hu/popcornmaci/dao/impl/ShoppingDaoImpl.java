package hu.popcornmaci.dao.impl;

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
