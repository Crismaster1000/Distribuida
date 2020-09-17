package com.distribuida.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.distribuida.datos.Orders;
import com.distribuida.proxy.proxyOrders;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

	
	private static final String URL_ORDERS = "http://localhost:5050/Orders/orders";
	
	@Override
	public List<Orders> listarTodos() {
		System.out.println("ENTRA PROXY LISTAR TODOS");

		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_ORDERS);
		proxyOrders simple = target.proxy(proxyOrders.class);
		return simple.listarTodos();
	}

	@Override
	public String crearOrden(Orders orders) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_ORDERS);

		proxyOrders simple = target.proxy(proxyOrders.class);
		Response resp = simple.crearPersona(orders);
		return String.valueOf(resp.getStatus());
	}

	@Override
	public String editarOrden(Orders orders) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_ORDERS);

		proxyOrders simple = target.proxy(proxyOrders.class);
		Response per = simple.editarPersona(orders);
		return String.valueOf(per.getStatus());
	}

	@Override
	public void eliminarOrden(Long id) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_ORDERS);

		proxyOrders simple = target.proxy(proxyOrders.class);
		simple.eliminarPersona(id);
		
	}

	@Override
	public String buscar(Long id) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_ORDERS);

		proxyOrders simple = target.proxy(proxyOrders.class);
		Response per = simple.buscar(id);
		return String.valueOf(per.getStatus());
	}

}
