package com.multicert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "cliente", indexes = {@Index(name = "nif_ondex",  columnList="nif", unique = true)})
public class Cliente implements Serializable{
	
	private String nome;
	
	private String morada;
	
	@Id
	@Column(unique=true,name = "nif")
	private String nif;
	
	private String telefone;
	
	
	public Cliente(){}
	
	public Cliente(String nome){
		this.nome=nome;
	}
	
	public Cliente(String nome,String morada){
		this(nome);
		this.morada=morada;
	}
	
	
	public Cliente(String nome,String morada,String nif){
		this(nome,morada);
		this.nif=nif;
	}
	
	public Cliente(String nome,String morada,String nif,String telefone){
		this(nome,morada,nif);
		this.telefone=telefone;
	}
	
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the morada
	 */
	public String getMorada() {
		return morada;
	}
	/**
	 * @param morada the morada to set
	 */
	public void setMorada(String morada) {
		this.morada = morada;
	}
	/**
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}
	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

}
