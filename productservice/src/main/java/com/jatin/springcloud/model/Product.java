package com.jatin.springcloud.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Must specify name!")
	private String name;
	
	@NotNull(message = "Must specify description!")
	private String description;
	
	@NotNull(message = "Must specify price!")
	private BigDecimal price;
	
	@NotNull(message = "Must specify couponCode!")
	@Transient
	private String couponCode;
	
	
	
    public Product() {
		
	}
	
	public Product(int id, String name, String description, String price, String couponCode) {
		
		this.id = new Long(id);
		this.name = name;
		this.description = description;
		this.price = new BigDecimal(price);
		this.couponCode = couponCode;
		
		
	}
	
   public Product(String name, String description, String price, String couponCode) {
		
		this.name = name;
		this.description = description;
		this.price = new BigDecimal(price);
		this.couponCode = couponCode;
		
		
	}
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

}
