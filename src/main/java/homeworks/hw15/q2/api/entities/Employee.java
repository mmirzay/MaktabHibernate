package homeworks.hw15.q2.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
	private int id;
	@Column(length = 25)
	private String name;
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;
	@ManyToOne(optional = false)
	private Branch branch;

	public Employee(int id, String name, Branch branch) {
		this.id = id;
		this.name = name;
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Branch getBranch() {
		return branch;
	}

}
