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


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import hu.popcornmaci.dao.api.ShoppingDao;
import hu.popcornmaci.dao.entity.Item;
import hu.popcornmaci.dao.entity.PayInfo;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.entity.Shopping;

public class DebtServiceTest {
	private static LocalDate date1 = LocalDate.of(2016, 5, 21);
	private static LocalDate date2 = LocalDate.of(2016, 5, 18);
	private static Person p1 = new Person("alma", "alma", "korte");
	private static Person p2 = new Person("banan", "banan", "meggy");
	private static Shopping sh1;
	private static Shopping sh2;

	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sh1 = new Shopping();
		List<PayInfo> pinfo = Arrays.asList(new PayInfo(sh1, p1, 500.0), new PayInfo(sh1, p2, 500.0));
		List<Item> items = Arrays.asList(new Item("", 800.0, Arrays.asList(p1, p2)),
				new Item("", 200.0, Arrays.asList(p1)));
		sh1.setItems(items);
		sh1.setPinfo(pinfo);
		sh1.setShoppingDate(toDate(date1));

		sh2 = new Shopping();
		List<PayInfo> pinfo2 = Arrays.asList(new PayInfo(sh2, p1, 300.0), new PayInfo(sh2, p2, 500.0));
		List<Item> items2 = Arrays.asList(new Item("", 400.0, Arrays.asList(p1, p2)),
				new Item("", 400.0, Arrays.asList(p1)));
		sh2.setItems(items2);
		sh2.setPinfo(pinfo2);
		sh2.setShoppingDate(toDate(date2));
	}

	@Test
	public void testNoShopping() {
		LocalDate date = LocalDate.of(2016, 5, 20);
		ShoppingDao dao = Mockito.mock(ShoppingDao.class);
		Mockito.when(dao.findByPersonId(p1.getId())).thenReturn(Arrays.asList(sh1,sh2));
		DebtServiceImpl dsi = new DebtServiceImpl(dao);
		Assert.assertTrue(dsi.getDebtFrom(p1, date, date).isEmpty());
		Assert.assertTrue(dsi.getDebtTo(p1, date, date).isEmpty());
	}
	@Test
	public void testOneShopping1() {
		LocalDate date = date1;
		ShoppingDao dao = Mockito.mock(ShoppingDao.class);
		Mockito.when(dao.findByPersonId(p1.getId())).thenReturn(Arrays.asList(sh1,sh2));
		DebtServiceImpl dsi = new DebtServiceImpl(dao);
		Assert.assertTrue(dsi.getDebtFrom(p1, date, date).isEmpty());
		Assert.assertEquals(100.0, dsi.getDebtTo(p1, date, date).get(p2), 0.001);
	}
	@Test
	public void testOneShopping2() {
		LocalDate date = date2;
		ShoppingDao dao = Mockito.mock(ShoppingDao.class);
		Mockito.when(dao.findByPersonId(p1.getId())).thenReturn(Arrays.asList(sh1,sh2));
		DebtServiceImpl dsi = new DebtServiceImpl(dao);
		Assert.assertTrue(dsi.getDebtFrom(p1, date, date).isEmpty());
		Assert.assertEquals(300.0, dsi.getDebtTo(p1, date, date).get(p2), 0.001);
	}
	@Test
	public void testTwoShopping1() {
		ShoppingDao dao = Mockito.mock(ShoppingDao.class);
		Mockito.when(dao.findByPersonId(p1.getId())).thenReturn(Arrays.asList(sh1,sh2));
		DebtServiceImpl dsi = new DebtServiceImpl(dao);
		Assert.assertTrue(dsi.getDebtTo(p2, date2, date1).isEmpty());
		Assert.assertEquals(400.0, dsi.getDebtFrom(p2, date2, date1).get(p1), 0.001);
	}



}
