package com.distribuida.services;


import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.distribuida.entidades.Customer;


@ApplicationScoped
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@PersistenceContext(name="myDatasource")
	private EntityManager em;
	
	@Override
	public List<Customer> listar() throws SQLException{
		
		List<Customer> lista = null;
		lista = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
		
		return lista;
	}
	
	@Override
	@Transactional
	public void crear(Customer cm) {
		
		//Customer customer = new Customer(cm.getId(),cm.getName(), cm.getSurname());
		em.persist(cm);

			
	}
	
	@Override
	public Customer buscarId(Integer id) {
		Customer customer = em.find(Customer.class, id);
		return customer;
	}
	
	@Override
	@Transactional
	public void update(Customer cm) {
		
	Customer customer = em.find(Customer.class, cm.getId());
		
	customer.setName(cm.getName());
	customer.setSurname(cm.getSurname());
	em.merge(customer);
	
	}
	
	@Override
	public void delete(Integer id) {
		
		Customer customer = em.find(Customer.class, id);
		if(!customer.equals(null)) {
			em.remove(customer);
		}
		
				
	}

}
