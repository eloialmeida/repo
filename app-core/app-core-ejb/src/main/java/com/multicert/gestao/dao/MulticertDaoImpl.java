package com.multicert.gestao.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;



import com.multicert.model.Cliente;

@SuppressWarnings("unchecked")
@Default
@Stateless
public class MulticertDaoImpl<T extends Cliente> implements MulticertDao<T> {

	private static final String KEY_1 = "key1";

	private static final String CLIENTS_GET_ALL = "SELECT c FROM Cliente c";

	private static final String CLIENT_GET_BY_NIF = "SELECT c FROM Cliente c WHERE c.nif = :"+KEY_1;
		
	private static final String CLIENT_GET_BY_NAME_CONTAINS = "SELECT c FROM Cliente c WHERE c.nome LIKE :"+KEY_1;


	/**
	 * PersistenceContextType.TRANSACTION -> 1 ciclo de vida por transaçao
	 * (necessario para ser consumido por beans stateless)
	 */
	@PersistenceContext(unitName = "punit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public void create(T object) {
		entityManager.persist(object);
	}

	public T read(String id) {
	    TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery(CLIENT_GET_BY_NIF);
		    return query.setParameter(KEY_1, id).getSingleResult();
	}

	public void update(T object) {
		entityManager.merge(object);
	}

	public void delete(T object) {
		entityManager.remove(object);
	}

	public List<T> getAll() {
	    TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery(CLIENTS_GET_ALL);
	    return query.getResultList();
	}

	public List<T> getAllWithNameContains(String pattern) {
		TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery(CLIENT_GET_BY_NAME_CONTAINS);
	    return query.setParameter(KEY_1, "%"+pattern+"%").getResultList();
	}
}
