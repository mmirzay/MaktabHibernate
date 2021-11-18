package day0827.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author MoMi
 *
 */
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity<Integer> {
	private String name;
	private String email;

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Contact(String name, String email) {

		this.name = name;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", email=" + email + "]";
	}

}
