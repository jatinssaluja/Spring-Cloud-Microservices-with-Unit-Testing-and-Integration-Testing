package com.jatin.springcloud.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatin.springcloud.model.Coupon;
import com.jatin.springcloud.model.Product;
import com.jatin.springcloud.repos.ProductRepo;
import com.jatin.springcloud.restclients.CouponClient;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepo repo;
	
	@MockBean
	private CouponClient couponClient;
	
	
	@Test
	public void createCouponWithoutException() throws Exception {
		
		Product postProduct = new Product("Iphone","Iphone10","2000","SUPERSALE1");
		Product mockProduct = new Product(1,"Iphone","Iphone10","1800","SUPERSALE1");
		Coupon coupon = new Coupon(1,"SUPERSALE1","10","12-12-2020");
		
	
		when(couponClient.getCoupon(postProduct.getCouponCode())).thenReturn(coupon);	
		when(repo.save(any(Product.class))).thenReturn(mockProduct);
		
		
		
		mockMvc.perform(post("/productapi/products")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(postProduct)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json("{id:1,name:Iphone,description:Iphone10,price:1800,couponCode:SUPERSALE1}"));
		
		
		
	}
	
static String asJsonString(Object obj) throws Exception {
		
		return new ObjectMapper().writeValueAsString(obj);
		
	}
	


}
