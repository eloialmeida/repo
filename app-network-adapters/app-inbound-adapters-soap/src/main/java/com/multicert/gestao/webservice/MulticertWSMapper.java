package com.multicert.gestao.webservice;

import com.multicert.multicertcommontypes.Cliente;

public class MulticertWSMapper {

	public static Cliente map2SoapObject(com.multicert.model.Cliente c) {
		Cliente soapObject = new Cliente();
		soapObject.setMorada(c.getMorada());
		soapObject.setNif(c.getNif());
		soapObject.setNome(c.getNome());
		return soapObject;
	}
	
}
