package com.simplilearn.webservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.entity.User;


import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.exception.ProductAlreadyExist;
import com.simplilearn.webservice.exception.ProductNotFound;

import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;

	// get one product by id
	@GetMapping("/product/{id}")
	public Product getOne(@PathVariable(value = "id") long id) {
		Optional<Product> data = productRepository.findById(id);
		if (!data.isPresent()) {
			throw new ProductNotFound("Product is not found with given id " + id);
		}
		return data.get();
	}

	// get one product by name
	@GetMapping("/product")
	public List<Product> getOne(@RequestParam(value = "name") String name) {
		List<Product> productList = productRepository.findByName(name);
		if (productList.isEmpty()) {
			throw new ProductNotFound("Product is not found with given name " + name);
		}
		return productList;
	}

	// get one product by enabled flag (active or inactive product)
	@GetMapping("/product/status")
	public List<Product> getOne(@RequestParam(value = "enabled") boolean enabled) {
		List<Product> productList = productRepository.findByEnabled(enabled);
		if (productList.isEmpty()) {
			throw new ProductNotFound("Product is not found with given status " + enabled);
		}
		return productList;
	}

	// get one product by name
	@GetMapping("/product/search")
	public List<Product> searchOne(@RequestParam(value = "name") String name) {
		List<Product> productList = productRepository.searchByName(name);
		if (productList.isEmpty()) {
			throw new ProductNotFound("Product is not found with given name " + name);
		}
		return productList;
	}

	// get all products
	@GetMapping("/products")
	public List<Product> getProducts() {
		List<Product> productList = productRepository.findAll();
		if (productList.isEmpty()) {
			throw new ProductNotFound("Empty product list, zero products found");
		}
		return productList;
	}

	// add product
	@PostMapping("/products")
	public Product addOne(@RequestBody Product product) {
		List<Product> productList = productRepository.findAll();
		for (Product pt : productList) {
			if (pt.getId() == product.getId()) {
				throw new ProductAlreadyExist("Product is already available with given id " + product.getId());
			}
		}
		return productRepository.save(product);
	}

	// update product
	@PutMapping("/products")
	public Product updateOne(@RequestBody Product product) {
		Optional<Product> data = productRepository.findById(product.getId());
		if (!data.isPresent()) {
			throw new ProductNotFound("Product is not found with given id " + product.getId());
		}
		return productRepository.save(product);
	}

	// delete product
	@DeleteMapping("/products/{id}")
	public String deleteOne(@PathVariable(value = "id") long id) {
		Optional<Product> data = productRepository.findById(id);
		if (!data.isPresent()) {
			throw new ProductNotFound("Product is not found with given id " + id);
		}
		productRepository.deleteById(id);
		return "Product is deleted successfully!";
	}
	
	@PutMapping("/users/{userId}/products/{productId}")
	public User orderFromUserToProduct(
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "productId") long productId
			) {
		Product product = productRepository.findById(productId).get();
		User user = userRepository.findById(userId).get();
		user.purchaseByUser(product);
		return userRepository.save(user);
	}

}
