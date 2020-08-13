package com.distribuida;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hola")
public class HolaMundoRest {
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public String hola() {
		
		return "hola mundo!! ";
	}
}
