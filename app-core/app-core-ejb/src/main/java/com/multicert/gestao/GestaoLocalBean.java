package com.multicert.gestao;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;




import org.hibernate.Session;


import com.multicert.model.Cliente;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {
	
	public Cliente guardarCliente(String nome, String morada, String nif) {
		
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		Cliente cliente = new Cliente(nome,morada,UUID.randomUUID().toString());
		session.beginTransaction();
		session.save(cliente);
		session.getTransaction().commit();
		return cliente;
	}

	public List<Cliente> listarClientes() {

		return Arrays.asList(new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"));
	}
}
