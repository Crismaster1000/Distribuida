package com.distribuida.servicios;


import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.distribuida.entidades.Customer;

import io.quarkus.panache.common.Parameters;

@ApplicationScoped
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Override
	public Customer crear(Customer cm) {
		Customer.persist(cm);
		return cm;
	}
	
	@Override
	public Customer buscarId(Long id) {
		return Customer.findById(id);
	}
	
	@Override
	public Customer update(Customer cm) {
		Customer cus = new Customer();
		cus.update("name = :name , surname = :surname where id = :id ", Parameters.with("name", cm.name).and("surname", cm.surname).and("id", cm.id).map());
		//cus.findById(cm.id);
		//cus.name = cm.name;
		//cus.surname = cm.surname;
		
		return cus;
	}
	
	@Override
	public void delete(Long id) {
		//Customer c = new Customer();
		Customer.deleteById(id);
		//c.delete();
	}

}
