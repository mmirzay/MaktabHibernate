package homeworks.hw15.q2.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	private long nationalId;
	@Column(length = 25)
	private String name;

	public Customer(long nationalId, String name) {
		this.nationalId = nationalId;
		this.name = name;
	}

	public long getNationalId() {
		return nationalId;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Customer [nationalId=" + nationalId + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (nationalId ^ (nationalId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationalId != other.nationalId)
			return false;
		return true;
	}



}
