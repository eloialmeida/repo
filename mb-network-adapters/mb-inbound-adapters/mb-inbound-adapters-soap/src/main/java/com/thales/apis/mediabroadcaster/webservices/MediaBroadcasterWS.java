package com.thales.apis.mediabroadcaster.webservices;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.ws.Holder;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;












import com.multicert.gestao.GestaoLocal;
import com.thales.apis.mediabroadcaster.MediaBroadcaster;
import com.thales.apis.mediabroadcaster.Message;
import com.thales.apis.mediabroadcastercommontypes.ErrorType;
import com.thales.apis.mediabroadcastercommontypes.MessageId;


@WebService(targetNamespace = "http://www.apis.thales.com/MediaBroadcaster/", name = "MediaBroadcaster")
public class MediaBroadcasterWS implements MediaBroadcaster {

	private static final String SERVICE_JNDI = "java:global/app-ear/app-core-ejb-8.1.0-SNAPSHOT/GestaoLocalBean!com.multicert.gestao.GestaoLocal";

	
	public void sendContent(Message message, Holder<MessageId> messageID, Holder<ErrorType> error) {
		
		
		
		messageID.value = new MessageId();
		messageID.value.setId(new ByPass().ping());
	}

}
