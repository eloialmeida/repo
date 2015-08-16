package com.multicert.gestao.webservice;

import com.multicert.model.MulticertException;
import com.multicert.multicertcommontypes.Cliente;
import com.multicert.multicertcommontypes.Error;

public class MulticertWSMapper {

	public static Cliente map2SoapObject(com.multicert.model.Cliente c) throws MulticertException {
		
		Cliente soapObject = new Cliente();
		if(c.getNif()==null)
			throw new MulticertException("NIF(ID) não pode ser null");
		soapObject.setNif(c.getNif());
		
		if(c.getMorada()==null)
			throw new MulticertException("Morada do nif{"+c.getNif()+"} não pode ser null");
		soapObject.setMorada(c.getMorada());
		
		if(c.getNome()==null)
			throw new MulticertException("Nome do nif{"+c.getNif()+"} não pode ser null");
		soapObject.setNome(c.getNome());
		
		if(c.getTelefone()==null)
			throw new MulticertException("Telefone do nif{"+c.getNif()+"} não pode ser null");
		soapObject.setTelefone(c.getTelefone());
		
		return soapObject;
	}

	public static Error map2SoapObject(MulticertException e) {
		Error error = new Error();
		error.setCause(e.getMessage());
		return error;
	}
}
