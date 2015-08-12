package com.multicert.usermgmt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.frontend.ServerFactoryBean;

import com.multicert.usermgmt.client.UserManagementClient;

/**
 * Unit test for simple App.
 */
public class AppTestClient extends TestCase
{
	 
	 protected UserManagementClient newBirthdayClient() {  
	    ClientProxyFactoryBean factory = new ClientProxyFactoryBean();  
	    factory.setServiceClass(UserManagementClient.class);  
	    factory.getServiceFactory().setDataBinding(new AegisDatabinding());  
	    factory.setAddress("http://localhost:8080/app-1.0-SNAPSHOT/userManagement");  
	    return (UserManagementClient) factory.create();  
	 }  
	   
	 public void testService() throws ParseException {  

	 }  
}
