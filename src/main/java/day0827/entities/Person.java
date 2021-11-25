package day0827.entities;

import javax.persistence.Entity;

@Entity
public class Person extends BaseEntity<Integer> {
	private String name;
	private String family;

	public Person(String name, String family) {
		this.setName(name);
		this.setFamily(family);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

}
