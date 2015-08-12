package com.multicert.usermgmt.client;

import java.util.Date;  
import java.util.List;

import com.multicert.usermgmt.model.Cliente;

public interface UserManagementClient {  
 
    void adicionarCliente(Cliente c);
  
    List<Cliente> listarClientes();
    
    List<Cliente> listarClientes(String nome);
    
    void apagarCliente(String nif);
    
    Cliente apresentarCliente(String nif);  
}
