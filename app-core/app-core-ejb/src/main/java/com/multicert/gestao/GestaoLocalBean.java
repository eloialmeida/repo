package com.multicert.gestao;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;



@Default
@Stateless
public class GestaoLocalBean implements GestaoLocal {

	public String ping() {
		return "HELLO";
	}
	

}
