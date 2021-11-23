package homeworks.hw15.q2.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 25)
	private String name;
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
