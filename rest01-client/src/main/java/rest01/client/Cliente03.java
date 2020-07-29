package rest01.client;

import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

//CLIENTE RESTEASY

public class Cliente03 {
	
	public static final String URL = "http://127.0.0.1:8080/rest01";

	public static void main(String[] args) {
		
		ResteasyClient cliente = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = cliente.target( URL );
		
		HolaMundoService service = target.proxy( HolaMundoService.class );
		
		Persona per = service.hola( "pepe" );
		
		System.out.println("nombre: " + per.getNombre());
		System.out.println("mensaje: " + per.getMensaje());

	}

}
