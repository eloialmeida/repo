package com.multicert.gestao;


import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;


import com.multicert.gestao.dao.MulticertDao;
import com.multicert.model.Cliente;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {

	@EJB
	private MulticertDao<Cliente> dao;


	public Cliente guardarCliente(String nome, String morada, String nif, String telefone) {
		Cliente cliente = new Cliente(nome,morada,UUID.randomUUID().toString());
		dao.create(cliente);	
		return cliente;
	}

	public List<Cliente> listarClientes() {	
		return dao.getAll();
	}

	public Cliente listarCliente(String nif) {	
		return dao.read(nif);
	}
	
	public List<Cliente> listarClientesComNome(String ptrn) {	
		return dao.getAllWithNameContains(ptrn);
	}

}
