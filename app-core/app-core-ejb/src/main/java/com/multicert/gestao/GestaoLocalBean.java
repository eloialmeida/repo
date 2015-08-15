package com.multicert.gestao;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;



import com.multicert.model.Cliente;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {
	
	/**
	 * PersistenceContextType.TRANSACTION -> 1 ciclo de vida por transaçao (GestaoLocalBean é stateless)
	 */
    @PersistenceContext(unitName = "punit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;


	
	public Cliente guardarCliente(String nome, String morada, String nif) {
		
//		Session session = HibernateSessionManager.getSessionFactory().openSession();
		Cliente cliente = new Cliente(nome,morada,UUID.randomUUID().toString());
//		session.beginTransaction();
//		session.save(cliente);
//		session.getTransaction().commit();
		

		entityManager.persist(cliente);
			
		
		return cliente;
	}

	public List<Cliente> listarClientes() {

		return Arrays.asList(new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"));
	}
}
