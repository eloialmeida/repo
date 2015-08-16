package com.multicert.gestao.webservice;


import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.multicert.gestao.GestaoLocal;
import com.multicert.model.MulticertException;
import com.multicert.multicert.ApagarClientesPorNifResponse.ApagarClientesPorNifResponseContent;
import com.multicert.multicert.GuardarClienteResponse.GuardarClienteResponseContent;
import com.multicert.multicert.ListarClientesComNifResponse.ListarClientesComNifResponseContent;
import com.multicert.multicert.ListarClientesComNomeResponse.ListarClientesComNomeResponseContent;
import com.multicert.multicert.ListarClientesResponse.ListarClientesResponseContent;
import com.multicert.multicert.Multicert;
import com.multicert.multicertcommontypes.VOID;


@WebService(targetNamespace = "http://www.multicert.com/Multicert/", name = "Multicert")
public class MulticertWS implements Multicert {

	@EJB
	GestaoLocal service;
	
	private static final Logger LOG = LoggerFactory.getLogger(MulticertWS.class);


	public ListarClientesResponseContent listarClientes(VOID parameters) {

		ListarClientesResponseContent responseContent = new ListarClientesResponseContent();

		try {
			for(com.multicert.model.Cliente c : service.listarClientes()){
				responseContent.getCliente().add(MulticertWSMapper.map2SoapObject(c));
			}
		} catch (MulticertException e) {
			LOG.error(e.getMessage());
			responseContent.setError(MulticertWSMapper.map2SoapObject(e));
		}

		LOG.info("[OUT] listarClientes");
		return responseContent;
	}


	public ListarClientesComNifResponseContent listarClientesComNif(String nif) {
		
		ListarClientesComNifResponseContent responseContent = new ListarClientesComNifResponseContent();
		
		try {
			LOG.info("[IN] listarClientesComNif {}",nif);
			com.multicert.model.Cliente c = service.listarCliente(nif);
			LOG.info("[OUT] listarClientesComNif");
			responseContent.setCliente(MulticertWSMapper.map2SoapObject(c));
		} catch (MulticertException e) {
			LOG.error(e.getMessage());
			responseContent.setError(MulticertWSMapper.map2SoapObject(e));
		}
		return responseContent;
	}


	public ListarClientesComNomeResponseContent listarClientesComNome(String nome) {
		
		ListarClientesComNomeResponseContent listarClientesResponse = new ListarClientesComNomeResponseContent();

		try {
			for(com.multicert.model.Cliente c : service.listarClientesComNome(nome)){
				listarClientesResponse.getCliente().add(MulticertWSMapper.map2SoapObject(c));
			}
		} catch (MulticertException e) {
			LOG.error(e.getMessage());
			listarClientesResponse.setError(MulticertWSMapper.map2SoapObject(e));
		}

		LOG.info("[OUT] listarClientesComNome");
		return listarClientesResponse;
	}


	public ApagarClientesPorNifResponseContent apagarClientesPorNif(String nif) {
		ApagarClientesPorNifResponseContent _void_ = new ApagarClientesPorNifResponseContent();
		LOG.info("[IN] apagarClientesPorNif {}",nif);
		try {
			service.apagarClientePorNif(nif);
		} catch (MulticertException e) {
			LOG.error(e.getMessage());
			LOG.error(e.getMessage());
			_void_.setError(MulticertWSMapper.map2SoapObject(e));
		}
		LOG.info("[OUT] apagarClientesPorNif");
		return _void_;
	}


	public GuardarClienteResponseContent guardarCliente(String nif,
			String nome, String morada, String telefone) {
		
		GuardarClienteResponseContent responseContent = new GuardarClienteResponseContent();
		try {
			LOG.info("[IN] guardarCliente with name {}", nome);
			com.multicert.model.Cliente b = service.guardarCliente(nome,morada, nif, telefone);
			LOG.info("[OUT] guardarCliente");
			responseContent.setCliente(MulticertWSMapper.map2SoapObject(b));
		} catch (MulticertException e) {
			LOG.error(e.getMessage());
			responseContent.setError(MulticertWSMapper.map2SoapObject(e));
		}
		return responseContent;
	}
}
