package com.example.repo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.entities.Cart;
import com.example.entities.Product;
import com.example.response.CartByUserIdRes;
import com.example.response.CartItemRes;

@Component
public class CartRepoImp {
	@Autowired
	CartRepo cartR;
	@Autowired
	ProductRepo proR;
	public List<CartByUserIdRes> getCartByUserId(int userid) {
		List<Cart> item=cartR.findCartByUserId(userid);
		List<CartByUserIdRes> prof=new ArrayList<>();
		for(Cart c : item) {
			Product product=proR.findById(c.getProductid());
			CartByUserIdRes p=new CartByUserIdRes();
			p.setCartId(c.getCartId());
			p.setProduct(product.getName());
			prof.add(p);
			
		}
		return prof;
	}
	
	public CartItemRes addCart(int userid, int productid) {
		Cart cart=cartR.findCartItemByUserId(userid,productid);
		Product p=proR.findById(productid);
		if(cart==null) {
			Cart item=new Cart();
			item.setUser_id(userid);
			item.setProductid(productid);
			
			
			item.setPrice(p.getPrice());
			item.setQuantity(1);
			cartR.save(item);
		}
		else {
			cart.setQuantity(cart.getQuantity()+1);
			cart.setPrice(cart.getPrice()+p.getPrice());
			cartR.save(cart);
		}
		Cart cart1=cartR.findCartItemByUserId(userid,productid);
		CartItemRes res=new CartItemRes();
		res.setCartitemId(cart1.getCartId());
		res.setProduct(p);
		res.setQuantity(cart1.getQuantity());
		
		return res;
		
	}

	public CartItemRes getCartByCartId(int userid, int cartitemId) {
		Cart c=cartR.findCartItemBycartitemId(userid,cartitemId);
		Product p=proR.findById(c.getProductid());
		CartItemRes res=new CartItemRes();
		res.setCartitemId(c.getCartId());
		res.setProduct(p);
		res.setQuantity(c.getQuantity());
		
		return res;
	}

	public String removeCart(int userid, int productid) {
		Cart cart=cartR.findCartItemByUserId(userid,productid);
		cartR.delete(cart);
		Product p=proR.findById(productid);
		return(p.getName()+" removed from cart");
	}

	public CartItemRes changequantity(int userid, int productid, int i) {
		Cart cart=cartR.findCartItemByUserId(userid,productid);
		Product p=proR.findById(productid);
		float price=p.getPrice();
		int quantity=cart.getQuantity();
		price=(i*price)/quantity;
		cart.setPrice(price);
		cart.setQuantity(i);
		cartR.save(cart);
		CartItemRes res=new CartItemRes();
		res.setCartitemId(cart.getCartId());
		res.setProduct(p);
		res.setQuantity(cart.getQuantity());
		return res;
		
		
	}

	public CartItemRes changequantity1(int cartId, int i) {
		Cart cart=cartR.findBycartItemId(cartId);
		Product p=proR.findById(cart.getProductid());
		float price=p.getPrice();
		int quantity=i;
		price=(i*price)/quantity;
		cart.setPrice(price);
		cart.setQuantity(quantity);
		cartR.save(cart);
		CartItemRes res=new CartItemRes();
		res.setCartitemId(cart.getCartId());
		res.setProduct(p);
		res.setQuantity(cart.getQuantity());
		return res;
		
	}
	

}
