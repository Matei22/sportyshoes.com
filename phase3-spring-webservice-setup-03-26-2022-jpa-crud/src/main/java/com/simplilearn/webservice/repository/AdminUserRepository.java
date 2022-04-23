package com.simplilearn.webservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simplilearn.webservice.entity.AdminUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, String>{
	
	List<AdminUser> findByEmail(String email);
	
}
