package hu.popcornmaci.service.api;

import java.time.LocalDate;
import java.util.Map;

import hu.popcornmaci.dao.entity.Person;

public interface DebtService {
	public Map<Person, Double> getDebtTo(Person person, LocalDate from, LocalDate to);
	public Map<Person, Double> getDebtFrom(Person person, LocalDate from, LocalDate to);
}
