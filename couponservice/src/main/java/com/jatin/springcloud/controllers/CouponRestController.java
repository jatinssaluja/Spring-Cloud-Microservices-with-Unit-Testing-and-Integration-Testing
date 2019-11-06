package com.jatin.springcloud.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jatin.springcloud.model.Coupon;
import com.jatin.springcloud.repos.CouponRepo;
import com.jatin.springcloud.utils.ErrorMessage;


@RestController
@RequestMapping("/couponapi")
public class CouponRestController {
	
	@Autowired
	CouponRepo repo;
	

	@PostMapping("/coupons")
	public ResponseEntity<Coupon> create(@Valid @RequestBody Coupon coupon)  {
	
		return new ResponseEntity(repo.save(coupon),HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code") String code) {
		
		
		if(repo.findByCode(code) != null) {
			
			return repo.findByCode(code);
			
		} else throw new CouponNotFoundException("Coupon Not Found");
		
		
	}
	
	@GetMapping("/coupons")
	public List<Coupon> getCoupons() {
	
		return repo.findAll();
	}
	
	@PutMapping("/coupons")
	public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon){
		
		Optional<Coupon> existingCoupon = repo.findById(coupon.getId());
		
		return existingCoupon.map(exc->{
			
			return new ResponseEntity(repo.save(coupon),HttpStatus.OK);
		}).orElse(new ResponseEntity(coupon,HttpStatus.BAD_REQUEST));
	
	
	}
	
	@DeleteMapping("/coupons/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable("id") Long id){
		
		
    Optional<Coupon> existingCoupon = repo.findById(id);
		
		return existingCoupon.map(exc->{
			
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.badRequest().build());
	
		
	
	}
	

}
