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
