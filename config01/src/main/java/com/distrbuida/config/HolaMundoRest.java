package com.distrbuida.config;

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
	
	//@Inject
	//private Config config;
	
	@Inject
	@ConfigProperty(name="config01/texto", defaultValue = "hola") private String msg;
	
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public String hola() {
		
		// imprimir configuracion
		//Config config = ConfigProvider.getConfig();
		
		//listar fuentes de configuracion
		//config.getConfigSources()
		//.forEach(s -> System.out.println( s.getName() ) );
		
		//imprimir un valor de configuracion
		//Integer puerto = config.getValue("server.port", Integer.class);
		//System.out.println( puerto );
		
		//return config.getValue("texto", String.class);
		//return "hola!! ";
		
		return msg;
	}
}
