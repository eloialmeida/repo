package com.multicert.gestao.dao;

import java.util.List;

import javax.ejb.Local;

import com.multicert.model.Cliente;
import com.multicert.model.MulticertException;

@Local
public interface MulticertDao<T extends Cliente> {

	public abstract void create(T object);

	public abstract void update(T object);

	public abstract void delete(T object);
	
	public List<T> getAll();

	public T read(String id);
	
	public List<T> getAllWithNameLike(String pattern);
}