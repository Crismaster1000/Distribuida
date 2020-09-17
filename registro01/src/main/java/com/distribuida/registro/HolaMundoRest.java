package com.distribuida.registro;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hola")
@ApplicationScoped
public class HolaMundoRest {
	
	@Inject
	@ConfigProperty(name="server.port", defaultValue = "8080") //llamamos a una propiedad de configuracion
	private Integer port;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public String hola() {
		
		return "Hola Mundo!! --> " + port ;
	}
}
