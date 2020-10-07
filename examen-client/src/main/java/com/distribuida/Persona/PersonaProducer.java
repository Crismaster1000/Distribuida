package com.distribuida.Persona;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

@ApplicationScoped
public class PersonaProducer {

private static final String URL = "https://127.0.0.1:8081/persona/direcciones";
	
	@Produces
	@ApplicationScoped
	public PersonaProxy getProxy() {
		ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(URL);
        PersonaProxy proxy = target.proxy(PersonaProxy.class);
		return proxy;
	}
}
