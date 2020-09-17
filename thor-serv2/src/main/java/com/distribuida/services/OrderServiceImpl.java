package com.distribuida.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.distribuida.entidades.Customer;
import com.distribuida.entidades.Orders;
import com.distribuida.proxi.CustomerProxyImpl;


@ApplicationScoped
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@PersistenceContext(name="myDatasource")
	private EntityManager em;
	
	@Inject
	private CustomerProxyImpl servicioCustomer;
	
	@Override
	public List<Orders> listar() throws SQLException{
		List<Orders> lista = null;
		lista = em.createNamedQuery("Orders.findAll", Orders.class).getResultList();
		return lista;
	}
	
	@Override
	public Orders buscarId(Integer id) {
		Orders order = em.find(Orders.class, id);
		return order;
	}
	
	@Override
	@Transactional
	public void crear(Orders ord) {
		
		em.persist(ord);	
	}
	
	

	/*@Override
	public List<Orders> buscarCustomerId(Long id) {
		return Orders.find("customer_id", id).list();
	}*/
	
	
	@Override
	@Transactional
	public void update(Orders ord) {
		Orders order = em.find(Orders.class, ord.getId());
		order.setItem(ord.getItem());
		order.setPrice(ord.getPrice());
		
		em.merge(order);
	}
	
	
	@Override
	public void delete(Integer id) {
		Orders order = em.find(Orders.class, id);
		if(!order.equals(null)) {
			em.remove(order);
		}
	}
	
	@Override
	public List<Orders> listaOrdersCustomers() throws SQLException{
		
		List<Customer> listaCustomer = servicioCustomer.listarAll();
		List<Orders> listaOrders = listar();
		List<Orders> lista = new ArrayList<>();
		
		for (Orders orders : listaOrders) {
			for (Customer customer : listaCustomer) {
				if(orders.customer_id == customer.id) {
					orders.nombreCustomer = customer.name;
					lista.add(orders);
				}
				
			}
			
		}
		
		return lista;
		
		
	}

}
