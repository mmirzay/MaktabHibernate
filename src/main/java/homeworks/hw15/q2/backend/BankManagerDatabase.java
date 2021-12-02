package homeworks.hw15.q2.backend;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import homeworks.hw15.q2.api.entities.Account;
import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.api.entities.CreditCardPassword;
import homeworks.hw15.q2.api.entities.Customer;
import homeworks.hw15.q2.api.entities.DepositTransaction;
import homeworks.hw15.q2.api.entities.Employee;
import homeworks.hw15.q2.api.entities.Transaction;

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

	@SuppressWarnings("unchecked")
	public List<Employee> fetchAllEmployees() {
		Query query = entityManager.createQuery("select e from Employee e");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> fetchAllCustomers() {
		Query query = entityManager.createQuery("select c from Customer c");
		return query.getResultList();
	}

	public Customer fetchCustomerById(long nationalId) {
		return entityManager.find(Customer.class, nationalId);
	}

	public void insertCustomer(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();

	}

	public Account fetchAccountByCustomerIdAndBranchId(long cutomerId, int branchId) {
		Query query = entityManager.createNativeQuery(
				"Select * from Account a where a.customer_id = ? and a.branch_code= ?", Account.class);
		query.setParameter(1, cutomerId);
		query.setParameter(2, branchId);
		try {
			return (Account) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void insertAccount(CreditCardPassword passwords) {
		entityManager.getTransaction().begin();
		entityManager.persist(passwords.getCard().getAccount());
		entityManager.persist(passwords.getCard());
		entityManager.persist(passwords);
		Transaction transaction = new DepositTransaction(passwords.getCard().getAccount(), 0);
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();

	}

	public CreditCardPassword fetchCardByCustomerIdAndBranchId(long cutomerId, int branchId) {
		Query query = entityManager.createNativeQuery("Select * from Account a, CreditCard c, CreditCardPassword p"
				+ " where p.card_id = c.id and c.account_number = a.number and"
				+ " a.customer_id = ? and a.branch_code= ?", CreditCardPassword.class);
		query.setParameter(1, cutomerId);
		query.setParameter(2, branchId);
		try {
			return (CreditCardPassword) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void updateAccount(Account source, Account destination, double amount) {
		entityManager.getTransaction().begin();
		entityManager.merge(source);
		if (destination != null)
			entityManager.merge(destination);
		Transaction transaction = new DepositTransaction(source, amount);
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
	}
}
