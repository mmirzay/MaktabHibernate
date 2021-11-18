package Department.dao;

import javax.persistence.EntityManager;

import Department.entities.Department;

public class DepartmentDao {
	private EntityManager entityManager;

	public DepartmentDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insert(Department department) {
//		entityManager.getTransaction().begin();
		entityManager.persist(department);
//		entityManager.getTransaction().commit();
	}
}
