package com.multicert.usermgmt.client;


import java.util.List;

import javax.jws.WebService;

import com.multicert.usermgmt.model.Cliente;

@WebService
public interface UserManagementClient {  
 
	void adicionarCliente(String nome, String nif, String morada);
  
    List<Cliente> listarClientes();
    
    void apagarCliente(String nif);
    
    Cliente apresentarCliente(String nif);  
}
