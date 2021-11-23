package homeworks.hw15.q2.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bank {
	@Id
	@Column(length = 20)
	private String name;

	public Bank(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
