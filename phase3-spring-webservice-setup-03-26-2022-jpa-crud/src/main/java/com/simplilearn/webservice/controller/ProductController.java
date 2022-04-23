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
import com.simplilearn.webservice.entity.AdminUser;
import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.exception.AdminNotFound;
import com.simplilearn.webservice.exception.NotAdmin;
import com.simplilearn.webservice.exception.ProductAlreadyExist;
import com.simplilearn.webservice.exception.ProductNotFound;
import com.simplilearn.webservice.exception.UserNotFound;
import com.simplilearn.webservice.repository.AdminUserRepository;
import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminUserRepository adminUserRepository;

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
	@PostMapping("/{admin_email}/{admin_pass}/products")
	public Product addOne(@RequestBody Product product,
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		List<Product> productList = productRepository.findAll();
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		for (Product pt : productList) {
			if (pt.getId() == product.getId()) {
				throw new ProductAlreadyExist("Product is already available with given id " + product.getId());
			}
		}
		if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		return productRepository.save(product);
	}

	// update product
	@PutMapping("/{admin_email}/{admin_pass}/products")
	public Product updateOne(@RequestBody Product product,
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		Optional<Product> data = productRepository.findById(product.getId());
		List<AdminUser> admin = adminUserRepository.findAll();

		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if (!data.isPresent()) {
			throw new ProductNotFound("Product is not found with given id " + product.getId());
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		return productRepository.save(product);
	}
	
	@PutMapping("/{admin_email}/{admin_pass}")
	public AdminUser updateOne(@RequestBody AdminUser adminUser,
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		List<AdminUser> admin = adminUserRepository.findByEmail(admin_email);

		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		
		return adminUserRepository.save(adminUser);
	}

	// delete product
	@DeleteMapping("/{admin_email}/{admin_pass}/products/{id}")
	public String deleteOne(@PathVariable(value = "id") long id,
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		Optional<Product> data = productRepository.findById(id);
		List<AdminUser> admin = adminUserRepository.findAll();

		if (!data.isPresent()) {
			throw new ProductNotFound("Product is not found with given id " + id);
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		productRepository.deleteById(id);
		return "Product is deleted successfully!";
	}
	
	@PutMapping("/users/{userId}/products/{productId}")
	public User orderFromUserToProduct(
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "productId") long productId
			) {
		if(!userRepository.findById(userId).isPresent()) {
			throw new UserNotFound("User is not found with given id: " + userId);
		}
		if(!productRepository.findById(productId).isPresent()) {
			throw new ProductNotFound("Product is not found with given id: " + productId);
		}
		Product product = productRepository.findById(productId).get();
		User user = userRepository.findById(userId).get();
		user.purchaseByUser(product);
		return userRepository.save(user);
	}

}
