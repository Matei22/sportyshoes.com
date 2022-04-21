package com.simplilearn.webservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simplilearn.webservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	// Derived Query Methods in Spring
	List<Product> findById(String id);
	
	List<Product> findByName(String name);
	
	List<Product> findByPrice(double price);
	
	List<Product> findByEnabled(boolean enabled);
	
	@Query("select p from Product p where p.name LIKE %?1%")
	List<Product> searchByName(String name);
}
