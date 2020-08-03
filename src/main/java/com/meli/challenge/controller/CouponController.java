package com.meli.challenge.controller;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.service.impl.CouponService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@ApiOperation(
		value = "Takes a list of favorite item ids and a maximum amount to spend and returns"
			+ "the subset of items whose maximize the total amount spend",
		response = CouponResponseDto.class)
	@ApiResponses(value = {
		@ApiResponse(
			code = 200,
			message = "Coupon was process successful and retrieve a list of items id whose "
				+ "maximize the spend amount",
			response = CouponResponseDto.class),
		@ApiResponse(
			code = 404,
			message = "Not was found result to match coupon criteria",
			response = CouponResponseDto.class),
		@ApiResponse(
			code = 503,
			message = "Error processing request",
			response = CouponResponseDto.class)
	})
	@PostMapping("/coupon")
	public ResponseEntity<CouponResponseDto> processCoupon(@RequestBody @Valid CouponRequestDto request) {
		log.info("Request received to process coupon with items length {} and amount {}",
			request.getItems().size(), request.getAmount());

		return ResponseEntity.status(HttpStatus.OK)
			.body(service.processCoupon(request));
	}

}
