package com.distribuida.proxi;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.distribuida.entidades.Customer;

@ApplicationScoped
public class CustomerProxyImpl {
	
	private static final String URL_CUSTOMERS = "http://127.0.0.1:5050/Customers/customer";
	
	public List<Customer> listarAll(){
		
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_CUSTOMERS);
		
		CustomerProxy simple = target.proxy(CustomerProxy.class);
		return simple.listarAll();
		
	}

}
