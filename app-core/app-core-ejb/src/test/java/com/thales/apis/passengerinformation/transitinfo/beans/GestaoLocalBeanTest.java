package com.thales.apis.passengerinformation.transitinfo.beans;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;


import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ejb.SupportEjb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.multicert.gestao.GestaoLocal;
import com.multicert.gestao.GestaoLocalBean;
import com.multicert.gestao.dao.MulticertDao;
import com.multicert.model.Cliente;
import com.multicert.model.MulticertException;


@RunWith(CdiRunner.class)
@SupportEjb
@AdditionalClasses({GestaoLocalBean.class})
public class GestaoLocalBeanTest {

	@Mock
	@Produces
	MulticertDao dao;
	
	@EJB
	GestaoLocal bean;
	
	Map<String,Cliente> fakeDatasource = new HashMap<String,Cliente>();
	
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup(){
		// Mock Dao Behaviour
		Mockito.doAnswer(new Answer<Cliente>() {

			public Cliente answer(InvocationOnMock invocation) throws Throwable {
				
				Cliente toPersist = (Cliente) invocation.getArguments()[0];			
				fakeDatasource.put(toPersist.getNif(), toPersist);				
				return toPersist;
			}
		}).when(dao).create(Mockito.any(Cliente.class));
		
		
		Mockito.doAnswer(new Answer<Cliente>() {

			public Cliente answer(InvocationOnMock invocation) throws Throwable {
				
				String id = (String) invocation.getArguments()[0];
				if(fakeDatasource.get(id)!=null){
					return fakeDatasource.get(id);
				}		
				return null;
			}
		}).when(dao).read(Mockito.anyString());
		
		
		
		
		Mockito.doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				
				Cliente id = (Cliente) invocation.getArguments()[0];		
				fakeDatasource.remove(id.getNif());
				return null;
			}
		}).when(dao).delete(Mockito.any(Cliente.class));
	}
	

	@Test
	public void testCreateAndRead(){
		
		try {
			Cliente newObject = bean.guardarCliente("nome", "morada", "nif", "telefone");
			Assert.assertNotNull(newObject);
			
			Assert.assertNotNull(bean.listarCliente(newObject.getNif()));
		} catch (MulticertException e) {
			Assert.assertTrue(false);
		};
	}
	
	@Test
	public void testCreateSameNifTwice(){
		
		try {
			bean.guardarCliente("asd", "ads", "NIF", "asd");

			bean.guardarCliente("123", "123", "NIF", "132");
			
			Assert.assertTrue(false);
			
		} catch (MulticertException e) {
			Assert.assertTrue(true);
			Assert.assertEquals("Cliente já existente com o mesmo nif NIF", e.getMessage());
		};
	}
	
	@Test
	public void testCreateDeleteReadSameObject() throws MulticertException{
		
		try{
			bean.guardarCliente("asd", "ads", "NIF", "asd");
	
			bean.apagarClientePorNif("NIF");
			
			Assert.assertNull(bean.listarCliente("NIF"));
		
		} catch (MulticertException e) {
			Assert.assertTrue(true);
			Assert.assertEquals("Cliente Não Encontrado", e.getMessage());
		};
	}
	
	@Test
	public void testDeleteTwice(){
		
		try {
			bean.guardarCliente("asd", "ads", "NIF", "asd");

			bean.apagarClientePorNif("NIF");
			
			bean.apagarClientePorNif("NIF");
			
			Assert.assertTrue(false);
			
		} catch (MulticertException e) {
			Assert.assertTrue(true);
			Assert.assertEquals("Cliente Inexistente", e.getMessage());
		};
	}
}
