package com.distribuida.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.distribuida.entidades.Customer;
import com.distribuida.entidades.Orders;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(urlPatterns = "/")
public class PersonaClienteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String URL = "https://erazo-distribuida41.herokuapp.com/customers";
	public static final String URL2 = "https://erazo-distribuida42.herokuapp.com/orders";
	public static final String URL3 = "https://erazo-distribuida42.herokuapp.com/orders/customer";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
			switch (action) {
			case "/":
				selectUser(req, resp);
				break;
			case "/consultar":
				selectUser(req, resp);
				break;
			case "/insertar":
				insertUser(req, resp);
				break;
			case "/actualizar":
				updateUser(req, resp);
				break;
			case "/actualizarOrden":
				updateOrder(req, resp);
				break;
			case "/borrar":
				deleteUser(req, resp);
				break;
			case "/buscarID":
				buscar_id(req, resp);
				break;
			case "/buscarOrders":
				buscar_orders(req, resp);
				break;
			case "/insertarOrden":
				insert_orden(req, resp);
				break;
			case "/borrarOrden":
				borrar_orden(req, resp);
				break;
			case "/buscarOrdenID":
				buscar_ordersId(req, resp);
				break;
			default:
				selectUser(req, resp);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	private void buscar_orders(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
		// HACEMOS UN GET AL SERVICIO PARA QUE NOS RETORNE LA LISTA DE ORDENES
		
		String customer_id = req.getParameter("id");
		StringBuilder stringBuilder = new StringBuilder("https://erazo-distribuida42.herokuapp.com/orders/customer");
		stringBuilder.append("/");
		stringBuilder.append(URLEncoder.encode(customer_id, "UTF-8"));
		
		URL url = new URL(stringBuilder.toString());
		//URL url = new URL("http://127.0.0.1:8087/orders/customer/"+customer_id);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod( "GET" );
		connection.setRequestProperty( "Accept", "application/json" );
		
		int httpCode = connection.getResponseCode();

		if(httpCode != HttpURLConnection.HTTP_OK) {
			System.err.println(" Error " + httpCode );
			return;
		}
		
		//commons-io
		//http 200: ok
		InputStream is = connection.getInputStream();

		String json = IOUtils.toString(  is , Charset.defaultCharset() );
		System.out.println( " datos devueltos:  " + json);

		// CONVERTIMOS EL JSON A LISTA
		ObjectMapper mapper = new ObjectMapper();
		List<Orders> orderslist = mapper.readValue(json, new TypeReference<List<Orders>>(){});
		System.out.println("aqui!!");
		for(int i=0 ; i< orderslist.size(); i++) {

			System.out.println(orderslist.get(i).getId());

			System.out.println(orderslist.get(i).getItem());

			System.out.println(orderslist.get(i).getPrice());

		}

		req.setAttribute("listOrder", orderslist);
		//req.setAttribute("custom_id", customer_id);
		req.getRequestDispatcher("OrderIndex.jsp").forward(req, resp);
		
	}
	
	private void insert_orden(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String item = req.getParameter("item");
		Float price = Float.parseFloat(req.getParameter("price"));
		Long cus_id = Long.parseLong(req.getParameter("customer_id"));

		Orders newOrder = new Orders(item,price,cus_id);
		
		// MANDAMOS LA NUEVA PERSONA AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL2);
		Response addResponse = add.request().post(Entity.entity(newOrder, MediaType.APPLICATION_JSON));
		
		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();

		System.out.println("Usuario insertado");

		resp.sendRedirect("consultar");

	}
	
	private void selectUser(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {

		// HACEMOS UN GET AL SERVICIO PARA QUE NOS RETORNE LA LISTA DE PERSONAS
		URL url = new URL("https://erazo-distribuida41.herokuapp.com/customers");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod( "GET" );
		connection.setRequestProperty( "Accept", "application/json" );
		

		int httpCode = connection.getResponseCode();

		if(httpCode != HttpURLConnection.HTTP_OK) {
			System.err.println(" Error " + httpCode );
			return;
		}

		//commons-io
		//http 200: ok
		InputStream is = connection.getInputStream();

		String json = IOUtils.toString(  is , Charset.defaultCharset() );
		System.out.println( " datos devueltos:  " + json);
		
		// CONVERTIMOS EL JSON A LISTA
		ObjectMapper mapper = new ObjectMapper();
		List<Customer> customerlist = mapper.readValue(json, new TypeReference<List<Customer>>(){});
		//System.out.println(personalist.get(1).getNombre());


		for(int i=0 ; i< customerlist.size(); i++) {

			System.out.println(customerlist.get(i).getId());

			System.out.println(customerlist.get(i).getName());

			System.out.println(customerlist.get(i).getSurname());

		}

		req.setAttribute("listUser", customerlist);
		req.getRequestDispatcher("NewIndex.jsp").forward(req, resp);

	}
	
	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");

		Customer newUser = new Customer(name, surname);
		
		// MANDAMOS LA NUEVA PERSONA AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL);
		Response addResponse = add.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		
		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();

		System.out.println("Usuario insertado");

		resp.sendRedirect("consultar");

	}
	
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");

		Customer newUser = new Customer(id, name, surname);
		
		// ACTUALIZAMOS LA PERSONA CON UN PUT AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL);
		Response addResponse = add.request().put(Entity.entity(newUser, MediaType.APPLICATION_JSON));

		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();
		
		System.out.println("Usuario actualizado");
		resp.sendRedirect("consultar");
	}
	
	private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String item = req.getParameter("item");
		Float precio = Float.parseFloat(req.getParameter("precio"));
		
		Orders newOrder = new Orders(id, item, precio);

		// ACTUALIZAMOS LA PERSONA CON UN PUT AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL2);
		Response addResponse = add.request().put(Entity.entity(newOrder, MediaType.APPLICATION_JSON));

		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();

		System.out.println("Orden actualizado");
		resp.sendRedirect("consultar");
	}
	
	private void buscar_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
		Long id = Long.parseLong(req.getParameter("id"));
		
		Client cliente = ClientBuilder.newClient();

		WebTarget target = cliente.target( URL )
				.path("/{id}")
				.resolveTemplate("id", id);

		Invocation.Builder builder = target.request( MediaType.APPLICATION_JSON ); 
		Customer customer = new Customer();
		try {
			customer = builder.get(Customer.class);

		}
		catch( NotFoundException ex ) {

			System.out.println(" Estado : " + ex.getResponse().getStatus());
			System.out.println(" Estado : " + ex.getResponse().getStatusInfo() );

		}
		
		String id1 = Long.toString(customer.getId());
		String name = customer.getName();
		String surname = customer.getSurname();
		
		req.setAttribute("id", id1);
		req.setAttribute("name", name);
		req.setAttribute("surname", surname);
		
		req.getRequestDispatcher("actualizar.jsp").forward(req, resp);
		
		System.out.println("Usuario encontrado");

	}
	
	private void buscar_ordersId(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
		Long id = Long.parseLong(req.getParameter("id"));
		
		Client cliente = ClientBuilder.newClient();

		WebTarget target = cliente.target( URL2 )
				.path("/{id}")
				.resolveTemplate("id", id);

		Invocation.Builder builder = target.request( MediaType.APPLICATION_JSON ); 
		Orders order = new Orders();
		try {
			order = builder.get(Orders.class);

		}
		catch( NotFoundException ex ) {

			System.out.println(" Estado : " + ex.getResponse().getStatus());
			System.out.println(" Estado : " + ex.getResponse().getStatusInfo() );

		}
		
		String id1 = Long.toString(order.getId());
		String item = order.getItem();
		Float price = order.getPrice();
		Long customer_id = order.getCustomer_id();
		
		req.setAttribute("id", id1);
		req.setAttribute("item", item);
		req.setAttribute("precio", price);
		req.setAttribute("customer_id", customer_id);
		
		req.getRequestDispatcher("actualizarOrden.jsp").forward(req, resp);
		
		System.out.println("Usuario encontrado");
		
	}
	
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		
		
		// MANDAMOS LA NUEVA PERSONA AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL)
						.path("/{id}")
						.resolveTemplate("id", id);
		Response addResponse = add.request().delete();

		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();
				
		System.out.println("Usuario eliminado");
		resp.sendRedirect("consultar");

	}
	
	private void borrar_orden(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		
		// MANDAMOS LA NUEVA PERSONA AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL2)
				.path("/{id}")
				.resolveTemplate("id", id);
		Response addResponse = add.request().delete();

		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();

		System.out.println("Orden eliminada");
		resp.sendRedirect("consultar");
		
	}
	
	

}
