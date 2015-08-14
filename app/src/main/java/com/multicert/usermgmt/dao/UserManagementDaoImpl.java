package com.multicert.usermgmt.dao;

import java.util.List;



import javax.ejb.Stateless;

import org.springframework.stereotype.Service;

import com.multicert.usermgmt.model.Cliente;

@Stateless
public class UserManagementDaoImpl implements UserManagementDao{

	public void save(Cliente cliente) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	public void update(Cliente cliente) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");	
	}

	public void delete(Cliente cliente) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	public Cliente findByNif(String nif) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return null;
	}

}
