package hu.popcornmaci.dao.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PayInfo extends BaseEntity {
	@ManyToOne
	private Shopping shopping;
	@ManyToOne
	private Person person;
	private Double value;

	public PayInfo() {
	}

	public PayInfo(Shopping shopping, Person person, Double value) {
		super();
		this.shopping = shopping;
		this.person = person;
		this.value = value;
	}

	public Shopping getShopping() {
		return shopping;
	}

	public void setShopping(Shopping shopping) {
		this.shopping = shopping;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((shopping == null) ? 0 : shopping.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PayInfo))
			return false;
		PayInfo other = (PayInfo) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (shopping == null) {
			if (other.shopping != null)
				return false;
		} else if (!shopping.equals(other.shopping))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
