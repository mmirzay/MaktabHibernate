package Department.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Department.dao.DepartmentDao;
import Department.dao.EmployeeDao;
import Department.entities.Department;
import Department.entities.Employee;

public class Manager {
	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;

	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-maktab");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	public Manager() {
		departmentDao = new DepartmentDao(getEntityManager());
		employeeDao = new EmployeeDao(getEntityManager());
	}

	public void addEmployee(Employee employee) {
		employeeDao.insert(employee);
	}

	public void addDepartment(Department department) {
		departmentDao.insert(department);
	}

	public Employee getEmployeeById(int id) {
		return employeeDao.findById(id);
	}

	public void updateEmployee(Employee e) {
		employeeDao.update(e);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
