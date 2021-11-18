package Department;

import Department.entities.Department;
import Department.entities.Employee;
import Department.manager.Manager;

public class Main {
	public static void main(String[] args) {
		Manager manager = new Manager();
		Department department = new Department("IT");
		Employee employee = new Employee(1, "mohsen");
//		employee.setDepartment(department);

		manager.getEntityManager().getTransaction().begin();
		manager.addDepartment(department);
		manager.addEmployee(employee);
		manager.getEntityManager().getTransaction().commit();

//		Employee found = manager.getEmployeeById(1);
//		System.out.println("found: " + found.getName());
//		found.setDepartment(department);
//		manager.updateEmployee(found);
	}
}
