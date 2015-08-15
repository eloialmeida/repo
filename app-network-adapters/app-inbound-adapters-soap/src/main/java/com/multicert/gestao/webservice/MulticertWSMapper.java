package com.multicert.gestao.webservice;

import com.multicert.model.MulticertException;
import com.multicert.multicertcommontypes.Cliente;

public class MulticertWSMapper {

	public static Cliente map2SoapObject(com.multicert.model.Cliente c) throws MulticertException {
		
		Cliente soapObject = new Cliente();
		
		if(c.getMorada()==null)
			throw new MulticertException("Morada não pode ser null");
		soapObject.setMorada(c.getMorada());
		
		if(c.getMorada()==null)
			throw new MulticertException("NIF(ID) não pode ser null");
		soapObject.setNif(c.getNif());
		
		if(c.getMorada()==null)
			throw new MulticertException("Nome não pode ser null");
		soapObject.setNome(c.getNome());
		
		if(c.getMorada()==null)
			throw new MulticertException("Telefone não pode ser null");
		soapObject.setTelefone(c.getTelefone());
		
		return soapObject;
	}
}
