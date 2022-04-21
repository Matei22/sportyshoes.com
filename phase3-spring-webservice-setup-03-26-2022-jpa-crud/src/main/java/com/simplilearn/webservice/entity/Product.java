package com.simplilearn.webservice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ecom_products")
public class Product {

	@Id
	@Column(name="product_id")
	private long id;
	
	@Column(name="product_name")
	private String name;
	
	@Column(name="product_price")
	private double price;
	
	@Column(name="product_desc")
	private String description;
	
	@Column(name="product_enable")
	private boolean enabled;
	
	@Column(name="product_create_at")
	private Date createAt;

	@JsonIgnore
	@ManyToMany(mappedBy = "products",cascade=CascadeType.ALL)

	private List<User> users = new ArrayList<>();
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	// get & set methods
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	// constructor 
	public Product() {
		super();
	}

	
	public Product(long id, String name, double price, String description, boolean enabled, Date createAt,
			List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.enabled = enabled;
		this.createAt = createAt;
		this.users = users;
	}
	// override toString
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", enabled=" + enabled + ", createAt=" + createAt + "]";
	}
	public void purchaseByUser(User user) {
		users.add(user);
	}
}
