package com.multicert.gestao;

import java.util.List;

import javax.ejb.Local;

import com.multicert.model.Cliente;

@Local
public interface GestaoLocal {
	Cliente guardarCliente(String nome, String morada, String nif);
	List<Cliente> listarClientes();
}
