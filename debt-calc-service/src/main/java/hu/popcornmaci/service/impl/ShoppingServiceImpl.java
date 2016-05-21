package hu.popcornmaci.service.impl;

/*
 * #%L
 * debt-calc-service
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
