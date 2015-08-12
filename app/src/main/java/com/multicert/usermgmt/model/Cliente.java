package com.multicert.usermgmt.model;

public class Cliente {
	
	private String nome;
	private String nif;
	private String morada;
	
	public Cliente(String nome, String nif, String morada){
		this.nome=nome;
		this.nif=nif;
		this.morada=morada;
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
