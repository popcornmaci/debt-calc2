package hu.popcornmaci.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Shopping extends BaseEntity {
	@Temporal(TemporalType.TIMESTAMP)
	private Date shoppingDate;
	@ManyToMany
	private List<Item> items;
	private String description;
	@OneToMany(mappedBy = "shopping")
	private List<PayInfo> pinfo;

	public Shopping() {
	}

	public Shopping(Date shoppingDate, List<Item> items, String description, List<PayInfo> pinfo) {
		super();
		this.shoppingDate = shoppingDate;
		this.items = items;
		this.description = description;
		this.pinfo = pinfo;
	}

	public Date getShoppingDate() {
		return shoppingDate;
	}

	public void setShoppingDate(Date shoppingDate) {
		this.shoppingDate = shoppingDate;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PayInfo> getPinfo() {
		return pinfo;
	}

	public void setPinfo(List<PayInfo> pinfo) {
		this.pinfo = pinfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((shoppingDate == null) ? 0 : shoppingDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Shopping))
			return false;
		Shopping other = (Shopping) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (shoppingDate == null) {
			if (other.shoppingDate != null)
				return false;
		} else if (!shoppingDate.equals(other.shoppingDate))
			return false;
		return true;
	}

}
