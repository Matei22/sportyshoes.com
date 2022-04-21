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

import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.entity.User;
import com.simplilearn.webservice.exception.UserAlreadyExist;
import com.simplilearn.webservice.exception.UserNotFound;
import com.simplilearn.webservice.repository.ProductRepository;
import com.simplilearn.webservice.repository.UserRepository;


@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	
	//add user
	@PostMapping("/user")
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
	@DeleteMapping("/users/{id}")
	public String deleteOne(@PathVariable(value = "id") String id) {
		List<User> data = userRepository.findByEmail(id);
		if (data.isEmpty()) {
			throw new UserNotFound("User is not found with given mail(id) " + id);
		}
		userRepository.deleteById(id);
		return "User is deleted successfully!";
	}
	
	// get one user by email(id)
	@GetMapping("/user")
	public User getOne(@RequestParam(value = "email") String email) {
		Optional<User> userList = userRepository.findById(email);
		if (!userList.isPresent()) {
			throw new UserNotFound("User is not found with given id " + email);
		}
		return userList.get();
	}

	// get all users	
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			throw new UserNotFound("Empty user list, zero users found");
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


}
