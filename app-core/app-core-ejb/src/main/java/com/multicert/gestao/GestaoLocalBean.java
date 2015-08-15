package com.multicert.gestao;


import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import com.multicert.gestao.dao.MulticertDao;
import com.multicert.model.Cliente;
import com.multicert.model.MulticertException;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {

	@EJB
	private MulticertDao<Cliente> dao;


	public Cliente guardarCliente(String nome, String morada, String nif, String telefone) throws MulticertException {
		
		if(!validateCliente(nome, morada, nif, telefone))
			throw new MulticertException("Cliente nao pode ter nenhum dado nulo");
		
		Cliente cliente = new Cliente(nome,morada,UUID.randomUUID().toString(),telefone);
		dao.create(cliente);	
		return cliente;
	}

	public List<Cliente> listarClientes() throws MulticertException {	
		return dao.getAll();
	}

	public Cliente listarCliente(String nif) throws MulticertException {	
		return dao.read(nif);
	}
	
	public List<Cliente> listarClientesComNome(String ptrn) throws MulticertException {	
		return dao.getAllWithNameLike(ptrn);
	}
	
	private boolean validateCliente(String nome, String morada, String nif, String telefone){
		return nome!=null && morada!=null && nif!=null && telefone!=null;
	}

	public void apagarClientePorNif(String nif) throws MulticertException {
		Cliente c = listarCliente(nif);
		if(c==null)
			throw new MulticertException("Cliente Inexistente");
		dao.delete(c);
	}

}
