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
public class FunctionalIT {
	
	private static final String TEST_TAG = "23$%$%&/()=*-";
	private static final String URI = "http://www.multicert.com/Multicert/";
	private static final String URL_ = "http://localhost:8080/app-inbound-adapters-soap/";
	private static final String SERVICE = "MulticertWSService";
	private static final QName QNAME = new QName(URI, SERVICE);
	private Multicert multicertWs;
	
	Service service;
	
	@Before
	public void setup() throws Exception{
		service = Service.create(new java.net.URL(URL_+"?wsdl"), QNAME);
		multicertWs = service.getPort(Multicert.class);
		
		apagarTudo();
	}
	
	@After
	public void cleanTestData(){
		apagarTudo();
	}
	
	
	/**
	 * 
	 *  - Adicionar Cliente A
	 *  - Listar Cliente A, verificar que foi retornado
	 *  - Apagar Cliente A
	 *  - Listar Por Nif o nif do Cliente A e verificar mensagem de erro que não existe
	 *  
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		
		String clienteNif = UUID.randomUUID().toString();
		
		GuardarClienteResponseContent r = multicertWs.guardarCliente(clienteNif, "CLIENTE A"+TEST_TAG, "CLIENTE A MORADA", "123456");
		
		Cliente cliente = r.getCliente();
		
		Assert.assertNotNull(cliente);
		
		ListarClientesComNifResponseContent r2 = multicertWs.listarClientesComNif(clienteNif);
		
		Cliente clienteMirror = r2.getCliente();
		
		assertClientAreEquals(clienteMirror,cliente);
		
		multicertWs.apagarClientesPorNif(clienteNif);
		
		ListarClientesComNifResponseContent r3 = multicertWs.listarClientesComNif(clienteNif);
		
		Assert.assertNull(r3.getCliente());
		
		Assert.assertNotNull(r3.getError());
		
		Assert.assertEquals("Cliente Não Encontrado",r3.getError().getCause());
    }
	
	
	/**
	 *  - Adicionar Cliente A
	 *  - Adicionar Cliente B, com o mesmo nif
	 *  - Verificar mensagem de erro
	 *  - Apagar Cliente A
	 *  
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		
		String clienteNif = UUID.randomUUID().toString();
		
		GuardarClienteResponseContent r = multicertWs.guardarCliente(clienteNif, "CLIENTE A"+TEST_TAG, "CLIENTE A MORADA", "123456");
		
		Cliente cliente = r.getCliente();
		
		Assert.assertNotNull(cliente);
		
		GuardarClienteResponseContent r2 = multicertWs.guardarCliente(clienteNif, "CLIENTE B"+TEST_TAG, "CLIENTE B MORADA", "123456");
		
		Assert.assertNull(r2.getCliente());
		
		Assert.assertNotNull(r2.getError());
		
		Assert.assertEquals("Cliente já existente com o mesmo nif "+clienteNif,r2.getError().getCause());
		
		multicertWs.apagarClientesPorNif(clienteNif);		
    }
	
	/**
	 *  - Adicionar Cliente A
	 *  - Apagar Cliente A
	 *  - Apagar Novamente Cliente A
	 *  - Verificar mensagem de erro
	 *  
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		
		String clienteNif = UUID.randomUUID().toString();
		
		GuardarClienteResponseContent r = multicertWs.guardarCliente(clienteNif, "CLIENTE A"+TEST_TAG, "CLIENTE A MORADA", "123456");
		
		Cliente cliente = r.getCliente();
		
		Assert.assertNotNull(cliente);
		
		multicertWs.apagarClientesPorNif(clienteNif);
		
		ApagarClientesPorNifResponseContent r2 = multicertWs.apagarClientesPorNif(clienteNif);
		
		Assert.assertNotNull(r2.getError());
		
		Assert.assertEquals("Cliente Inexistente",r2.getError().getCause());
    }
	

	/**
	 *  - Adicionar Cliente A
	 *  - Adicionar Cliente B (nome de B é parcialmente comum a A)
	 *  - Adicionar Cliente C
	 *  - Listar os 3 clientes
	 *  - Listar Clientes com nomes parcialmente comuns, listar com nomes singulares
	 *  - Apagar A,B,C
	 *  
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		
		
		GuardarClienteResponseContent r = multicertWs.guardarCliente(UUID.randomUUID().toString(), "CLIENTETeste4 A"+TEST_TAG, "CLIENTE A MORADA", "123456");
		
		Cliente clienteA = r.getCliente();
		
		Assert.assertNotNull(clienteA);
		
		GuardarClienteResponseContent r2 = multicertWs.guardarCliente(UUID.randomUUID().toString(), "CLIENTETeste4 B"+TEST_TAG, "CLIENTE B MORADA", "123456");
		
		Cliente clienteB = r.getCliente();
		
		Assert.assertNotNull(clienteB);
		
		GuardarClienteResponseContent r3 = multicertWs.guardarCliente(UUID.randomUUID().toString(), "CLIENTEx C"+TEST_TAG, "CLIENTE C MORADA", "123456");
		
		Cliente clienteC = r.getCliente();
		
		Assert.assertNotNull(clienteC);
		
			
		ListarClientesComNomeResponseContent r4 = multicertWs.listarClientesComNome(TEST_TAG);
		
		Assert.assertEquals(r4.getCliente().size(), 3);
		
		ListarClientesComNomeResponseContent r5 = multicertWs.listarClientesComNome("Teste4");
		
		Assert.assertEquals(r5.getCliente().size(), 2);
    }
	
	
	private void apagarTudo(){
		for (Cliente c : multicertWs.listarClientesComNome(TEST_TAG).getCliente()){
			multicertWs.apagarClientesPorNif(c.getNif());
		}
	}
	
	
	//Aux
	private void assertClientAreEquals(Cliente c1, Cliente c2){
		Assert.assertEquals(c1.getNif(), c2.getNif());
		Assert.assertEquals(c1.getMorada(), c2.getMorada());
		Assert.assertEquals(c1.getNome(), c2.getNome());
		Assert.assertEquals(c1.getTelefone(), c2.getTelefone());
	}

}


