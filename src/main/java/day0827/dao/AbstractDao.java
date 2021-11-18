package day0827.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import day0827.entities.BaseEntity;

public abstract class AbstractDao<T extends BaseEntity<U>, U extends Number> {
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-maktab");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	public void save(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public T findById(U id) {
		return entityManager.find(getEntityClass(), id);
	}

	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery("From Contact", getEntityClass());
		return query.getResultList();

	}

	public List<T> findByNamePrefix(String name) {
		TypedQuery<T> query = entityManager.createQuery("From Contact where name= :name", getEntityClass());
		query.setParameter("name", name);
		return query.getResultList();

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected abstract Class<T> getEntityClass();
}
