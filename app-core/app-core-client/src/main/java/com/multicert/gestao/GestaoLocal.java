package com.multicert.gestao;

import java.util.List;

import javax.ejb.Local;

import com.multicert.model.Cliente;
import com.multicert.model.MulticertException;

@Local
public interface GestaoLocal {
	
	Cliente guardarCliente(String nome, String morada, String nif, String telefone) throws MulticertException;
	
	List<Cliente> listarClientes() throws MulticertException;
	
	Cliente listarCliente(String nif) throws MulticertException;
	
	public List<Cliente> listarClientesComNome(String ptrn) throws MulticertException;
	
	public void apagarClientePorNif(String nif) throws MulticertException;
}
