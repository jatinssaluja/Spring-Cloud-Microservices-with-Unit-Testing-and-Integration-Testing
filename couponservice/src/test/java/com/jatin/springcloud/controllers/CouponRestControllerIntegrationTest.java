package com.jatin.springcloud.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatin.springcloud.model.Coupon;
import com.jatin.springcloud.repos.CouponRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CouponRestControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	
	
	@Test
	public void contextLoads() throws Exception {
		
       ObjectMapper om = new ObjectMapper();

		
		Coupon coupon = new Coupon(1,"SUPERSALE1","10","12-12-2020");

		
		//GET Request
		String response = this.restTemplate.getForObject("/couponapi/coupons", String.class);
		JSONAssert.assertEquals("[{id:1,code:SUPERSALE1,discount:10,expDate:12-12-2020}]", response, false);
		
		
        //POST Request
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/couponapi/coupons", coupon, String.class);

        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        JSONAssert.assertEquals(om.writeValueAsString(coupon), postResponse.getBody(), false);
        
        
        //DELETE Request
        HttpEntity<String> deleteEntity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> deleteResponse = restTemplate.exchange("/couponapi/coupons/1", HttpMethod.DELETE, deleteEntity, String.class);

        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        
       
		
	}

}
