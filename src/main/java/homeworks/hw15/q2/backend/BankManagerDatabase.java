package homeworks.hw15.q2.backend;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.api.entities.Employee;

public class BankManagerDatabase {
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-maktab");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	public void addAllBanks(List<Bank> banks) {
		entityManager.getTransaction().begin();
		for (Bank bank : banks)
			entityManager.persist(bank);
		entityManager.getTransaction().commit();
	}

	public void addAllBranches(List<Branch> branches) {
		entityManager.getTransaction().begin();
		for (Branch branch : branches)
			entityManager.persist(branch);
		entityManager.getTransaction().commit();
	}

	public void addAllEmployees(List<Employee> employees) {
		entityManager.getTransaction().begin();
		for (Employee employee : employees)
			entityManager.persist(employee);
		entityManager.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public List<Bank> fetchAllBanks() {
		Query query = entityManager.createQuery("select b from Bank b");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Branch> fetchAllBranches() {
		Query query = entityManager.createQuery("select b from Branch b");
		return query.getResultList();
	}
}
