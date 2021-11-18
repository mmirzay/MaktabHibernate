package Department.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	@Id
	private String name;
	@OneToMany(mappedBy = "department",targetEntity = Employee.class)
	List<Employee> employees;

	public Department(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
