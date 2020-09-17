package com.distribuida.registro.cliente;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;

public class Cliente {
	
	public static String testRest(String url) {
		
		//invocar al servicio
		Client cliente =  ClientBuilder.newClient();
		WebTarget target = cliente.target( url )
				.path("/hola");

		Invocation.Builder builder = target.request( MediaType.APPLICATION_JSON);

		String txt = builder.get(String.class);
		
		// demoramos en 5 seg 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return txt;
		
	}

	public static void main2(String[] args) {
		
		ConsulClient client = new ConsulClient( "127.0.0.1" ); // se le pasa la ip donde esta corriendo el servidor
		
		/*Response<Map<String, Service>> ss = client.getAgentServices();
		
		Map<String, Service> servicios = ss.getValue();
		
		servicios.values()
			.stream()
			.filter( ser -> ser.getService().equals("hola-mundo"))
			.forEach( ser -> {
				System.out.printf("[%s:%s]  http://%s:%d/hola\n", ser.getAddress(), ser.getPort(), ser.getAddress(), ser.getPort()); 
			});*/
		
		
		HealthServicesRequest request = HealthServicesRequest.newBuilder()
				.setPassing(true)
				.setQueryParams(QueryParams.DEFAULT)
				.build();
		
		Response<List<HealthService>> datos = client.getHealthServices("hola-mundo", request);
		
		datos.getValue()
		.stream()
		.map( ser -> ser.getService())
		.forEach( ser -> {
			System.out.printf("[%s:%s]  http://%s:%d/hola\n", ser.getService(), ser.getId(), ser.getAddress(), ser.getPort()); 

		});
		
		//obtenemos una lista de los servicios sanos
		List<String> urls = datos.getValue()
				.stream()
				.map( ser -> ser.getService())
				.map( s -> String.format("http://%s:%d", s.getAddress(), s.getPort()))
				.collect( Collectors.toList() );
		
		// usamos un random para elegir el servicio de la lista
		int max = urls.size();
		//Random rd = new Random();
		
		//balanceamos estilo ron robbin
		for (int i = 0; i < 10; i++) {
			//int index = rd.nextInt(max);

			int index = i %max;

			String url = urls.get(index);

			System.out.println( "URL-->" + url );

			String txt = testRest(url);
			
			System.out.println( txt );
		}
		
		
	}
	
	public static void main(String[] args) {
		
		while(true) {
			
			//int index = rd.nextInt(max);

			//int index = i %max;

			String url = "http://127.0.0.1";

			System.out.println( "URL-->" + url );

			String txt = testRest(url);
			
			System.out.println( txt );
		}
		
	}

}
