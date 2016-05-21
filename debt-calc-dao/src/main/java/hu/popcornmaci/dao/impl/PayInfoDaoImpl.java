package hu.popcornmaci.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import hu.popcornmaci.dao.EFMManager;
import hu.popcornmaci.dao.api.PayInfoDao;
import hu.popcornmaci.dao.entity.PayInfo;

public class PayInfoDaoImpl implements PayInfoDao {

	@Override
	public PayInfo save(PayInfo entity) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.flush();
		manager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<PayInfo> save(List<PayInfo> entities) {
		EntityManager manager = EFMManager.getManager();
		manager.getTransaction().begin();
		for (PayInfo info : entities) {
			manager.persist(info);
		}
		manager.flush();
		manager.getTransaction().commit();
		return entities;
	}

	@Override
	public PayInfo findById(Long id) {
		return null;
	}

	@Override
	public List<PayInfo> findAll() {
		EntityManager manager = EFMManager.getManager();
		TypedQuery<PayInfo> query = manager.createQuery("SELECT info FROM PayInfo info", PayInfo.class);
		return query.getResultList();
	}

//	@Override
//	public List<PayInfo> findByShoppingId(Long id) {
//		EntityManager manager = EFMManager.getManager();
//		TypedQuery<PayInfo> query = manager.createQuery("SELECT i FROM Shopping sh JOIN sh.pinfo i WHERE sh.id=:id", PayInfo.class);
//		query.setParameter("id", id);
//		return query.getResultList();
//	}
//
//	@Override
//	public List<PayInfo> findByShoppingIdAndUsername(Long id, String username) {
//		EntityManager manager = EFMManager.getManager();
//		TypedQuery<PayInfo> query = manager.createQuery("SELECT i FROM Shopping sh JOIN sh.pinfo i WHERE sh.id=:id", PayInfo.class);
//		query.setParameter("id", id);
//		return query.getResultList();
//	}

}
