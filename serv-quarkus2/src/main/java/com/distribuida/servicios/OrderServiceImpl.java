package com.distribuida.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.distribuida.entidades.Orders;

import io.quarkus.panache.common.Parameters;

@ApplicationScoped
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Override
	public Orders crear(Orders ord) {
		Orders.persist(ord);
		return ord;
	}
	
	@Override
	public Orders buscarId(Long id) {
		return Orders.findById(id);
	}
	
	//@Override
	public List<Orders> buscarCustomerId(Long id) {
		return Orders.find("customer_id", id).list();
		/*List<Orders> ordenes = new ArrayList<>();
		ordenes = Orders.find(query, params)
		return Orders.findById(id);*/
	}
	
	@Override
	public Orders update(Orders ord) {
		Orders order = new Orders();
		order.update("item = :item , price = :price where id = :id ", Parameters.with("item", ord.item).and("price", ord.price).and("id", ord.id).map());
		//cus.findById(cm.id);
		//cus.name = cm.name;
		//cus.surname = cm.surname;
		
		return order;
	}
	
	@Override
	public void delete(Long id) {
		//Customer c = new Customer();
		Orders.deleteById(id);
		//c.delete();
	}

}
