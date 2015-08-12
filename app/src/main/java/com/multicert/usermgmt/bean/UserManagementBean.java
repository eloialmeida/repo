package com.multicert.usermgmt.bean;
import java.util.ArrayList;  
import java.util.Arrays;
import java.util.Calendar;  
import java.util.Date;  
import java.util.List;  

import com.multicert.usermgmt.client.UserManagementClient;
import com.multicert.usermgmt.model.Cliente;
  
public class UserManagementBean implements UserManagementClient {  
 

	public void adicionarCliente(Cliente c) {
		
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
