package com.example.response;

import com.example.entities.Product;

public class CartItemRes {
private int cartitemId;
private Product product;
private int quantity;
public int getCartitemId() {
	return cartitemId;
}
public void setCartitemId(int cartitemId) {
	this.cartitemId = cartitemId;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
