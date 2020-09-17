package com.distribuida.controlador;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.distribuida.datos.Customer;
import com.distribuida.service.CustomerService;


@Named(value = "customerController")
@ApplicationScoped
public class CustomerController {

	
	@Inject
	private CustomerService customerService ;
	
	private Customer customer;
	private Long id;

	private List<Customer> customers;
	private boolean banderaListar;
	private boolean banderaCrear;
	private boolean banderaEditar;
	private boolean banderaBuscar;
	@PostConstruct
	public void init() {
		customer = new Customer();
		
		System.out.println("ENTRA A INIT");
		customers = customerService.listarTodos();
		banderaListar = true;
		banderaCrear = false;
		banderaEditar = false;
		banderaBuscar=false;
	}
	
	public void listarCustomers() {
		banderaListar = true;
		banderaCrear = false;
		banderaEditar = false;
		banderaBuscar=false;
		customers= customerService.listarTodos();
	}
	
	public void banderaCrear() {
		customer = new Customer();
		banderaListar = false;
		banderaCrear = true;
		banderaEditar = false;
		banderaBuscar=false;
	}
	
	public void banderaEditar(Customer element) {
		customer = new Customer();
		banderaListar = false;
		banderaCrear = false;
		banderaEditar = true;
		banderaBuscar=false;
		customer= element;
	}
	
	public void banderaBuscar(Long id) {
		customer = new Customer();
		banderaListar = false;
		banderaCrear = false;
		banderaEditar = true;
		banderaBuscar=false;
		this.id=id;
	}
	
	public void eliminarCustomer(Customer element) {
		customerService.eliminarPersona(element.id);
		listarCustomers();
	}
	
	public void guardarCustomer() {
		String resp = customerService.crearPersona(customer);
		System.out.println("RESPUESTA>>>>>" + resp);
		listarCustomers();
	}
	
	public void actualizarCustomer() {
		String resp =customerService.editarPersona(customer);
		System.out.println("RESPUESTA>>>>>" + resp);
		listarCustomers();
	}
	
	
	public void buscarCustomer() {
		String resp =customerService.buscar(id);
		System.out.println("RESPUESTA>>>>>" + resp);
		listarCustomers();
		System.out.println("Estoy buscandor");
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
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
