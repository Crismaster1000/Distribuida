package com.distribuida.registro;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;


@ApplicationPath("/")
@ApplicationScoped
public class RestApplication extends Application {
	
	// USAMOS LAS ANOTACIONES DE LA CONFIGURACION DE MICROPROFILE
	@Inject
	@ConfigProperty(name="server.port", defaultValue = "8080") //llamamos a una propiedad de configuracion
	private Integer port;
	
	public void init( @Observes @Initialized(ApplicationScoped.class) Object event) {
		
		System.out.println("init");
		
		// http://ip:puerto/xxxxxx
		// ip:puerto
		
		ConsulClient client = new ConsulClient();
		
		NewService newService = new NewService();
		newService.setId("hola-mundo-" + port); //nombre de la instancia
		newService.setName("hola-mundo"); //nombre del servicio
		newService.setPort(port); //puerto en el q esta corriendo
		newService.setAddress("127.0.0.1"); //ip en el q esta corriendo
		
		// registrar el URL para verificar si el servidor esta activo o no
		NewService.Check check = new NewService.Check();
		check.setMethod( "GET" );
		check.setHttp( "http://127.0.0.1:" + port + "/ping");
		check.setInterval( "10s" );
		check.setDeregisterCriticalServiceAfter( "20s" );
		
		newService.setCheck(check);
		
		client.agentServiceRegister(newService);
	}
	
	public void destroy( @Observes @Destroyed(ApplicationScoped.class) Object event) {
		
		System.out.println("destroyed");
		
		ConsulClient client = new ConsulClient();
		client.agentServiceDeregister("hola-mundo-"+ port); //se quita con el nombre de la instancia q registramos
	}

}
