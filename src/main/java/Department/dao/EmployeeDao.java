package Department.dao;

import javax.persistence.EntityManager;

import Department.entities.Employee;

public class EmployeeDao {

	private EntityManager entityManager;

	public EmployeeDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insert(Employee e) {
//		entityManager.getTransaction().begin();
		entityManager.persist(e);
//		entityManager.getTransaction().commit();
	}

	public Employee findById(int id) {
		return entityManager.find(Employee.class, id);
	}

	public void update(Employee e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();

	}
}
