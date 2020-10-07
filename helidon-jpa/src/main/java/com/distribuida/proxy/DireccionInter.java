package com.distribuida.proxy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.distribuida.entidad.Direccion;

@RegisterRestClient(baseUri = "http://127.0.0.1:8080/direccion")
public interface DireccionInter {
	
	@GET
	@Consumes(value =  MediaType.APPLICATION_JSON)
	public List<Direccion> ListarDirecciones();


}
