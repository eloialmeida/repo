package com.multicert.usermgmt.bean;  
import java.util.Arrays;
import java.util.List;  

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multicert.usermgmt.client.UserManagementClient;
import com.multicert.usermgmt.dao.UserManagementDao;
import com.multicert.usermgmt.model.Cliente;


public class UserManagementBean implements UserManagementClient {  
 
	
	UserManagementDao userManagementDao;
	
	public void setUserManagementDao(UserManagementDao dao){
		this.userManagementDao=dao;
	}

	public void adicionarCliente(String nome, String nif, String morada) {
		userManagementDao.save(new Cliente(nome,nif,morada));
	}

	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return Arrays.asList(new Cliente("todo","todo","todo"));
	}

	public List<Cliente> listarClientes(String nome) {
		// TODO Auto-generated method stub
		return Arrays.asList(new Cliente("todo","todo","todo"));
	}

	public void apagarCliente(String nif) {
		// TODO Auto-generated method stub
	}

	public Cliente apresentarCliente(String nif) {
		return new Cliente("todo","todo","todo");
	}  
  
}  
