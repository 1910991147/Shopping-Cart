package com.example.request;
public class FilterRequest {
	private int id;
	private String subcategory;
    public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private String name;
    private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
