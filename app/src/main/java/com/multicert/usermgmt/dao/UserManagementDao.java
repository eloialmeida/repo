package com.multicert.usermgmt.dao;


import javax.ejb.Local;

import com.multicert.usermgmt.model.Cliente;

@Local
public interface UserManagementDao {
	
	void save(Cliente cliente);
	
	void update(Cliente cliente);
	
	void delete(Cliente cliente);
	
	Cliente findByNif(String nif);
}
