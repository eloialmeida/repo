package com.multicert.gestao.webservice;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.multicert.multicert.ApagarClientesPorNifResponse.ApagarClientesPorNifResponseContent;
import com.multicert.multicert.GuardarClienteResponse.GuardarClienteResponseContent;
import com.multicert.multicert.ListarClientesComNifResponse.ListarClientesComNifResponseContent;
import com.multicert.multicert.ListarClientesComNomeResponse.ListarClientesComNomeResponseContent;
import com.multicert.multicert.Multicert;
import com.multicert.multicertcommontypes.Cliente;

/***
 * Testes integrados, o serviço tem de estar UP
 * 
 * Testes idempotentes
 *  
 * @author eloi
 *
 */
public class PopulateDB {
	
	private static final int COUNT = 1000;
	
	private static final String TEST_TAG = "!#!#!#!#!#!#";
	private static final String URI = "http://www.multicert.com/Multicert/";
	private static final String URL_ = "http://23.97.220.130:8080/MULTICERT_WSDL/service.wsdl";
	private static final String SERVICE = "MulticertWSService";
	private static final QName QNAME = new QName(URI, SERVICE);
	private Multicert multicertWs;
	
	Service service;
	
	@Before
	public void setup() throws Exception{
		service = Service.create(new java.net.URL(URL_), QNAME);
		multicertWs = service.getPort(Multicert.class);
		
		for(int i=0;i<COUNT;i++){
			multicertWs.guardarCliente(UUID.randomUUID().toString(), UUID.randomUUID().toString()+TEST_TAG, UUID.randomUUID().toString(), UUID.randomUUID().toString());
			if(i%100==0){
				System.out.println(i);
			}
		}
	}

	
	
	@Test
	public void test1() throws Exception {
		
		Assert.assertNotNull(new Object());
    }

}


