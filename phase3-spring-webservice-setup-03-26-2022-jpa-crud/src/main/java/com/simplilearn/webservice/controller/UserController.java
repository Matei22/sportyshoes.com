package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.simplilearn.webservice.entity.AdminUser;
import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.entity.User;
import com.simplilearn.webservice.exception.AdminNotFound;
import com.simplilearn.webservice.exception.NotAdmin;
import com.simplilearn.webservice.exception.UniqueAdminException;
import com.simplilearn.webservice.exception.UserAlreadyExist;
import com.simplilearn.webservice.exception.UserNotFound;
import com.simplilearn.webservice.repository.AdminUserRepository;
import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserRepository;


@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AdminUserRepository adminUserRepository;
	
	
	//add the admin
	@PostMapping("/admin/signup")
	public AdminUser addOne(@RequestBody AdminUser adminUser) {
		List<AdminUser> adminUserList = adminUserRepository.findAll();

		if (!adminUserList.isEmpty()) {
			throw new UniqueAdminException("There can be only one admin user: " + adminUserList.get(0));
		}
		return adminUserRepository.save(adminUser);
	}
	
	//add user
	@PostMapping("/user/login")
	public User addOne(@RequestBody User user) {
		List<User> userList = userRepository.findAll();
		for (User u : userList) {
			if (u.getEmail().equals(user.getEmail())) {
				throw new UserAlreadyExist("User is already available with given id " + user.getEmail());
			}
		}
		return userRepository.save(user);
	}
	
	// delete user
	@DeleteMapping("/{admin_email}/{admin_pass}/users/{id}")
	public String deleteOne(@PathVariable(value = "id") String id,
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass) {
		List<User> data = userRepository.findByEmail(id);
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if (data.isEmpty()) {
			throw new UserNotFound("User is not found with given mail(id) " + id);
			
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		userRepository.deleteById(id);
		return "User is deleted successfully!";
	}
	
	// get one user by email(id)
	@GetMapping("/{admin_email}/{admin_pass}/user")
	public User getOne(@RequestParam(value = "email") String email,
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass
			) {
		Optional<User> userList = userRepository.findById(email);
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if (!userList.isPresent()) {
			throw new UserNotFound("User is not found with given id " + email);
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		return userList.get();
	}

	// get all users	
	@GetMapping("/{admin_email}/{admin_pass}/users")
	public List<User> getUsers(
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass) {
		List<User> userList = userRepository.findAll();
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if (userList.isEmpty()) {
			throw new UserNotFound("Empty user list, zero users found");
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		return userList;
	}

	// update user
	@PutMapping("/users")
	public User updateOne(@RequestBody User user) {
		Optional<User> data = userRepository.findById(user.getEmail());
		if (!data.isPresent()) {
			throw new UserNotFound("User is not found with given id " + user.getEmail());
		}
		return userRepository.save(user);
	}
	
	//get all products that are in cart from all users
	@GetMapping("/{admin_email}/{admin_pass}/cart-elements")
	public List<Product> getAllPurchaseProducts(
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		List<Product> allProducts = new ArrayList<>();
		List<User> userList = userRepository.findAll();
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		for(User u: userList) {
			allProducts.addAll(u.getProducts());
		}
		return allProducts;
	}
	
	//map all products and numbers of purchase
	@GetMapping("/{admin_email}/{admin_pass}/products-purchaseNr")
	public Map<Product, Integer> mapProductsVsPurchaseNr(
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		Map<Product, Integer> result = new HashMap<>();
		List<Product> allProducts = new ArrayList<>();
		List<User> userList = userRepository.findAll();
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		for(User u: userList) {
			allProducts.addAll(u.getProducts());
			
		}
		for(Product p : allProducts) {
			if(result.containsKey(p)) {
				result.put(p, result.get(p)+1);
			}
			else {
				result.put(p, 1);
			}
		}
		return result;
	}
	
	//map all users id and purchase list
	@GetMapping("/{admin_email}/{admin_pass}/users-purchase-list")
	public Map<String, List<Product>> mapUsersIdVsPurchaseList(
			@PathVariable(value = "admin_email") String admin_email,
			@PathVariable(value = "admin_pass") String admin_pass){
		Map<String, List<Product>> result = new HashMap<>();
		List<User> userList = userRepository.findAll();
		List<AdminUser> admin = adminUserRepository.findAll();
		if (admin.isEmpty()) {
			throw new AdminNotFound("Admin is not found with id: " + admin_email + ". Please insert first an eligible admin");
		}
		else if(!admin.get(0).getEmail().equals(admin_email) || !admin.get(0).getPass().equals(admin_pass)) {
			throw new NotAdmin("The data of the admin isn't correct");
		}
		
		for(User u : userList) {
			result.put(u.getEmail(), u.getProducts());
		}
		return result;
	}


}
