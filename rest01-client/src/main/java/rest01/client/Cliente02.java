package rest01.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

// CLIENTE JAX-RS

public class Cliente02 {
	
	public static final String URL = "http://127.0.0.1:8080/rest01";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Client cliente = ClientBuilder.newClient();
		
		WebTarget target = cliente.target( URL )
				.path("/hola/{nombre}")
				.resolveTemplate("nombre", "juan");
		
		Invocation.Builder builder = target.request( MediaType.APPLICATION_JSON ); 
		
		Persona per = builder.get( Persona.class);
		
		System.out.println("nombre: " + per.getNombre());
		System.out.println("mensaje: " + per.getMensaje());
		
		

	}

}
