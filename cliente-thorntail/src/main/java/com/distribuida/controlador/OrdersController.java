package com.distribuida.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.distribuida.datos.Orders;
import com.distribuida.service.OrderService;

@Named(value = "ordersController")
@ApplicationScoped
public class OrdersController {

	
	@Inject
	private OrderService orderService;
	
	private Orders order;
	private Long id;

	private List<Orders> orders;
	private boolean banderaListar;
	private boolean banderaCrear;
	private boolean banderaEditar;
	private boolean banderaBuscar;

	@PostConstruct
	public void init() {
		order = new Orders();

		System.out.println("ENTRA A INIT");
		orders = orderService.listarTodos();
		banderaListar = true;
		banderaCrear = false;
		banderaEditar = false;
		banderaBuscar = false;
	}

	public void listarOrders() {
		banderaListar = true;
		banderaCrear = false;
		banderaEditar = false;
		banderaBuscar = false;
		orders = orderService.listarTodos();
	}

	public void banderaCrear() {
		order = new Orders();
		banderaListar = false;
		banderaCrear = true;
		banderaEditar = false;
		banderaBuscar = false;
	}

	public void banderaEditar(Orders element) {
		order = new Orders();
		banderaListar = false;
		banderaCrear = false;
		banderaEditar = true;
		banderaBuscar = false;
		order = element;
	}

	public void banderaBuscar(Long id) {
		order = new Orders();
		banderaListar = false;
		banderaCrear = false;
		banderaEditar = true;
		banderaBuscar = false;
		this.id = id;
	}

	public void eliminarOrder(Orders element) {
		orderService.eliminarOrden(element.id);
		listarOrders();
	}

	public void guardarOrder() {
		String resp = orderService.crearOrden(order);
		System.out.println("RESPUESTA>>>>>" + resp);
		listarOrders();
	}

	public void actualizarOrder() {
		String resp = orderService.editarOrden(order);
		System.out.println("RESPUESTA>>>>>" + resp);
		listarOrders();
	}

	public void buscarOrder() {
		String resp = orderService.buscar(id);
		System.out.println("RESPUESTA>>>>>" + resp);
		listarOrders();
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public boolean isBanderaListar() {
		return banderaListar;
	}

	public void setBanderaListar(boolean banderaListar) {
		this.banderaListar = banderaListar;
	}

	public boolean isBanderaCrear() {
		return banderaCrear;
	}

	public void setBanderaCrear(boolean banderaCrear) {
		this.banderaCrear = banderaCrear;
	}

	public boolean isBanderaEditar() {
		return banderaEditar;
	}

	public void setBanderaEditar(boolean banderaEditar) {
		this.banderaEditar = banderaEditar;
	}

	public boolean isBanderaBuscar() {
		return banderaBuscar;
	}

	public void setBanderaBuscar(boolean banderaBuscar) {
		this.banderaBuscar = banderaBuscar;
	}

}
