package Department.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	private int id;
	@Column(length = 25)
	private String name;
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "FK_department_employee"))
	private Department department;

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
