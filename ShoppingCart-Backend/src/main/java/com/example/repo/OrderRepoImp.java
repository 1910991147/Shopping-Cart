package com.example.repo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entities.Cart;
import com.example.entities.Order;

@Component
public class OrderRepoImp {
	@Autowired
	OrderRepo orderR;
	@Autowired
	CartRepo cartR;
	@Autowired
	ProductRepo proR;
	public List<Order> createOrder(int userId) {
		Date date = new Date();
		
		List<Cart> item =cartR.findCartByUserId(userId);
		for(Cart c : item) {
			Order o=new Order();
			o.setDate(date);
			o.setProductId(c.getProductid());
			o.setQuantity(c.getQuantity());
			o.setUserId(userId);
			orderR.save(o);
			
		}
		cartR.deleteAll(item);
		List<Order> order=orderR.findByuserid(userId);
		return order;
	}
	@SuppressWarnings("unused")
	public List<Order> getOrders(int userId) {
		List<Order> orders=orderR.findByuserid(userId);
		return orders;	
	}
}
