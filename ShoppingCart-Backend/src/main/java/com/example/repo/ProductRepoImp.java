package com.example.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entities.Product;
import com.example.request.AddRequest;
import com.example.request.FilterRequest;
import com.example.request.ModifyRequest;
import com.example.response.AddResponse;
@Component
public  class ProductRepoImp{
    @Autowired
    ProductRepo proR;
    public boolean checkName(String s){
        List<Product> u =proR.findByname(s);
        if(u.isEmpty())
            return true;
        return false;
    }
    public List<Product> findByname(String s){
        return proR.findByname(s);
    }
    public void addProduct(Product u){
        proR.save(u);
    }
    public boolean checkProduct(String s){
        List<Product> u =findByname(s);
        if(u==null)
            return false;
        return true;
    }
    public Product findProductByName(String s){
        Product u = findByname(s).get(0);
        return u;
    }
  
    public Product findEntityById(int id) {

        Product u = proR.findById(id);
        return u;
    }
	
	public List<Product> findEntityBycategory(String category) {
		List<Product> u =proR.findBycategory(category);
		return u;
	}
	public List<Product> findEntityBysearch(String searchString) {
		 List<Product> u=proR.searchProducts(searchString);
	return u;
	}
	public List<Product> findEntityBycategoryfilter(String category, FilterRequest req) {
		List<Product> u =proR.findBycategory(category);
		List<Product> u1 =new ArrayList<Product>();
		if(req.getName()!=null) {
			for (Product p  : u) {
		        if (p.getName().contains(req.getName())) {
		            u1.add(p);
		        }
		    }
		    u= new ArrayList<>(u1);
		    u1.clear();  
		}
		if(req.getId()!=0){
			 for (Product p  : u) {
				 if (p.getId().equals(req.getId())) {
					 u1.add(p);
				 }
			 }
			 u=new ArrayList<>(u1);
			 u1.clear(); 
		}
		if(req.getPrice()!=0){
			 for (Product p  : u) {
		        if (p.getPrice() <= (req.getPrice())) {
		            u1.add(p);
		        }
			 }
			 u=new ArrayList<>(u1);
			 u1.clear(); 
		}
		if(req.getSubcategory()!=null) {
			for(Product p : u) {
				String str=p.getSubcategory();
				String [] strArray = str.split(","); 
				if(Arrays.stream(strArray).anyMatch(t -> t.equals(req.getSubcategory()))) {
					u1.add(p);
				}
			}
			u=new ArrayList<>(u1);
			u1.clear();
		}
		return u;
	}
	public AddResponse addProduct(AddRequest req) {
		 Product u = new Product();
	        u.setName(req.getName());
	        u.setPrice(req.getPrice());
	        u.setDetail(req.getDetail());
	        u.setCategory(req.getCategory());
	        u.setSubcategory(req.getSubcategory());
	       
	        AddResponse addResp = new AddResponse();
	        try {
	            if (!checkName(req.getName())) {
	                addResp.setId(u.getId());
	                addResp.setMessage("Product already exists");
	            }
	            else{
					addProduct(u);
	                addResp.setId(u.getId());
	                System.out.print(u.getId());
	                if (u.getId() == null)
	                    addResp.setMessage("Product Name And Price are required field");
	                else
	                    addResp.setMessage("Product added");
	                addResp.setName(req.getName());
	                addResp.setPrice(req.getPrice());
	                addResp.setDetail(req.getDetail());
	                addResp.setCategory(req.getCategory());
	                addResp.setSubcategory(req.getSubcategory());
	            }
	        } catch (Exception e) {

	           addResp.setMessage("Product not added");
	       }
			return addResp;
	}
	public Product updateProduct(ModifyRequest req) {
		Product prof=findEntityById(req.getProductid());
    	if(req.getName()!=null) {
    		prof.setName(req.getName());
    	}
    	if(req.getPrice()!=0){
    		prof.setPrice(req.getPrice());
    		}
    	if(req.getDetail()!=null) {
    		prof.setDetail(req.getDetail());
    	}
    	if(req.getCategory()!=null) {
    		prof.setCategory(req.getCategory());
    	}
    	if(req.getSubcategory()!=null) {
    		prof.setSubcategory(req.getSubcategory());
    	}
    	
    	proR.save(prof);
		return prof;
    	 
	}
	
   
}