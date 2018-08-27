package com.infogain.boot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("genericDao")
@Transactional
public class GenericDaoImpl<T> implements IGenericDao<T> {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public T find(final T entity, Long id, String condition) {
		if (condition.isEmpty() || condition == null) {
			return (T) entityManager.find(entity.getClass(), id);
		} else {
			return (T) entityManager.createQuery("from " + entity.getClass().getName() + "  " + condition)
					.getSingleResult();
		}

	}

	@Override
	public List<T> fetchAll(final T entity, String condition) {
		if (condition.isEmpty() || condition == null) {
			return (List<T>) entityManager.createQuery("from " + entity.getClass().getName()).getResultList();
		} else {
			return (List<T>) entityManager.createQuery("from " + entity.getClass().getName() + " " + condition)
					.getResultList();
		}

	}

	@Override
	public void create(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

}
