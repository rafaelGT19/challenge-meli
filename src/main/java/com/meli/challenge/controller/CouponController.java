package com.meli.challenge.controller;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.service.impl.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Coupon controller
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
public class CouponController {

	/**
	 * Service to process coupon
	 */
	private final CouponService service;

	@Autowired
	public CouponController(CouponService service) {
		this.service = service;
	}

	@PostMapping("/coupon")
	public CouponResponseDto processCoupon(@RequestBody CouponRequestDto request) {
		log.info("Request received to process coupon with items length {} and amount {}",
			request.getItems().size(), request.getAmount());

		return service.processCoupon(request);
	}

}
