package hu.popcornmaci.dao.api;

import java.util.List;

import hu.popcornmaci.dao.entity.Shopping;

public interface ShoppingDao extends BaseDao<Shopping>{

	List<Shopping> findByPersonId(Long id);
	
}
