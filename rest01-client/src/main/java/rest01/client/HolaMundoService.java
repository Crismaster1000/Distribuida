package rest01.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface HolaMundoService {
	
	@GET @Path(value="/hola/{nombre}")
	@Produces(value= MediaType.APPLICATION_JSON)
	public Persona hola(@PathParam("nombre") String nombre );
	
	
}
