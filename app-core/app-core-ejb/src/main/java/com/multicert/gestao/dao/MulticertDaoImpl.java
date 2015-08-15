package com.multicert.gestao.dao;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.multicert.model.Cliente;

@Default
@Stateless
public class MulticertDaoImpl<T extends Cliente> implements MulticertDao<T> {
	
	/**
	 * PersistenceContextType.TRANSACTION -> 1 ciclo de vida por transaçao 
	 * (necessario para ser consumido por beans stateless)
	 */
    @PersistenceContext(unitName = "punit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;


    public void create(T  object){
    	entityManager.persist(object);
    }
    

    public T read(Class<T> clazz, long id){
    	return entityManager.getReference(clazz, id);  			
    }
    
    
    public void update(T  object){
    	entityManager.merge(object);
    }
    

    public void delete(T object){
    	entityManager.remove(object);
    }
}
