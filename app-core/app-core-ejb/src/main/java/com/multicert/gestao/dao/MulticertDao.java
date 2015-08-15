package com.multicert.gestao.dao;

import javax.ejb.Local;

import com.multicert.model.Cliente;

@Local
public interface MulticertDao<T extends Cliente> {

	public abstract void create(T object);

	public abstract T read(Class<T> clazz, long id);

	public abstract void update(T object);

	public abstract void delete(T object);

}