package com.multicert.model;

public class MulticertException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MulticertException(){
		super();
	}
	
	public MulticertException(String reason){
		super(reason);
	}
}
