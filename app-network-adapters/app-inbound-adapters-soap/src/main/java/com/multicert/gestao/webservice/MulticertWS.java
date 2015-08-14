package com.multicert.gestao.webservice;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.multicert.gestao.GestaoLocal;
import com.multicert.multicert.ListarClientes;
import com.multicert.multicert.ListarClientesResponse;
import com.multicert.multicert.Multicert;
import com.multicert.multicertcommontypes.Cliente;


@WebService(targetNamespace = "http://www.multicert.com/Multicert/", name = "Multicert")
public class MulticertWS implements Multicert {

	@EJB
	GestaoLocal service;
	
	private static final Logger LOG = LoggerFactory.getLogger(MulticertWS.class);

	public Cliente guardarCliente(String nif, String nome, String morada) {
		LOG.info("[IN] guardarCliente with name {}",nome);
		com.multicert.model.Cliente b = service.guardarCliente(nome, morada, nif);
		LOG.info("[OUT] guardarCliente");
		return MulticertWSMapper.map2SoapObject(b);
	}


	public ListarClientesResponse listarClientes(ListarClientes parameters) {
		LOG.info("[IN] listarClientes");
		ListarClientesResponse listarClientesResponse = new ListarClientesResponse();
		for(com.multicert.model.Cliente c : service.listarClientes()){
			listarClientesResponse.getCliente().add(MulticertWSMapper.map2SoapObject(c));
		}
		LOG.info("[OUT] listarClientes");
		return listarClientesResponse;
	}
}
