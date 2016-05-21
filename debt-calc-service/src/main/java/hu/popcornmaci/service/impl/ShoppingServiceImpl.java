package hu.popcornmaci.service.impl;

import hu.popcornmaci.dao.api.ItemDao;
import hu.popcornmaci.dao.api.PayInfoDao;
import hu.popcornmaci.dao.api.ShoppingDao;
import hu.popcornmaci.dao.entity.Shopping;
import hu.popcornmaci.dao.impl.ItemDaoImpl;
import hu.popcornmaci.dao.impl.PayInfoDaoImpl;
import hu.popcornmaci.dao.impl.ShoppingDaoImpl;
import hu.popcornmaci.service.api.ShoppingService;

public class ShoppingServiceImpl implements ShoppingService {
	
	private ShoppingDao shd= new ShoppingDaoImpl();
	private ItemDao id = new ItemDaoImpl();
	private PayInfoDao pid = new PayInfoDaoImpl();
	@Override
	public Shopping save(Shopping sh) {
		id.save(sh.getItems());
		shd.save(sh);
		pid.save(sh.getPinfo());

		return sh;
	}

}
