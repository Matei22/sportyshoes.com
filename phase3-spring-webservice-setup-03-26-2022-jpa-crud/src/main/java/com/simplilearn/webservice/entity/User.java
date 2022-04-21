package com.simplilearn.webservice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ecom_users")
public class User {
	@Id
	@Column(name="user_email")
	private String email;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="user_desc")
	private String desc;
	
	@Column(name="user_dateOfBirth")
	private Date dateOfBirth;
	

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="orders",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name="product_id")
			)
	private List<Product> products = new ArrayList<>();
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public User() {
		super();
	}

	public User(String email, String password, String name, String desc, Date dateOfBirth,
			List<Product> products) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.desc = desc;
		this.dateOfBirth = dateOfBirth;
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", desc=" + desc
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
	public void purchaseByUser(Product product) {
		products.add(product);
	}
}
