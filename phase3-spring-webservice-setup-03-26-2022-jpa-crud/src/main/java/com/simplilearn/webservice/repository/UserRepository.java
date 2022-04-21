package com.simplilearn.webservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simplilearn.webservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	// Derived Query Methods in Spring
	List<User> findByEmail(String email);
	
	
	
	
	@Query("select u from User u where u.email LIKE %?1%")
	List<User> searchByEmail(String email);
}
