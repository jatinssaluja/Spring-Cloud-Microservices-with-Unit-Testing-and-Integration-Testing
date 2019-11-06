package com.jatin.springcloud.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatin.springcloud.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {


	
	Coupon findByCode(String code);

}
