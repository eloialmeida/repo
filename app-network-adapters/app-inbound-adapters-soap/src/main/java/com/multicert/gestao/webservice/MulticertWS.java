package com.multicert.gestao.webservice;


import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.multicert.gestao.GestaoLocal;
import com.multicert.model.MulticertException;
import com.multicert.multicert.ListarClientesComNomeResponse;
import com.multicert.multicert.ListarClientesResponse;
import com.multicert.multicert.Multicert;
import com.multicert.multicertcommontypes.Cliente;
import com.multicert.multicertcommontypes.VOID;


@WebService(targetNamespace = "http://www.multicert.com/Multicert/", name = "Multicert")
public class MulticertWS implements Multicert {

	@EJB
	GestaoLocal service;
	
	private static final Logger LOG = LoggerFactory.getLogger(MulticertWS.class);

	public Cliente guardarCliente(String nif, String nome, String morada, String telefone) {
		try {
			LOG.info("[IN] guardarCliente with name {}",nome);
			com.multicert.model.Cliente b = service.guardarCliente(nome, morada, nif, telefone);
			LOG.info("[OUT] guardarCliente");
			return MulticertWSMapper.map2SoapObject(b);
		} catch (MulticertException e) {
			throw new RuntimeException(e.getCause());
		}

	}


	public List<Cliente> listarClientes(VOID parameters) {

		ListarClientesResponse listarClientesResponse = new ListarClientesResponse();

		try {
			for(com.multicert.model.Cliente c : service.listarClientes()){
				listarClientesResponse.getCliente().add(MulticertWSMapper.map2SoapObject(c));
			}
		} catch (MulticertException e) {
			throw new RuntimeException(e.getCause());
		}

		LOG.info("[OUT] listarClientes");
		return listarClientesResponse.getCliente();
	}


	public Cliente listarClientesComNif(String nif) {
		
		try {
			LOG.info("[IN] listarClientesComNif {}",nif);
			com.multicert.model.Cliente c = service.listarCliente(nif);
			LOG.info("[OUT] listarClientesComNif");
			return MulticertWSMapper.map2SoapObject(c);
		} catch (MulticertException e) {
			throw new RuntimeException(e.getCause());
		}
	}


	public List<Cliente> listarClientesComNome(String nome) {
		
		ListarClientesComNomeResponse listarClientesResponse = new ListarClientesComNomeResponse();

		try {
			for(com.multicert.model.Cliente c : service.listarClientesComNome(nome)){
				listarClientesResponse.getCliente().add(MulticertWSMapper.map2SoapObject(c));
			}
		} catch (MulticertException e) {
			throw new RuntimeException(e.getCause());
		}

		LOG.info("[OUT] listarClientesComNome");
		return listarClientesResponse.getCliente();
	}


	public VOID apagarClientesPorNif(String nif) {
		VOID _void_ = new VOID();
		LOG.info("[IN] apagarClientesPorNif {}",nif);
		try {
			service.apagarClientePorNif(nif);
		} catch (MulticertException e) {
			throw new RuntimeException(e.getCause());
		}
		LOG.info("[OUT] apagarClientesPorNif");
		return _void_;
	}
}
