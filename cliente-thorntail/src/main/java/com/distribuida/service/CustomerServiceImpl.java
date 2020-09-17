package com.distribuida.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.distribuida.datos.Customer;
import com.distribuida.proxy.proxyCustomer;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

	private static final String URL_CUSTOMER = "http://localhost:5050/Customers/customer";

	@Override
	public List<Customer> listarTodos() {
		System.out.println("ENTRA PROXY LISTAR TODOS");

		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_CUSTOMER);
		proxyCustomer simple = target.proxy(proxyCustomer.class);
		return simple.listarTodos();
	}

	@Override
	public String crearPersona(Customer customer) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_CUSTOMER);
		proxyCustomer proxy = target.proxy(proxyCustomer.class);
		Response resp = proxy.crearPersona(customer);
		return String.valueOf(resp.getStatus());
	}

	@Override
	public String editarPersona(Customer customer) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_CUSTOMER);
		proxyCustomer proxy = target.proxy(proxyCustomer.class);
		Response per = proxy.editarPersona(customer);
		return String.valueOf(per.getStatus());
	}

	@Override
	public void eliminarPersona(Long id) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_CUSTOMER);
		proxyCustomer proxy = target.proxy(proxyCustomer.class);
		proxy.eliminarPersona(id);

	}

	@Override
	public String buscar(Long id) {
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target(URL_CUSTOMER);
		proxyCustomer proxy = target.proxy(proxyCustomer.class);
		Response per = proxy.buscar(id);
		return String.valueOf(per.getStatus());
	}

}
