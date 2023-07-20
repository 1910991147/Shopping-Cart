package com.example.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	 List<Product> findBycategory(String category);
	 List<Product> findByname(String name);
	 Product findById(int id);
	 @Query("SELECT p FROM Product p WHERE " +
	            "p.name LIKE CONCAT('%', :query, '%')" +
	            "Or p.detail LIKE CONCAT('%', :query, '%')")
	 List<Product> searchProducts(String query);
	 @Query("SELECT p.name FROM Product p WHERE p.id=:e ")
     Object findNameById(Integer e);
	 
}
