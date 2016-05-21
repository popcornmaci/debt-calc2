package hu.popcornmaci.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hu.popcornmaci.dao.api.ShoppingDao;
import hu.popcornmaci.dao.entity.Item;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.entity.Shopping;
import hu.popcornmaci.dao.impl.ShoppingDaoImpl;
import hu.popcornmaci.service.api.DebtService;

public class DebtServiceImpl implements DebtService {
	private ShoppingDao shd = new ShoppingDaoImpl();

	@Override
	public Map<Person, Double> getDebtTo(Person person, LocalDate from, LocalDate to) {
		List<Shopping> list = shd.findByPersonId(person.getId());

		Date datefrom = from == null ? new Date(0) : Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateto = to == null ? new Date() : Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
		list = list.stream().filter(sh -> {
			long time = sh.getShoppingDate().getTime();
			long lfrom = datefrom.getTime();
			long lto = dateto.getTime();
			return (time >= lfrom && time <= lto);
		}).collect(Collectors.toList());
		return calcDebtTo(person, list);
	}

	private Map<Person, Double> calcDebtTo(Person person, List<Shopping> list) {
		Map<Person, Double> map = new HashMap<>();
		Map<Person, Double> dif = getDifMap(list);
		for (Shopping sh : list) {
			for (Item item : sh.getItems()) {
				item.getPersons().stream().forEach(p -> {
					dif.put(p, dif.get(p) - item.getValue() / item.getPersons().size());
				});
			}
		}

		while (!dif.isEmpty()) {
			if (dif.size() == 1) {
				break;
			}
			List<Map.Entry<Person, Double>> entries = new ArrayList<>(dif.entrySet());
			entries = entries.stream().sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
					.collect(Collectors.toList());
			// legtöbb tartozású
			Person low = entries.get(0).getKey();
			Person high = entries.get(entries.size() - 1).getKey();
			// a high/low következő egyenlege
			double highlowdif = dif.get(high) + dif.get(low);

			if (low.equals(person)) {
				map.put(high,
						Math.min(entries.get(entries.size() - 1).getValue(), Math.abs(entries.get(0).getValue())));
			}

			// ha nem tudta rendezni az egész tartozást
			if (highlowdif < 0) {

				dif.remove(high);
				dif.put(low, highlowdif);
			}
			// ha rendezte és már nincs másfelé tartozása
			else if (highlowdif > 0) {
				dif.remove(low);
				dif.put(high, highlowdif);
			}
			// ha pont ki tudta egyenlíteni
			else {
				dif.remove(low);
				dif.remove(high);
			}

		}
		return map;
	}

	private HashMap<Person, Double> getDifMap(List<Shopping> list) {
		HashMap<Person, Double> res = new HashMap<>();
		for (Shopping shopping : list) {
			shopping.getPinfo().stream().forEach(pi -> {
				Double val = res.get(pi.getPerson());
				if (val == null) {
					val = 0.0;
				}
				res.put(pi.getPerson(), val + pi.getValue());
			});
		}
		return res;
	}

	@Override
	public Map<Person, Double> getDebtFrom(Person person, LocalDate from, LocalDate to) {
		List<Shopping> list = shd.findByPersonId(person.getId());

		Date datefrom = from == null ? new Date(0) : Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateto = to == null ? new Date() : Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
		list = list.stream().filter(sh -> {
			long time = sh.getShoppingDate().getTime();
			long lfrom = datefrom.getTime();
			long lto = dateto.getTime();
			return (time >= lfrom && time <= lto);
		}).collect(Collectors.toList());
		return calcDebtFrom(person, list);
	}

	private Map<Person, Double> calcDebtFrom(Person person, List<Shopping> list) {
		Map<Person, Double> map = new HashMap<>();
		Map<Person, Double> dif = getDifMap(list);
		for (Shopping sh : list) {
			for (Item item : sh.getItems()) {
				item.getPersons().stream().forEach(p -> {
					dif.put(p, dif.get(p) - item.getValue() / item.getPersons().size());
				});
			}
		}

		while (!dif.isEmpty()) {
			if (dif.size() == 1) {
				break;
			}
			List<Map.Entry<Person, Double>> entries = new ArrayList<>(dif.entrySet());
			entries = entries.stream().sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
					.collect(Collectors.toList());
			// legtöbb tartozású
			Person low = entries.get(0).getKey();
			Person high = entries.get(entries.size() - 1).getKey();
			// a high/low következő egyenlege
			double highlowdif = dif.get(high) + dif.get(low);

			if (high.equals(person)) {
				map.put(low, Math.min(entries.get(entries.size() - 1).getValue(), Math.abs(entries.get(0).getValue())));
			}

			// ha nem tudta rendezni az egész tartozást
			if (highlowdif < 0) {

				dif.remove(high);
				dif.put(low, highlowdif);
			}
			// ha rendezte és már nincs másfelé tartozása
			else if (highlowdif > 0) {
				dif.remove(low);
				dif.put(high, highlowdif);
			}
			// ha pont ki tudta egyenlíteni
			else {
				dif.remove(low);
				dif.remove(high);
			}

		}
		return map;
	}
}
