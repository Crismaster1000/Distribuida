package com.distribuida.registro;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/ping")
public class PingRest {
	
	@GET
	public String ping() {
		System.out.println(" ping ");
		
		return "ok";
	}
}
