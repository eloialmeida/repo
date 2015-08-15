package com.multicert.gestao.dao;

import java.util.List;

import javax.ejb.Local;

import com.multicert.model.Cliente;
import com.multicert.model.MulticertException;

@Local
public interface MulticertDao<T extends Cliente> {

	public abstract void create(T object) throws MulticertException;

	public abstract void update(T object) throws MulticertException;

	public abstract void delete(T object) throws MulticertException;
	
	public List<T> getAll() throws MulticertException;

	public T read(String id) throws MulticertException;
	
	public List<T> getAllWithNameLike(String pattern) throws MulticertException;
}