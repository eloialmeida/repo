package com.multicert.gestao;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.multicert.model.Cliente;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {

	
	public Cliente guardarCliente(String nome, String morada, String nif) {
		return new Cliente("echo "+nome,"echo "+morada,"in echo "+nif);
	}

	public List<Cliente> listarClientes() {

		return Arrays.asList(new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"),
				new Cliente("in progress","in progress","in progress"));
	}
}
