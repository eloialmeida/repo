package com.multicert.model;



public class Cliente {
	
	private String nome;
	
	private String morada;
	
	private String nif;
	
	
	public Cliente(){}
	
	public Cliente(String nome,String morada,String nif){
		this.nome=nome;
		this.morada=morada;
		this.nif=nif;
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
