package com.multicert.gestao;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;






import com.multicert.gestao.dao.MulticertDao;
import com.multicert.model.Cliente;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {

	@EJB
	private MulticertDao<Cliente> dao;

	public Cliente guardarCliente(String nome, String morada, String nif) {

		Cliente cliente = new Cliente(nome,morada,UUID.randomUUID().toString());

		dao.create(cliente);
		
		return cliente;
	}

	public List<Cliente> listarClientes() {

		return Arrays.asList(new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"));
	}
	

}
