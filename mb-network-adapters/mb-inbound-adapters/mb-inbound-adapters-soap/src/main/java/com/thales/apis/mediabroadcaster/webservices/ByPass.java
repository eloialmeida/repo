package com.thales.apis.mediabroadcaster.webservices;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import com.multicert.gestao.GestaoLocal;

@ApplicationScoped
@Startup
@Default
public class ByPass {
	
	@EJB
	private GestaoLocal service;
	
	public String ping(){
		return service.ping();
	}
}
