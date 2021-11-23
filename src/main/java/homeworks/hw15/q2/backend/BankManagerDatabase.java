package homeworks.hw15.q2.backend;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BankManagerDatabase {
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-maktab");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();
}
