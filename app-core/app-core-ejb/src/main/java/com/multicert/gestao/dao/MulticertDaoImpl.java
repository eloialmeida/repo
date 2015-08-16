package com.multicert.gestao.dao;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.multicert.model.Cliente;
import com.multicert.model.MulticertException;

@SuppressWarnings("unchecked")
@Default
@Stateless
public class MulticertDaoImpl<T extends Cliente> implements MulticertDao<T> {

	private static final String KEY_1 = "key1";

	private static final String CLIENTS_GET_ALL = "SELECT c FROM Cliente c";

	private static final String CLIENT_GET_BY_NIF = "SELECT c FROM Cliente c WHERE c.nif = :"
			+ KEY_1;

	private static final String CLIENT_GET_BY_NAME_CONTAINS = "SELECT c FROM Cliente c WHERE c.nome LIKE :"
			+ KEY_1;

	// private static final String CLIENT_DELETE_BY_NIF =
	// "DELETE FROM Cliente c WHERE c.nif = :"+KEY_1;

	/**
	 * PersistenceContextType.TRANSACTION -> 1 ciclo de vida por transaçao
	 * (necessario para ser injectado em beans stateless)
	 */
	@PersistenceContext(unitName = "punit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public void create(T object) {
		entityManager.persist(object);
	}

	public T read(String id) {
		try {
			TypedQuery<T> query = (TypedQuery<T>) entityManager
					.createQuery(CLIENT_GET_BY_NIF);
			return query.setParameter(KEY_1, id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void update(T object) {
		entityManager.merge(object);
	}

	public void delete(T object) {
		try {
			entityManager.remove(object);
		} catch (NoResultException e) {
			// do nothing, might be expected
		}
	}

	public List<T> getAll() {
		try {
			TypedQuery<T> query = (TypedQuery<T>) entityManager
					.createQuery(CLIENTS_GET_ALL);
			return query.getResultList();
		} catch (NoResultException e) {
			return Arrays.asList();
		}
	}

	public List<T> getAllWithNameLike(String pattern) {
		try {
			TypedQuery<T> query = (TypedQuery<T>) entityManager
					.createQuery(CLIENT_GET_BY_NAME_CONTAINS);
			return query.setParameter(
					KEY_1,
					new StringBuilder().append("%").append(pattern).append("%")
							.toString()).getResultList();
		} catch (NoResultException e) {
			return Arrays.asList();
		}
	}
}
