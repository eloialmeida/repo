package com.multicert.usermgmt.model;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
	

	private static final long serialVersionUID = 1L;


	private String nif;


	private String nome;
	

	private String morada;
	
	public Cliente(){
		
	}

	public Cliente(String nome, String nif, String morada) {
		this.nome = nome;
		this.nif = nif;
		this.morada = morada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}
}
