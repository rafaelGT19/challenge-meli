package com.meli.challenge.service.api;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;

/**
 * Service to process coupon request
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
public interface ICouponService {

	/**
	 * Process a coupon and return the list of items ids and amount
	 * whose maximize spend value
	 * @param coupon coupon request information
	 * @return
	 */
	CouponResponseDto processCoupon(CouponRequestDto coupon);

}
