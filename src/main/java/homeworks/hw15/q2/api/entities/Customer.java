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

}
