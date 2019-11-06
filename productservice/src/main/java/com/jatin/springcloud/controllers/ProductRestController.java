package com.jatin.springcloud.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jatin.springcloud.model.Coupon;
import com.jatin.springcloud.model.Product;
import com.jatin.springcloud.repos.ProductRepo;
import com.jatin.springcloud.restclients.CouponClient;


@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	CouponClient couponClient;
	
	@Autowired
	ProductRepo repo;
	
	@PostMapping("/products")
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return new ResponseEntity(repo.save(product),HttpStatus.CREATED);
	}
	

}