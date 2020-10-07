package com.distribuida.proxy;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
//import javax.ws.rs.client.ClientBuilder;

import org.eclipse.microprofile.rest.client.inject.RestClient;

//import org.eclipse.microprofile.rest.client.inject.RestClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.distribuida.entidad.Direccion;



@ApplicationScoped
public class DireccionProxyImpl {
	
	@Inject
    @RestClient
    DireccionInter client;
	
	public List<Direccion> ListarDirecciones(){
		return client.ListarDirecciones();
	}
	
	
	
	

}
