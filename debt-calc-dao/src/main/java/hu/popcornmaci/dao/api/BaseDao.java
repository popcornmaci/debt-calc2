package hu.popcornmaci.dao.api;

import java.util.List;

import hu.popcornmaci.dao.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity> {

	T save(T entity);
	List<T> save(List<T> entities);
	T findById(Long id);
	List<T> findAll();
	
}
