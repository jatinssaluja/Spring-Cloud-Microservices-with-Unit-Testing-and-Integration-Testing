package com.jatin.springcloud.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Coupon {
	
	
	private Long id;
	private String code;
	private BigDecimal discount;
	private String expDate;
	
	
    public Coupon() {
		
	}
	
	public Coupon(int id, String code, String discount, String expDate) {
		
		this.id = new Long(id);
		this.code = code;
		this.discount = new BigDecimal(discount);
		this.expDate = expDate;
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

}
